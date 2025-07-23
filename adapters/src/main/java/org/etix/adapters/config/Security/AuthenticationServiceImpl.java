package org.etix.adapters.config.Security;



import lombok.RequiredArgsConstructor;
import org.etix.adapters.driving.repositories.StrategieCompteRepository;
import org.etix.adapters.driving.repositories.UtilisateurRepository;
import org.etix.adapters.entities.Security.LogAccesEntity;
import org.etix.adapters.entities.Security.ProfilAccesEntity;
import org.etix.adapters.entities.Security.StrategieCompteEntity;
import org.etix.adapters.entities.Security.UtilisateurEntity;
import org.etix.adapters.utils.Request;
import org.etix.domain.exceptions.ApplicationAuthenticationException;
import org.etix.domain.exceptions.BadRequestException;
import org.etix.domain.exceptions.EntityNotExistsException;
import org.etix.domain.exceptions.NotificationException;
import org.etix.domain.models.Security.Principal;
import org.etix.domain.models.enumerations.TypeLog;
import org.etix.domain.models.request.MailRequest;
import org.etix.domain.ports.driver.GererSecurite;
import org.etix.domain.ports.driver.MailNotification;
import org.etix.domain.utils.CommonValidator;
import org.etix.domain.utils.Constant;
import org.etix.domain.utils.UtilisateurValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.sasl.AuthenticationException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    private final PasswordResetTokenService passwordResetTokenService;
    private final StrategieCompteRepository strategieCompteRepository;

    private final AuthenticationManager authenticationManager;
    private final UtilisateurRepository utilisateurRepository;

    private final GererSecurite gererSecurite;

    private final MailNotification notifyRecipient;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;

    @Override
    public boolean login(String username, String password) throws ApplicationAuthenticationException {

        try {

            // Validation plus explicite
            if (username == null || username.trim().isEmpty()) {
                throw new ApplicationAuthenticationException("Le nom d'utilisateur est requis.");
            }

            if (!UtilisateurValidator.isValidLogin(username)) {
                throw new ApplicationAuthenticationException("Format du nom d'utilisateur invalide.");
            }
            final org.springframework.security.core.Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            // Set the authentication in the SecurityContextHolder
            if (authentication.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

            return authentication.isAuthenticated();

        } catch (DisabledException e) {
            logger.error("Accès refusé - Utilisateur désactivé: " + username, e);
            throw new ApplicationAuthenticationException("Votre compte est désactivé. Contactez l'administrateur.");

        } catch (BadCredentialsException e) {
            logger.warn("Tentative de connexion échouée pour: " + username);
            throw new ApplicationAuthenticationException("Identifiants incorrects.");

        } catch (LockedException e) {
            logger.error("Compte verrouillé: " + username, e);
            throw new ApplicationAuthenticationException("Votre compte est verrouillé. Contactez l'administrateur.");

        } catch (Exception e) {
            logger.error("Erreur inattendue lors de la connexion: " + username, e);
            throw new ApplicationAuthenticationException("Une erreur technique s'est produite. Veuillez réessayer.");
        }
    }

    @Transactional
    @Override
    public boolean updatePassword(String password, String newPassword, String passwordConfirmed) {

        try {

            Principal principal = getPrincipal();
            if (principal == null || principal.getEmail() == null) {
                throw new BadRequestException("Le nom d'utilisateur est invalide.");
            }
            if (!newPassword.equals(passwordConfirmed)) {
                throw new BadRequestException("Le nouveau mot de passe est différent de la confirmation.");
            }
            StrategieCompteEntity strategieCompte = null;
            UtilisateurEntity toUpdate = utilisateurRepository.findByLogin(principal.getLogin())
                    .orElseThrow(() -> new EntityNotExistsException("Cet utilisateur est inexistant"));
            Optional<ProfilAccesEntity> optionalStrategie = toUpdate.getProfils().stream().findFirst();

            if (optionalStrategie.isEmpty()) {
                strategieCompte = strategieCompteRepository.findByLibelleIgnoreCase(Constant.DEFAULT_CODE)
                        .orElseThrow(() -> new EntityNotExistsException(
                                "Aucune stratégie n'a été trouvé pour votre mot de passe."));
            } else {
                strategieCompte = optionalStrategie.get().getStrategieCompte();
            }

            if (null == strategieCompte || null == strategieCompte.getGeneratedRegex()) {
                throw new EntityNotExistsException("Aucune stratégie n'a été trouvé pour votre mot de passe.");
            }
            if (!UtilisateurValidator.isValidPassword(strategieCompte.toDomain(), newPassword)) {
                throw new BadRequestException("Votre mot de passe n'est pas conforme à la politique de gestion de mot de passe.");
            }
            final Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(principal.getLogin(), password));
            if (!authentication.isAuthenticated()) {
                throw new ApplicationAuthenticationException("Le mot de passe est incorecte.");
            }
            String hashedPassword = passwordEncoder.encode(newPassword);
            toUpdate.setPassword(hashedPassword);
            toUpdate.setDateDernModification(LocalDateTime.now());

            utilisateurRepository.save(toUpdate);
            this.tracelog(toUpdate.getLogin());
        } catch (Exception ex) {
            if (ex instanceof DisabledException) {
                throw new ApplicationAuthenticationException("Aucun droit d'accès défini.");
            }
            if (ex instanceof AuthenticationException) {
                throw new ApplicationAuthenticationException("Identifiants invalides.");
            }
            throw new ApplicationAuthenticationException(ex.getMessage());
        }
        return true;
    }

    private void tracelog(String username) {
        LocalDateTime localeDate = LocalDateTime.now();
        LogAccesEntity log = LogAccesEntity.builder()
                .description(String.format("L'utilisateur nommé %s a modifié son mot de passe avec à BdtOnline à la date du %s.", username, localeDate))
                .libelle("Changement de mot de passe réussi")
                .auteur(username)
                .createdAt(localeDate)
                .type(TypeLog.INFO).build();

        gererSecurite.createLogAcces(log.toDomain());
    }

    /**
     * @param email
     * @return
     * @throws Exception
     */


    @Transactional
    @Override
    public boolean initPasswordResetRequest(String email) throws Exception {

        if (!CommonValidator.isValidEmail(email)) {
            throw new UsernameNotFoundException("Merci de renseigner une adresse email valide.");
        }

        String token = null;
        UtilisateurEntity utilisateur = utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotExistsException("Aucun compte n'est associé à cette adresse email."));
        do {
            token = UUID.randomUUID().toString();
        } while (passwordResetTokenService.countByToken(token) > 0);
        passwordResetTokenService.saveToken(token, utilisateur);

        MailRequest mailResquest = new MailRequest();
        String url = Request.urlBase() + "/auth/password-token/" + token;
        mailResquest.setRecipients(new String[]{utilisateur.getEmail()});
        mailResquest.setObject("eTix - Changement de mot de passe");

        Map<String, String> messageParams = new HashMap<String, String>();
        messageParams.put("NOM_OPERATEUR", utilisateur.getPrenom());
        messageParams.put("LIEN_DEMANDE", url);
        mailResquest.setContent(notifyRecipient.format("changement-mot-passe.html", messageParams));
        boolean mailResponse = notifyRecipient.envoyer(mailResquest);
        if (!mailResponse) {
            throw new NotificationException("L'envoi des instructions de réinitialisation du mot de passe n'a pas abouti.");
        }


        return true;
    }

    /**
     * @param password
     * @param password2
     * @param token
     * @return
     * @throws Exception
     */


    @Transactional
    @Override
    public boolean confirmPasswordResetRequest(String password, String password2, String token) throws Exception {

        if (!password.equals(password2)) {
            throw new BadRequestException("Veuillez renseigner deux mots de passe identiques.");
        }

        StrategieCompteEntity strategieCompte = null;
        PasswordResetToken prToken = passwordResetTokenService.findByToken(token)
                .orElseThrow(() -> new EntityNotExistsException("Le code de vérification est introuvable."));
        UtilisateurEntity utilisateur = prToken.getUtilisateur();
        Optional<ProfilAccesEntity> optionalStrategie = utilisateur.getProfils().stream().findFirst();

        if (optionalStrategie.isEmpty()) {
            strategieCompte = strategieCompteRepository.findByLibelleIgnoreCase(Constant.DEFAULT_CODE).orElseThrow(
                    () -> new EntityNotExistsException("Aucune stratégie n'a été trouvée pour votre mot de passe."));
        } else {
            strategieCompte = optionalStrategie.orElseThrow().getStrategieCompte();
        }
        if (null == strategieCompte || null == strategieCompte.getGeneratedRegex()) {
            throw new EntityNotExistsException("Aucune stratégie n'a été trouvée pour votre mot de passe.");
        }

        if (!UtilisateurValidator.isValidPassword(strategieCompte.toDomain(), password)) {
            throw new BadRequestException("Votre mot de passe n'est pas conforme à la politique de gestion de mot de passe.");
        }

        utilisateur.setDateDernModification(LocalDateTime.now());
        utilisateur.setPassword(passwordEncoder.encode(password));
        utilisateurRepository.save(utilisateur);
        prToken.setUsed(Boolean.TRUE);
        passwordResetTokenService.updateToken(prToken);

        MailRequest mailResquest = new MailRequest();
        mailResquest.setRecipients(new String[]{utilisateur.getEmail()});
        mailResquest.setObject("eTix  - Confirmation de changement de mot de passe");
        Map<String, String> messageParams = new HashMap<String, String>();
        messageParams.put("NOM_OPERATEUR", utilisateur.getPrenom());
        mailResquest.setContent(notifyRecipient.format("confirme-changement-mot-passe.html", messageParams));
        boolean mailResponse = notifyRecipient.envoyer(mailResquest);
        if (!mailResponse) {
            throw new NotificationException("L'envoi du mail de confirmation de changement de mot de passe n'a pas abouti.");
        }

        return true;
    }


    /**
     * @return
     */

    public boolean isAuthenticated() {
        try {
            org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || AnonymousAuthenticationToken.class.isAssignableFrom(authentication.getClass())) {
                return false;
            }
            return authentication.isAuthenticated();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public UtilisateurDto getPrincipal() {
        try {
            var auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null && auth.getPrincipal() instanceof ApplicationUtilisateur user) {
                return UtilisateurMapper.mapToDto(user.getUtilisateur());
            }
        } catch (Exception ex) {
            logger.error("Erreur lors de la récupération de l'utilisateur connecté", ex);
        }
        return null;
    }


}

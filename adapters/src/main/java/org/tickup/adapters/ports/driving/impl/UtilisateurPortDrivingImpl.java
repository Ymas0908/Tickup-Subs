package org.tickup.adapters.ports.driving.impl;


import org.tickup.adapters.entites.UtilisateurEntity;
import org.tickup.adapters.ports.driving.repositories.UtilisateurRepository;
import org.tickup.domain.exceptions.EntityAlreadyExistsException;
import org.tickup.domain.models.Utilisateur;
import org.tickup.domain.ports.driver.UtilisateurPortDriving;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
//@AllArgsConstructor
public class UtilisateurPortDrivingImpl implements UtilisateurPortDriving {

    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;

    public UtilisateurPortDrivingImpl(UtilisateurRepository utilisateurRepository, PasswordEncoder passwordEncoder) {
        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Utilisateur findUtilisateurSlimById(Integer id) {
        return null;
    }

    @Override
    public List<Utilisateur> getUtilisateurs() {
        return utilisateurRepository.getUtilisateurs().stream().map(UtilisateurEntity::toDomain).toList();
    }



    @Override
    public List<Utilisateur> getAllUtilisateursDto() {
        return null;
    }

    @Transactional
    @Override
    public Utilisateur saveUtilisateur(Utilisateur utilisateur) {

//        // Valider le nom et le(s) prénom(s) de l'utilisateur.
//        if (!UtilisateurValidator.isValidNom(utilisateur.getNom())) {
//            throw new BadRequestException(
//                    String.format("Le nom %s de l'utilisateur n'est pas valide.", utilisateur.getNom()));
//        }
//        if (!UtilisateurValidator.isValidPrenoms(utilisateur.getPrenom())) {
//            throw new BadRequestException(
//                    String.format("Le prénom %s de l'utilisateur n'est pas valide.", utilisateur.getPrenom()));
//        }
//
//
//        if (!UtilisateurValidator.isValidStatutActif(utilisateur.getStatutActif())) {
//            throw new BadRequestException(String.format("Le statut actif %s de l'utilisateur n'est pas valide.",
//                    utilisateur.getStatutActif()));
//        }
//
//        //  Valider login, l'e-mail et le code d'exploitant de l'utilisateur.
//        if (!UtilisateurValidator.isValidCodeExploitant(utilisateur.getCodeExploitant())) {
//            throw new BadRequestException(String.format("Le code d'exploitant %s de l'utilisateur n'est pas valide.",
//                    utilisateur.getCodeExploitant()));
//        }
//        if (!UtilisateurValidator.isValidEmail(utilisateur.getCodeClient())) {
//            throw new BadRequestException(
//                    String.format("L'adresse email %s de l'utilisateur n'est pas valide.", utilisateur.getCodeClient()));
//        }
//        if (!UtilisateurValidator.isValidLogin(utilisateur.getLogin())) {
//            throw new BadRequestException(
//                    String.format("Le login %s de l'utilisateur n'est pas valide.", utilisateur.getLogin()));
//        }

     /*   Utilisateur superieurHierachique = null;
        Optional<Utilisateur> optionalSuperieur = findById(utilisateur.getSuperieurHierachique().getId());
        if (optionalSuperieur.isPresent()) {
            superieurHierachique = optionalSuperieur.get();
        }*/
        // superieurHierachiqueId
   /*     Agence agence = agenceService.getAgenceById(utilisateur.getAgence().getId());
        Fonction fonction = fonctionService.getById(utilisateur.getFonction().getId());*/


        if (utilisateur.getId() == null) {
            utilisateur = createUtilisateur(utilisateur);
        } else {
            utilisateur = updateUtilisateur(utilisateur);
        }

        return utilisateur;
    }

    @Override
    public Utilisateur reinitialiseMotPasse(Utilisateur utilisateur) {
        return null;
    }

    @Override
    public Utilisateur createUtilisateur(Utilisateur utilisateur) {

        Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findByLogin(utilisateur.getLogin()).map(UtilisateurEntity::toDomain);
        if (optionalUtilisateur.isPresent()) {
            throw new EntityAlreadyExistsException(
                    String.format("Un utilisateur existe avec le login %s ", utilisateur.getLogin()));
        }
        LocalDateTime dateOperation = LocalDateTime.now();
        utilisateur.setCreatedAt(dateOperation);
        utilisateur.setStatutActif(Boolean.TRUE);
        utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
        utilisateur = utilisateurRepository.save(UtilisateurEntity.toEntity(utilisateur)).toDomain();
        return utilisateur;
    }

    @Override
    public Utilisateur updateUtilisateur(Utilisateur utilisateur) {
        Utilisateur toUpdate = getById(utilisateur.getId());
        if (!toUpdate.getLogin().equals(utilisateur.getLogin())
                && (utilisateurRepository.countByLogin(utilisateur.getLogin()) > 0)) {
            throw new EntityAlreadyExistsException(
                    String.format("Un utilisateur existe avec le login %s", utilisateur.getLogin()));
        }

//        if (!toUpdate.getCodeClient().equals(utilisateur.getCodeClient())
//                && (utilisateurRepository.countByEmail(utilisateur.getCodeClient()) > 0)) {
//            throw new EntityAlreadyExistsException(
//                    String.format("Un utilisateur existe avec l'email %s", utilisateur.getCodeClient()));
//
//        }
//
//        if (!toUpdate.getCodeExploitant().equals(utilisateur.getCodeExploitant())
//                && (utilisateurRepository.countByCodeExploitant(utilisateur.getCodeExploitant()) > 0)) {
//            throw new EntityAlreadyExistsException(String.format("Un utilisateur existe avec le code d'exploitant %s",
//                    utilisateur.getCodeExploitant()));
//
//        }

        utilisateur.setDateDernModification(LocalDateTime.now());
        utilisateur.setCreatedAt(toUpdate.getCreatedAt());
//        utilisateur.setPassword(toUpdate.getPassword());
        return utilisateurRepository.save(UtilisateurEntity.toEntity(utilisateur)).toDomain();
    }

    @Override
    public Utilisateur deleteUtilisateur(Utilisateur utilisateur) {
        Utilisateur toDelete = getById(utilisateur.getId());
        toDelete.setDeletedAt(LocalDateTime.now());
        toDelete.setStatutActif(Boolean.FALSE);
        return utilisateurRepository.save(UtilisateurEntity.toEntity(toDelete)).toDomain();
    }

    @Override
    public Utilisateur getById(Integer id) {
        System.out.println("id ://///////// " + id);
        UtilisateurEntity utilisateurEntity = utilisateurRepository.rechercherUtilisateurParPrincipal(id);
        return utilisateurEntity != null ? utilisateurEntity.toDomain() : null;

    }


    @Override
    public Optional<Utilisateur> findByLogin(String login) {
        return utilisateurRepository.findByLogin(login).map(UtilisateurEntity::toDomain);
    }

    @Override
    public Utilisateur getUtilisateurByLoginAndPassword(String login, String password) {
        return utilisateurRepository.findByLoginAndPassword(login, password).toDomain();
    }

//    @Override
//    public Utilisateur getUtilisateurConnected() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        if (authentication == null || !authentication.isAuthenticated()
//                || authentication.getPrincipal().equals("anonymousUser")) {
//            return null;
//        }
//
//        return (Utilisateur) authentication.getPrincipal();
//    }

    @Override
    public Utilisateur getUtilisateurConnected() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()
                || "anonymousUser".equals(authentication.getPrincipal())) {
            throw new AccessDeniedException("Utilisateur non connecté");
        }

        com.itcentrex.adapters.config.ApplicationUtilisateur appUser = (com.itcentrex.adapters.config.ApplicationUtilisateur) authentication.getPrincipal();
        System.out.println("appUser " + appUser);
        Utilisateur utilisateur = utilisateurRepository.findById(appUser.getId()).map(UtilisateurEntity::toDomain).orElse(null);
        if (utilisateur == null)
            throw new AccessDeniedException("Utilisateur non connecté");
        return utilisateur;
    }

    @Override
    public void save(Utilisateur utilisateur) {
        utilisateurRepository.save(UtilisateurEntity.toEntity(utilisateur));
    }
}

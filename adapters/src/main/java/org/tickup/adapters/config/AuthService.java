package org.tickup.adapters.config;


import org.tickup.adapters.entites.UsagerEntity;
import org.tickup.adapters.entites.UtilisateurEntity;
import org.tickup.adapters.ports.driving.repositories.UsagerRepositoryRepo;
import org.tickup.adapters.ports.driving.repositories.UtilisateurRepository;
import org.tickup.adapters.requestModel.SignUpRequest;
import org.tickup.adapters.requestModel.UpdateUserPasswordForgetRequest;
import org.tickup.adapters.requestModel.UpdateUserPasswordRequest;
import org.tickup.adapters.responseModel.LoginResponse;
import org.tickup.adapters.responseModel.UsagersResponse;
import jakarta.persistence.EntityExistsException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.tickup.domain.exceptions.BadRequestException;
import org.tickup.domain.models.Utilisateur;
import org.tickup.domain.ports.driving.GererUtilisateurPortDriver;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
//@Transactional(readOnly = true)
public class AuthService {

    private final UtilisateurRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final GererUtilisateurPortDriver gererUtilisateurPortDriver;
    private final UsagerRepositoryRepo usagerRepositoryRepo;

    public AuthService(UtilisateurRepository userRepo, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, GererUtilisateurPortDriver gererUtilisateurPortDriver, UsagerRepositoryRepo usagerRepositoryRepo) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.gererUtilisateurPortDriver = gererUtilisateurPortDriver;
        this.usagerRepositoryRepo = usagerRepositoryRepo;
    }


    public UtilisateurEntity singup(SignUpRequest signUpRequest) {

        Optional<UtilisateurEntity> user = userRepo.findByLogin(signUpRequest.getLogin());
        if (user.isPresent())
            throw new EntityExistsException("Un utilisateur existe déjà avec ce login " + signUpRequest.getLogin());
        String hashedPassword = passwordEncoder.encode(signUpRequest.getPassword());
        UtilisateurEntity newUser = UtilisateurEntity.builder()
//                .nom(signUpRequest.getNom().toUpperCase())
//                .prenom(signUpRequest.getPrenom().toUpperCase())
                .login(signUpRequest.getLogin())
                .password(hashedPassword)
//                .role(signUpRequest.getRole())
                .isFisrtConnection(true)
                .createdAt(LocalDateTime.now())
                .refUsager(signUpRequest.getRefUsager())
                .build();
        return userRepo.save(newUser);
    }

    public LoginResponse login(String login, String password, String terminalId, String terminalModel, String terminalMarque, String terminalIp) throws AccessDeniedException {
        try {
            Optional<UtilisateurEntity> user = userRepo.findByLogin(login);
            if (user.isEmpty()) throw new BadRequestException("Aucun utilisateur trouvé avec ce login");
            Authentication authenticate;
            authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, password));
            com.itcentrex.adapters.config.ApplicationUtilisateur principal = (com.itcentrex.adapters.config.ApplicationUtilisateur) authenticate.getPrincipal();
            System.out.println("ApplicationUtilisateur:::::::" + principal);
            Map<String, Object> claims = new HashMap<>();
            claims.put("username", principal.getUsername());
            claims.put("userId", principal.getId());
//            claims.put("roles", user.get().getRole());
            String accessToken = JwtHelper.generateAccessToken(login, claims);
            String refreshToken = JwtHelper.generateRefreshToken(login);


            LoginResponse build = LoginResponse.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .expiresIn(JwtHelper.getAccesTokenMinutes())
                    .isFisrtConnection(user.get().isFisrtConnection())
                    .refUsager(user.get().getRefUsager())
                    .build();

            user.get().setDateDernConnexion(LocalDateTime.now());
            user.get().setFisrtConnection(false);
            userRepo.save(user.get());
//            saveActivite(login, terminalId, terminalModel, terminalMarque, terminalIp, user.get().getRefAbonnement());
            return build;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

//    private void saveActivite(String login, String terminalId, String terminalModel, String terminalMarque, String terminalIp, String refAbonnement) {
//        try {
//            activiteDriver.save(
//                    new Activite.Builder()
//                            .libelle("Nouvelle tentative de connexion à votre compte " + AppUtils.troncatCodeClient(login))
//                            .type(TypeActivite.CONNEXION)
//                            .refAbonnement(refAbonnement)
//                            .terminal(
//                                    new Terminal.Builder()
//                                            .numeroSerie(terminalId)
//                                            .model(terminalModel)
//                                            .marque(terminalMarque)
//                                            .addressIp(terminalIp)
//                                            .build()
//                            )
//                            .build());
//        } catch (Exception e) {
//            log.error("Erreur lors de l'enregistrement de l'activité", e);
//        }
//    }

    public List<UtilisateurEntity> getUsers() {
        return userRepo.findAll();
    }


    public Utilisateur getUtilisateurCurrent() {
        return gererUtilisateurPortDriver.getUtilisateurConnected();
    }

    public void updatePassword(@Valid UpdateUserPasswordRequest updateUserPasswordRequest) {
        try {
            if (!updateUserPasswordRequest.getNewPassword().equals(updateUserPasswordRequest.getConfirmNewPassword()))
                throw new BadRequestException("Les mots de passe ne correspondent pas");
            Optional<UtilisateurEntity> user = userRepo.findByLogin(updateUserPasswordRequest.getLogin());
            if (user.isEmpty()) throw new AccessDeniedException("Aucun utilisateur trouvé avec ce login");
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(updateUserPasswordRequest.getLogin(), updateUserPasswordRequest.getOldPassword()));
            if (!authenticate.isAuthenticated()) throw new AccessDeniedException("Mot de passe incorrect");
            user.get().setPassword(passwordEncoder.encode(updateUserPasswordRequest.getNewPassword()));
            user.get().setDateDernConnexion(LocalDateTime.now());
            user.get().setFisrtConnection(false);
            userRepo.save(user.get());
        } catch (AccessDeniedException e) {
            throw new RuntimeException(e);
        }


    }

    public void updatePasswordForget(@Valid UpdateUserPasswordForgetRequest updateUserPasswordForgetRequest) throws AccessDeniedException {
        if (!updateUserPasswordForgetRequest.getNewPassword().equals(updateUserPasswordForgetRequest.getConfirmNewPassword()))
            throw new BadRequestException("Les mots de passe ne correspondent pas");
        Optional<UtilisateurEntity> user = userRepo.findByLogin(updateUserPasswordForgetRequest.getLogin());
        if (user.isEmpty()) throw new AccessDeniedException("Aucun utilisateur trouvé avec ce login");
//		Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(updateUserPasswordForgetRequest.getLogin(), user.get().getPassword()));
//		if (!authenticate.isAuthenticated()) throw new AccessDeniedException("Mot de passe incorrect");
        user.get().setPassword(passwordEncoder.encode(updateUserPasswordForgetRequest.getNewPassword()));
        user.get().setDateDernConnexion(LocalDateTime.now());
        user.get().setFisrtConnection(false);
        userRepo.save(user.get());

    }

    public LoginResponse refreshToken(String refreshToken) throws AccessDeniedException {
        try {
            String username = JwtHelper.extractUsername(refreshToken);
            Optional<UtilisateurEntity> user = userRepo.findByLogin(username);
            if (user.isEmpty()) throw new AccessDeniedException("Aucun utilisateur trouvé avec ce login");
            Map<String, Object> claims = new HashMap<>();
            claims.put("username", username);
            claims.put("userId", user.get().getId());
//            claims.put("roles", user.get().getRole());
            String accessToken = JwtHelper.generateAccessToken(username, claims);
            return LoginResponse.builder()
                    .accessToken(accessToken)
                    .expiresIn(JwtHelper.getAccesTokenMinutes())
                    .isFisrtConnection(user.get().isFisrtConnection())
                    .refUsager(user.get().getRefUsager())
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public UsagersResponse getUserConnected() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()
                || "anonymousUser".equals(authentication.getPrincipal())) {
            throw new AccessDeniedException("Utilisateur non connecté");
        }

        com.itcentrex.adapters.config.ApplicationUtilisateur appUser = (com.itcentrex.adapters.config.ApplicationUtilisateur) authentication.getPrincipal();

        System.out.println(" user connected ***" + appUser.getUsername() + " *** with id: " + appUser.getId());

        Optional<UtilisateurEntity> utilisateur = userRepo.findByLogin(appUser.getUsername());
        System.out.println("Utilisateur trouvé: " + utilisateur);
        if (utilisateur.isEmpty()) throw new AccessDeniedException("Aucun utilisateur trouvé avec ce login");
        UsagerEntity usager = usagerRepositoryRepo.findByRefUsager(utilisateur.get().getRefUsager()).orElseThrow(() -> new AccessDeniedException("Aucun utilisateur trouvé pour cette référence"));
        return  UsagersResponse.toResponse(usager);
    }
}

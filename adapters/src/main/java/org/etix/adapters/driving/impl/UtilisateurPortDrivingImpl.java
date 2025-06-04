//package org.etix.adapters.driving.impl;
//
//import com.gimsandbox.adapters.config.ApplicationUtilisateur;
//import com.gimsandbox.adapters.models.UtilisateurEntity;
//import com.gimsandbox.adapters.ports.driving.repositories.UtilisateurEntityRepo;
//import com.gimsandbox.domain.exceptions.EntityAlreadyExistsException;
//import com.gimsandbox.domain.models.Security.Utilisateur;
//import com.gimsandbox.domain.models.Security.UtilisateurSlim;
//import com.gimsandbox.domain.ports.driving.UtilisateurPortDrving;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//
//@Component
////@AllArgsConstructor
//public class UtilisateurPortDrivingImpl implements UtilisateurPortDrving {
//
//    private final UtilisateurEntityRepo utilisateurRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    public UtilisateurPortDrivingImpl(UtilisateurEntityRepo utilisateurRepository, PasswordEncoder passwordEncoder) {
//        this.utilisateurRepository = utilisateurRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//
//    @Override
//    public Utilisateur getUtilisateurConnected() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        if (authentication == null || !authentication.isAuthenticated()
//                || "anonymousUser" .equals(authentication.getPrincipal())) {
//            return null;
//        }
//
//        ApplicationUtilisateur appUser = (ApplicationUtilisateur) authentication.getPrincipal();
//
//        return new Utilisateur.Builder()
//                .id(appUser.getId())
//                .login(appUser.getUsername())
//                //.role(appUser.getAuthorities())
//                .build();
//
//        // etc.
//
//    }
//
//    @Override
//    public void save(Utilisateur utilisateur) {
//        utilisateurRepository.save(UtilisateurEntity.toEntity(utilisateur));
//    }
//}

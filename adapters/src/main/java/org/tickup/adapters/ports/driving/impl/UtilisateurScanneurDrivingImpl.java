package org.tickup.adapters.ports.driving.impl;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.tickup.adapters.entites.UtilisateurEntity;
import org.tickup.adapters.entites.UtlitisateurScanneurEntity;
import org.tickup.adapters.ports.driving.repositories.UtilisateurRepository;
import org.tickup.adapters.ports.driving.repositories.UtilisateurScanneurReposiroty;
import org.tickup.domain.exceptions.EntityAlreadyExistsException;
import org.tickup.domain.models.Utilisateur;
import org.tickup.domain.models.UtilisateurScanneur;
import org.tickup.domain.ports.driver.UtilisateurScanneurPortDriving;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Component
public class UtilisateurScanneurDrivingImpl implements UtilisateurScanneurPortDriving {
    private final UtilisateurScanneurReposiroty utilisateurScanneurReposiroty;
    private final PasswordEncoder passwordEncoder;

    public UtilisateurScanneurDrivingImpl(UtilisateurScanneurReposiroty utilisateurScanneurReposiroty, PasswordEncoder passwordEncoder) {
        this.utilisateurScanneurReposiroty = utilisateurScanneurReposiroty;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UtilisateurScanneur findUtilisateurScanneurSlimById(Integer id) {
        return null;
    }

    @Override
    public List<UtilisateurScanneur> getUtilisateurScanneurs() {
        return utilisateurScanneurReposiroty.getUtilisateurs().stream().map(UtlitisateurScanneurEntity::toDomain).toList();
    }

    @Override
    public List<UtilisateurScanneur> getAllUtilisateurScanneursDto() {
        return List.of();
    }

    @Override
    public UtilisateurScanneur saveUtilisateurScanneur(UtilisateurScanneur UtilisateurScanneur) {
        if (UtilisateurScanneur.getId() == null) {
            UtilisateurScanneur = createUtilisateurScanneur(UtilisateurScanneur);
        } else {
            UtilisateurScanneur = updateUtilisateurScanneur(UtilisateurScanneur);
        }

        return UtilisateurScanneur;
    }

    @Override
    public UtilisateurScanneur reinitialiseMotPasse(UtilisateurScanneur UtilisateurScanneur) {
        return null;
    }

    @Override
    public UtilisateurScanneur createUtilisateurScanneur(UtilisateurScanneur UtilisateurScanneur) {
        Optional<UtilisateurScanneur> optionalUtilisateur = utilisateurScanneurReposiroty.findByLogin(UtilisateurScanneur.getLogin()).map(UtlitisateurScanneurEntity::toDomain);
        if (optionalUtilisateur.isPresent()) {
            throw new EntityAlreadyExistsException(
                    String.format("Un utilisateur existe avec le login %s ", UtilisateurScanneur.getLogin()));
        }
        LocalDateTime dateOperation = LocalDateTime.now();
        UtilisateurScanneur.setCreatedAt(dateOperation);
        UtilisateurScanneur.setStatutActif(Boolean.TRUE);
        UtilisateurScanneur.setPassword(passwordEncoder.encode(UtilisateurScanneur.getPassword()));
        UtilisateurScanneur = utilisateurScanneurReposiroty.save(UtlitisateurScanneurEntity.toEntity(UtilisateurScanneur)).toDomain();
        return UtilisateurScanneur;
    }

    @Override
    public UtilisateurScanneur updateUtilisateurScanneur(UtilisateurScanneur UtilisateurScanneur) {
        UtilisateurScanneur toUpdate = getById(UtilisateurScanneur.getId());
        if (!toUpdate.getLogin().equals(UtilisateurScanneur.getLogin())
                && (utilisateurScanneurReposiroty.countByLogin(UtilisateurScanneur.getLogin()) > 0)) {
            throw new EntityAlreadyExistsException(
                    String.format("Un utilisateur existe avec le login %s", UtilisateurScanneur.getLogin()));
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

        UtilisateurScanneur.setDateDernModification(LocalDateTime.now());
        UtilisateurScanneur.setCreatedAt(toUpdate.getCreatedAt());
//        utilisateur.setPassword(toUpdate.getPassword());
        return utilisateurScanneurReposiroty.save(UtlitisateurScanneurEntity.toEntity(UtilisateurScanneur)).toDomain();    }

    @Override
    public UtilisateurScanneur deleteUtilisateurScanneur(UtilisateurScanneur UtilisateurScanneur) {
        UtilisateurScanneur toDelete = getById(UtilisateurScanneur.getId());
        toDelete.setDeletedAt(LocalDateTime.now());
        toDelete.setStatutActif(Boolean.FALSE);
        return utilisateurScanneurReposiroty.save(UtlitisateurScanneurEntity.toEntity(toDelete)).toDomain();    }

    @Override
    public UtilisateurScanneur getById(Integer valueOf) {
        System.out.println("id ://///////// " + id);
        UtlitisateurScanneurEntity utlitisateurScanneurEntity = utilisateurScanneurReposiroty.rechercherUtilisateurScanneurParPrincipal(valueOf);
        return utlitisateurScanneurEntity != null ? utlitisateurScanneurEntity.toDomain() : null;
    }

    @Override
    public Optional<UtilisateurScanneur> findByLogin(String login) {
        return utilisateurScanneurReposiroty.findByLogin(login).map(UtlitisateurScanneurEntity::toDomain);
    }

    @Override
    public UtilisateurScanneur getUtilisateurScanneurByLoginAndPassword(String login, String password) {
        return utilisateurScanneurReposiroty.findByLoginAndPassword(login, password).toDomain();
    }

    @Override
    public UtilisateurScanneur getUtilisateurScanneurConnected() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()
                || "anonymousUser".equals(authentication.getPrincipal())) {
            throw new AccessDeniedException("Utilisateur non connecté");
        }

        com.itcentrex.adapters.config.ApplicationUtilisateur appUser = (com.itcentrex.adapters.config.ApplicationUtilisateur) authentication.getPrincipal();
        System.out.println("appUser " + appUser);
        UtilisateurScanneur utilisateurScanneur = utilisateurScanneurReposiroty.findById(appUser.getId()).map(UtlitisateurScanneurEntity::toDomain).orElse(null);
        if (utilisateurScanneur == null)
            throw new AccessDeniedException("Utilisateur scanneur non connecté");
        return utilisateurScanneur;
    }

    @Override
    public void save(UtilisateurScanneur UtilisateurScanneur) {
        utilisateurScanneurReposiroty.save(UtlitisateurScanneurEntity.toEntity(UtilisateurScanneur));

    }
}

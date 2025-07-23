package org.etix.adapters.config.Security;


import com.itcentrex.adapter.entities.Security.FonctionnaliteEntity;
import com.itcentrex.adapter.entities.Security.ProfilAccesEntity;
import com.itcentrex.adapter.entities.Security.UtilisateurEntity;
import com.itcentrex.adapter.ports.driven.repositories.UtilisateurRepository;
import com.itcentrex.domain.ports.driven.FonctionnaliteRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Component
@AllArgsConstructor
public class ApplicationUtilisateurService implements UserDetailsService {


    private final UtilisateurRepository utilisateurRepo;
    private final FonctionnaliteRepo fonctionRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UtilisateurEntity> optionalUtilisateur = utilisateurRepo.findByLogin(username);
        if (optionalUtilisateur.isEmpty()) {
            throw new UsernameNotFoundException("Utilisateur non trouvé");
        }

        UtilisateurEntity utilisateur = optionalUtilisateur.orElseThrow();
        Optional<ProfilAccesEntity> optionalFirstProfil = utilisateur.getProfils().stream().findFirst();
        if (optionalFirstProfil.isEmpty()) {
            throw new UsernameNotFoundException("Aucun profil d'accès trouvé.");
        }

        Set<Integer> profilsId = utilisateur.getProfils().stream().map(profil -> profil.getId()).collect(Collectors.toSet());
        List<FonctionnaliteEntity> fonctionnalites = fonctionRepo.getFonctionnalitesByProfilIdsIn(profilsId).stream().map(FonctionnaliteEntity::toEntity).collect(Collectors.toList());
        return new ApplicationUtilisateur(utilisateur, fonctionnalites, optionalFirstProfil.orElseThrow().getFonctionnaliteAccueil());
    }

}

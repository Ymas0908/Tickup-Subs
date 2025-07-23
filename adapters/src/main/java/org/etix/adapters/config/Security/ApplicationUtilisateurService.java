package org.etix.adapters.config.Security;



import lombok.AllArgsConstructor;
import org.etix.adapters.driving.repositories.UtilisateurRepository;
import org.etix.adapters.entities.Security.FonctionnaliteEntity;
import org.etix.adapters.entities.Security.ProfilAccesEntity;
import org.etix.adapters.entities.Security.UtilisateurEntity;
import org.etix.domain.ports.driving.FonctionnaliteRepo;
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

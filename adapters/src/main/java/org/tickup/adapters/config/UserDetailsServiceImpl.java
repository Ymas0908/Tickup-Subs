package org.tickup.adapters.config;

import org.tickup.adapters.entites.UtilisateurEntity;
import org.tickup.adapters.entites.UtlitisateurScanneurEntity;
import org.tickup.adapters.ports.driving.repositories.UtilisateurRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.tickup.adapters.ports.driving.repositories.UtilisateurScanneurReposiroty;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UtilisateurRepository userRepo;
	private final UtilisateurScanneurReposiroty utilisateurScanneurRepository;

	public UserDetailsServiceImpl(UtilisateurRepository userRepo, UtilisateurScanneurReposiroty utilisateurScanneurRepository) {
		this.userRepo = userRepo;
        this.utilisateurScanneurRepository = utilisateurScanneurRepository;
    }

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Chercher d'abord dans les usagers
		Optional<UtilisateurEntity> user = userRepo.findByLogin(username);
		if (user.isPresent()) {
			return new ApplicationUtilisateur.Builder()
					.id(user.get().getId())
					.login(user.get().getLogin())
					.password(user.get().getPassword())
//					.roles(authority)
					.build();
		}

		// Si pas trouvé dans les usagers, chercher dans les scanneurs
		Optional<UtlitisateurScanneurEntity> scanneur = utilisateurScanneurRepository.findByLogin(username);
		if (scanneur.isPresent()) {
			return new ApplicationUtilisateur.Builder()
					.id(scanneur.get().getId())
					.login(scanneur.get().getLogin())
					.password(scanneur.get().getPassword())
//					.roles(authority)
					.build();
		}

		// Si ni usager ni scanneur trouvé
		throw new UsernameNotFoundException("Aucun utilisateur (usager ou scanneur) trouvé avec ce login " + username);
	}


}

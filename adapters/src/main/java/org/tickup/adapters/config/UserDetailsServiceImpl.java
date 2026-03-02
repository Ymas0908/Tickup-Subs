package org.tickup.adapters.config;

import org.tickup.adapters.entites.UtilisateurEntity;
import org.tickup.adapters.ports.driving.repositories.UtilisateurRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UtilisateurRepository userRepo;

	public UserDetailsServiceImpl(UtilisateurRepository userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UtilisateurEntity> user = userRepo.findByLogin(username);
		if (user.isEmpty()) throw new EntityNotFoundException("Aucun utilisateur trouvé avec ce login " + username);


		// Convertir les rôles en GrantedAuthority
//		List<SimpleGrantedAuthority> authority = Collections.singletonList(new SimpleGrantedAuthority(user.get().getRole().name()));

		return new com.itcentrex.adapters.config.ApplicationUtilisateur.Builder()
				.id(user.get().getId())
				.login(user.get().getLogin())
				.password(user.get().getPassword())
//				.roles(authority)
				.build();
	}
}

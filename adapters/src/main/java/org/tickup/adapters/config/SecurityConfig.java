package org.tickup.adapters.config;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
//@AllArgsConstructor
public class SecurityConfig {

	private final UserDetailsServiceImpl userDetailsService;
	private final JwtAuthFilter jwtAuthFilter;

	public SecurityConfig(UserDetailsServiceImpl userDetailsService, JwtAuthFilter jwtAuthFilter) {
		this.userDetailsService = userDetailsService;
		this.jwtAuthFilter = jwtAuthFilter;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {
		try {
			return http
					.cors(AbstractHttpConfigurer::disable)
					.csrf(AbstractHttpConfigurer::disable)
					.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
					.authorizeHttpRequests(auth -> auth
							// Public
							.requestMatchers("/", "/favicon.ico", "*.xhtml", "/test/**").permitAll()
							.requestMatchers(
									"/swagger-ui.html",
									"/swagger-ui/**",
									"/v3/api-docs/**",
									"/api-docs/**",
									"/swagger-resources/**",
									"/webjars/**").permitAll()
							.requestMatchers("/api/v1/auth/register").permitAll() // Inscription
							.requestMatchers("api/v1/otp/generate").permitAll()
							.requestMatchers("/api/v1/auth/login").permitAll()    // Login (si tu gardes cette route)
							.requestMatchers("/api/v1/auth/users/login").permitAll() // Login actuel
							.requestMatchers("/api/v1/auth/scanneurs/login").permitAll() // Login actuel
							.requestMatchers("/api/v1/usagers/usager").permitAll() // Création utilisateur
							.requestMatchers("/api/v1/scanneurs/scanneur").permitAll() // Création utilisateur
							.requestMatchers("/health/**").permitAll()
							.requestMatchers(HttpMethod.GET, "/error").permitAll()

							// Tout le reste doit être authentifié
							.anyRequest().authenticated()
					)
					.exceptionHandling(exception -> exception
							.authenticationEntryPoint((request, response, authException) -> {
								System.out.println("request: " + request.getRequestURI());
								System.out.println("🔴 Auth error: " + authException.getMessage());
								response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
							})
					)
					.authenticationManager(authenticationManager)
					.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
					.build();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
		AuthenticationManagerBuilder authenticationManagerBuilder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		return authenticationManagerBuilder.build();
	}
}

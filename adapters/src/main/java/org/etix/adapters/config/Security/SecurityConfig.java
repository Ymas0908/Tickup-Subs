package org.etix.adapters.config.Security;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationProvider authProvider;
    private final AuthSuccessHandler authSuccessHandler;


    @Bean
    public SecurityFilterChain configure(HttpSecurity http) {

        try {
            http
                    //Desactiver CSRF si non requis
                    .csrf(AbstractHttpConfigurer::disable)

                    //Configurer les règles d'autorisation
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers("/").permitAll()
                            .requestMatchers("/public/**", "/api-docs/**", "/swagger-ui/**").permitAll()
                            .requestMatchers("/**.jsf", "/**.xhtml", "/auth/**").permitAll()
                            .requestMatchers("/jakarta.faces.resource/**").permitAll()
                            .requestMatchers("*.xhtml").permitAll()
                            .requestMatchers("/auth/**").permitAll()
                            .requestMatchers("/api/v1/**").permitAll()
                            .anyRequest()
                            .authenticated()
                    )

                    // Configurer l'authentification via formulaire
                    .formLogin(form -> form
                            .loginPage("/auth/connexion.xhtml")
                            .failureUrl("/auth/connexion?error=true")
                            .successHandler(authSuccessHandler)
                    )
                    //Configurer le formulaire de l'authentification
                    .authenticationProvider(authProvider)
                    // Configurer la deconnexion
                    .logout(logout -> logout
                            .logoutUrl("/logout")
                            .invalidateHttpSession(true)
                            .logoutSuccessUrl("/auth/connexion.xhtml")
                            .deleteCookies("JSESSIONID")
                    );
            return http.build();
        } catch (Exception ex) {
            throw new BeanCreationException("Wrong spring security configuration", ex);
        }
    }
}

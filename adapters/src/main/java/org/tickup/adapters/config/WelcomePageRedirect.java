package org.tickup.adapters.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WelcomePageRedirect implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        // Rediriger la racine vers Swagger UI
        registry.addViewController("/")
                .setViewName("redirect:/swagger-ui/index.html");

        // Toujours donner la plus haute priorité
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }
}
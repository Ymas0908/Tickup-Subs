/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.etix.adapters.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Luc
 */
@Configuration
public class WelcomePageRedirect implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {


        /*registry.addViewController("/")
                .setViewName("redirect:/content/starter.xhtml");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);*/

        registry.addViewController("/")
                .setViewName("redirect:/swagger-ui/index.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

}

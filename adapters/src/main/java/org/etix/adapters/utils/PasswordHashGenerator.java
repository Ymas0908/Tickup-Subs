package org.etix.adapters.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordHashGenerator {

    @Autowired
    private PasswordEncoder passwordEncoder;
/*
    @PostConstruct
    public void generate() {
        String encoded = passwordEncoder.encode("DccProxy@2025");
        System.out.println("Mot de passe encodé = " + encoded);
    }*/

 /*   @PostConstruct
    public void testPassword() {
        String rawPassword = "DccProxy@2025";
        String hashedPassword = "$2a$10$4J/aHQVEw4iixLzi6EhrDecPTfI/qV62gcJhT3eujd2CzBA3u88fm"; // Mets ici celui en base
        boolean match = passwordEncoder.matches(rawPassword, hashedPassword);
        System.out.println("Le mot de passe correspond ? " + match);
    }*/
}

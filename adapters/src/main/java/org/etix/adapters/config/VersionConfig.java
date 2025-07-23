package org.etix.adapters.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
/**
 * @author Nikiema Ismael
 * Cette classe permet de recuperer la version de l'application
 */
public class VersionConfig {

    private final String version;

    public VersionConfig(@Value("${application.version}") String version) {
        this.version = version;
    }

}

package org.etix.adapters.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration
public class SwaggerConfig {


    private final String API_VERSION;
    private final String TITLE;

    public SwaggerConfig(@Value("${application.version}") String apiVersion, @Value("${application.title}") String title) {
        TITLE = title;
        API_VERSION = apiVersion;
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .servers(
                        List.of(
                                new Server().url("http://localhost:9005").description("Serveur local....8")
//                                new Server().url("https://gimpaywallet.it-centrex.com").description("Serveur de pré-production....")
                        )
                )
                .info(new Info()
                        .title(TITLE)
                        .version(API_VERSION)
                );
    }
}

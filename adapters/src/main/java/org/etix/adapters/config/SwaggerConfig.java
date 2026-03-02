package org.etix.adapters.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .servers(
                        List.of(
                                new Server().url("http://localhost:8004").description("Serveur local"),
                                new Server().url("https://gimregistrar.it-centrex.com").description("Serveur de production")
                        )
                )
                .info(new Info()
                        .title("GimRegistrar APIs register")
                        .version("1.0")
                );
    }
}

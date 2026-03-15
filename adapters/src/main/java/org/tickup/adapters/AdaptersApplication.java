package org.tickup.adapters;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@OpenAPIDefinition(
        info = @Info(
                title = "TickUp Subs API",
                version = "1.0",
                description = "API pour la gestion des usagers",
                license = @License(
                        name = "MIT License",
                        url = "https://opensource.org/licenses/MIT"
                )
        )
)
@SpringBootApplication
public class AdaptersApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdaptersApplication.class, args);
    }

}

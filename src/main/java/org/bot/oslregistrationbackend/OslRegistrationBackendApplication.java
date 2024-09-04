package org.bot.oslregistrationbackend;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class OslRegistrationBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(OslRegistrationBackendApplication.class, args);
    }

}

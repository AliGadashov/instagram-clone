package com.gadashov.instagram.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Author: Ali Gadashov
 * Version: v1.0
 * Date: 3/10/2024
 * Time: 12:21 PM
 */

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .addSecurityItem(
                        new SecurityRequirement()
                                .addList("Bearer Authentication"
                                )
                )
                .components(new Components().addSecuritySchemes(
                                "Bearer Authentication",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP)
                                        .bearerFormat("JWT")
                                        .scheme("bearer")
                        )
                )
                .info(
                        new Info()
                                .title("Instagram Rest Api")
                                .description("Examples for api")
                                .version("1.0")
                                .contact(new Contact()
                                                .name("Your name")
                                                .email("Your email")
                                                .url("Your URL")
                                )
                );
    }

}

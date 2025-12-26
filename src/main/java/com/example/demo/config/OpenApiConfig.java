package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Components;          // ✅ CORRECT IMPORT
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    public static final String SECURITY_SCHEME_NAME = "bearerAuth";

    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()
            .info(new Info()
                .title("Vendor Compliance API")
                .version("1.0")
                .description("Vendor Compliance – Document Validator"))

            // ✅ Your server URL
            .servers(List.of(
                new Server().url("https://9114.pro604cr.amypo.ai")
            ))

            // ✅ JWT Security Scheme
            .components(new Components()
                .addSecuritySchemes(SECURITY_SCHEME_NAME,
                    new SecurityScheme()
                        .name(SECURITY_SCHEME_NAME)
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")
                )
            )

            // ✅ Apply security globally (🔒 icons appear)
            .addSecurityItem(
                new SecurityRequirement().addList(SECURITY_SCHEME_NAME)
            );
    }
}

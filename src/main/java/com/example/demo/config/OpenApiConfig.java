// package com.example.demo.config;

// import io.swagger.v3.oas.models.OpenAPI;
// import io.swagger.v3.oas.models.servers.Server;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import java.util.List;

// @Configuration
// public class OpenApiConfig {

//     @Bean
//     public OpenAPI customOpenAPI() {
//         return new OpenAPI()
//                 // You need to change the port as per your server
//                 .servers(List.of(
//                         new Server().url("https://9085.pro604cr.amypo.ai")
//                 ));
//         }
// }


package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        // Must match the key used in .addList()
        final String securitySchemeName = "bearerAuth"; 

        return new OpenAPI()
                .info(new Info()
                        .title("Vendor Compliance - Document Validator API")
                        .version("1.0")
                        .description("API for managing vendor documents, compliance rules, and scoring."))
                // 🌍 Server URL
                .servers(List.of(
                        new Server().url("https://9114.pro604cr.amypo.ai")
                ))
                // 🏷️ Adding tags for controller groups 
                .tags(List.of(
                        new Tag().name("Auth").description("Authentication endpoints"),
                        new Tag().name("Vendors").description("Vendor management"),
                        new Tag().name("Documents").description("Vendor document operations"),
                        new Tag().name("Compliance").description("Rules and Scoring evaluation")
                ))
                // 🔒 Apply security globally to all protected endpoints [cite: 372]
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                // 🔑 Register security scheme definition [cite: 372]
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .name(securitySchemeName)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")));
    }
}

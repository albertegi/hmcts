package com.example.hmcts.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("HMCTS Task Management API")
                        .description("REST API for managing caseworker tasks in HMCTS system")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("HMCTS Development Team")
                                .email("dev@hmcts.gov.uk")
                                .url("https://hmcts.gov.uk"))
                        .license(new License()
                                .name("No License")
                                .url("https://example.org")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080")
                                .description("Development Server")
                ));
    }
}

package com.example.demo.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Value("${app.name:MicroServicio4 API}")
    private String appName;

    @Value("${app.description:API para gestión de dashboard}")
    private String appDescription;

    @Value("${app.version:1.0.0}")
    private String appVersion;

    @Value("${app.contact.name:Equipo de Desarrollo}")
    private String contactName;

    @Value("${app.contact.email:office@scrapetok.com}")
    private String contactEmail;

    @Value("${app.license.name:MIT License}")
    private String licenseName;

    @Value("${app.license.url:https://opensource.org/licenses/MIT}")
    private String licenseUrl;

    @Value("${server.port:8080}")
    private String serverPort;

    @Value("${spring.profiles.active:local}")
    private String activeProfile;

    @Value("${app.api.base.url:http://localhost:8080}")
    private String apiBaseUrl;

    @Bean
    public OpenAPI customOpenAPI() {
        OpenAPI openAPI = new OpenAPI()
                .info(new Info()
                        .title(appName)
                        .description(appDescription)
                        .version(appVersion)
                        .contact(new Contact()
                                .name(contactName)
                                .email(contactEmail))
                        .license(new License()
                                .name(licenseName)
                                .url(licenseUrl)))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .description("Token JWT para autenticación")));

        openAPI.addServersItem(new Server()
                .url(apiBaseUrl)
                .description("Servidor de " + activeProfile));

        return openAPI;
    }
}

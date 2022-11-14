package com.empresax.core.application.openapi;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiDoc {
    @Bean
    public OpenAPI infoOpenApi() {
        return new OpenAPI()
                .info(new Info().title("Ecommerce API RESTful")
                        .description("This project was developed for learning porpouses, if you found any problem and you want to notify me or want to contact me yo can do it through my Linkedln Done by: @efrxngg")
                        .version("v1")
                        .license(
                                new License().name("Apache 2.0").url("http://www.apache.org/licenses/LICENSE-2.0.html"))
                        .contact(new Contact()
                                .name("Efren Galarza")
                                .email("efrenpgc2602@gmail.com")
                                .url("https://github.com/efrxngg")))
                .externalDocs(new ExternalDocumentation()
                        .description("by @efrxngg")
                        .url("https://github.com/efrxngg?tab=repositories"));
    }

    @Bean
    public GroupedOpenApi userOpenApi() {
        return GroupedOpenApi.builder().group("")
                .pathsToMatch("/application/rest/**")
                .build();
    }
}
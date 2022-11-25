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
                .info(new Info().title("E-commerce API REST")
                        .description("Este proyecto fue desarrollado con fines de aprendizaje si encuentras algún error o deseas comunicarte conmigo puedes hacerlo mediante mi LinkedIn, el repository del proyecto tambien se encuentra en mi perfil. <br><br><b>Instrucciones de uso:</b> <br>1.- Registrate en el apartado de usuario. <br>2.- Inicia Session en el apartado de Authentication y coloca el token devuelto en el candadito de la parte superior. <br>3.- En el apartado de Product escoja los ids de los productos  que desees agregar a tu carrito. <br>4.- En el apartado de Cart dentro items coloque el id y la cantidad de los productos seleccionados.<br>5.- Por ultimo en el apartado de Invoice puede darle a generar factura para obtener el valor total a cancelar.<br><b>Usuario Admin:</b> efrxngg, password: root1234")
                        .version("v1")
                        .license(
                                new License().name("Apache 2.0").url("http://www.apache.org/licenses/LICENSE-2.0.html"))
                        .contact(new Contact()
                                .name("LinkedIn")
                                // .email("efrenpgc2602@gmail.com")
                                .url("https://www.linkedin.com/in/efren-galarza-chavez-a7b245243/")))
                .externalDocs(new ExternalDocumentation()
                        .description("by @efrxngg")
                        .url("https://www.linkedin.com/in/efren-galarza-chavez-a7b245243/"));
    }

    @Bean
    public GroupedOpenApi userOpenApi() {
        return GroupedOpenApi.builder().group("")
                .pathsToMatch("/application/rest/**")
                .build();
    }
}
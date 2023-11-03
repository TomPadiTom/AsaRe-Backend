package com.padillatom.asadoremotobackend.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition
public class SpringdocConfig {

    @Bean
    public OpenAPI baseOPenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Asado Remoto")
                        .version("1.0.0")
                        .description("Backend Rest API para Asado Remoto App.")
                );
    }
}

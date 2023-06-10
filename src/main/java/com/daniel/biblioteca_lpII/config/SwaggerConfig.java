package com.daniel.biblioteca_lpII.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI()
                .info(new Info()
                        .title("DOCUMENTACION API REST BIBLITECA")
                        .version("En proceso...")
                        .termsOfService("https://springdoc.org/")
                        .description("Trabajo backend proyecto LPII"));
    }

}

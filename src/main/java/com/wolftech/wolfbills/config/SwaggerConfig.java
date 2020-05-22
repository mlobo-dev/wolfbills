package com.wolftech.wolfbills.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.wolftech.wolfbills.controller"))
                .paths(PathSelectors.any())
                .build()
                .groupName("Wolf Tech Masters")
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "WOLFBILLS API",
                "Esta API é utilizada para controlar as requisições REST do  Wolfbills",
                "Versão 1.0",
                "https://github.com/mlobo-dev/wolfbills",
                new Contact("Márcio Lôbo ", "https://github.com/mlobo-dev/wolfbills", "mlobo.dev@gmail.com"),
                "Permitido para utilização em estudos, compartilhe :)",
                "https://github.com/mlobo-dev/wolfbills",
                Collections.emptyList() // Vendor Extensions
        );
    }
}
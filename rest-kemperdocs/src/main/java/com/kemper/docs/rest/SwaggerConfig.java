package com.kemper.docs.rest;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select().apis(RequestHandlerSelectors.basePackage("com.kemper.docs.rest.controller"))
                .paths(regex("/api/v1.*"))
                .build()
                .apiInfo(metaData());
             
    }
    
    private ApiInfo metaData() {
    	ApiInfo apiInfo = new ApiInfoBuilder()
                .title("Kemper Docs REST API")
                .description("REST API for Kemper Docs POC")
                .version("1.0")
                .contact(new Contact("Rodney Odvina", "", "rodvina@kemper.com"))
                .build();
        return apiInfo;
    }
}

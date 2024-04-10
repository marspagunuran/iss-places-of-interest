package org.mars.issplacesofinterest.config;

import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


/**
 * Swagger Configuration
 *
 * @author Marcelino Pagunuran
 *
 */
@Configuration
public class SwaggerConfig {


    private ApiInfo apiInfo() {
        return new ApiInfo(
                        "ISS Places of Interest APIs",
                        "REST Api for Places of Interest",
                        "1.0", 
                        "", 
                        new Contact("Marcelino Pagunuran", null, "pagunuranmarcelino@gmail.com"),
                        "License of API", 
                        "http://unlicense.org", 
                        Collections.emptyList());
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                        .ignoredParameterTypes(Throwable.class, Error.class, StackTraceElement.class)
                        .apiInfo(apiInfo())
                        .select()
                        .apis(RequestHandlerSelectors.basePackage("org.mars.issplacesofinterest.controller"))
                        .paths(PathSelectors.any())
                        .build();

    }

    
}

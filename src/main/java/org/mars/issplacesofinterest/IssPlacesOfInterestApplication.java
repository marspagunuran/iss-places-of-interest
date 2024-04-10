package org.mars.issplacesofinterest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class IssPlacesOfInterestApplication {

    public static void main(String[] args) {
        SpringApplication.run(IssPlacesOfInterestApplication.class, args);
    }

}

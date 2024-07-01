package com.valorant.valorantadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.valorant")
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class ValorantAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(ValorantAdminApplication.class, args);
    }

}

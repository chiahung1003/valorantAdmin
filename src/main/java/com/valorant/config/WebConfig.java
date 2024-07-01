package com.valorant.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author KenLi
 * @date 2023-07-10
 */
@Configuration
public class WebConfig {
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplateBuilder().build();
    }
}

package com.tripzy.Configurations;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@Configurable
public class AppConfig {

    @Bean
    public WebClient webClient() {
        return WebClient.builder().baseUrl("url").defaultHeader("key", "value").build();
    }
}

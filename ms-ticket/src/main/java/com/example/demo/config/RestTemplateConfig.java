package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
     private static final int CINCO_SEGUNDOS = 5000; // milissegundos

    @Bean
    public ClientHttpRequestFactory clientHttpRequestFactory() {
        SimpleClientHttpRequestFactory clientHttpRequestFactory =
            new SimpleClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(CINCO_SEGUNDOS);
        return clientHttpRequestFactory;
    }

    @Bean
    public RestTemplate restTemplate(
        ClientHttpRequestFactory clientHttpRequestFactory) {
        RestTemplate restTemplate =
            new RestTemplate(clientHttpRequestFactory);
        return restTemplate;
    }
}

package com.example.demo.facade;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dto.UserResponse;

@Service
public class UserFacade {
    @Value("${ms-user.url}")
    private String msUserUrl;

    private final RestTemplate restTemplate;

    public UserFacade(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public UserResponse getUserById(Integer id) {
        String url = msUserUrl + "/api/v1/users/" + id;
        
        try {
            ResponseEntity<UserResponse> response = restTemplate.getForEntity(url, UserResponse.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            } else {
                throw new RuntimeException("Usuário não encontrado no serviço ms-user");
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro na comunicação com ms-user: " + e.getMessage());
        }
    }
}

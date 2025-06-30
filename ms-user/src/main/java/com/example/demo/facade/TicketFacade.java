package com.example.demo.facade;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TicketFacade {
    private final RestTemplate restTemplate;

    @Value("${ms-ticket.url}")
    private String msTicketUrl;

    public TicketFacade(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void criarTicketParaNovoUsuario(Integer userId, String email) {
        String url = msTicketUrl + "/api/v1/tickets";

        Map<String, Object> body = new HashMap<>();
        body.put("action", "CRIAR_EMAIL");
        body.put("object", "Email corporativo");
        body.put("details", "Criar email para o novo usuário: " + email);
        body.put("userId", userId);

        try {
            ResponseEntity<Void> response = restTemplate.postForEntity(url, body, Void.class);
            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new RuntimeException("Falha ao criar ticket no ms-tickets.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao comunicar com ms-tickets: " + e.getMessage(), e);
        }
    }
}

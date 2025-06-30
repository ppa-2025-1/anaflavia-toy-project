package com.example.demo.dto;

import java.util.List;

import jakarta.persistence.criteria.CriteriaBuilder.In;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

// CLASSE (RECORD OU STRUCT) QUE NÃO POSSUI LÓGICA DE NEGÓCIO
// E É USADA PARA "CARREGAR" OU "TRANSFERIR" DADOS ENTRE CAMADAS
// É CHAMADO DTO: 
// Data Transfer Object -> Objeto de Transferência de Dados
public record NewTicket (
        @NotNull(message = "A ação é obrigatória")
        @NotBlank(message = "A ação não pode ser vazia")
        String action,
        @NotNull(message = "O objeto é obrigatório")	
        @NotBlank(message = "O objeto não pode ser vazio")
        String object,
        String details,
        @NotNull(message = "O usuário é obrigatório")
        Integer userId
)  {
    @Override
    public String toString() {
        return "NewTicket [action=" + action + ", object=" + object + ", details=" + details + ", userId=" + userId + "]";
    }
}

package com.example.demo.model.enums;

public enum TicketStatusEnum {
    OPEN("Aberto"),
    IN_PROGRESS("Em Andamento"),
    CANCELLED("Cancelado"),
    RESOLVED("Resolvido");

    private String status;

    TicketStatusEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
    
}

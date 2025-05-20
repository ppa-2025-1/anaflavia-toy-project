package com.example.demo.model.entity;

import com.example.demo.model.enums.TicketStatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tickets")
public class Ticket extends BaseEntity {

    private String action;
    private String object;
    private String details;
    private TicketStatusEnum status;

    @ManyToOne
    @JsonIgnore
    private User user;

    public String getAction() {
        return action;
    }
    public void setAction(String action) {
        this.action = action;
    }
    public String getObject() {
        return object;
    }
    public void setObject(String object) {
        this.object = object;
    }
    public String getDetails() {
        return details;
    }
    public void setDetails(String details) {
        this.details = details;
    }
    public TicketStatusEnum getStatus() {
        return status;
    }
    public void setStatus(TicketStatusEnum status) {
        this.status = status;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    
}

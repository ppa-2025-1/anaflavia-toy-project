package com.example.demo.repository;

import java.util.Optional;
import java.util.Set;

import com.example.demo.model.entity.Ticket;

public interface TicketRepository extends BaseRepository<Ticket, Integer> {
    
    // validar isso aqui
    // Optional <Ticket> findById(Integer id);

    // Set<Ticket> findAll(String status);

    // Ticket createTicket(Ticket ticket);

    // Ticket updateStatusTicket(Ticket ticket);
}

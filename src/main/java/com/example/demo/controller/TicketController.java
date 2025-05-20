package com.example.demo.controller;

import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.NewTicket;
import com.example.demo.model.business.TicketBusiness;
import com.example.demo.model.business.UserBusiness;
import com.example.demo.model.entity.Ticket;
import com.example.demo.repository.TicketRepository;
import com.example.demo.repository.UserRepository;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;


@RestController
@RequestMapping("/api/v1/tickets")
public class TicketController extends AbstractController {

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final TicketBusiness ticketBusiness;

    public TicketController(TicketRepository ticketRepository, TicketBusiness ticketBusiness, UserRepository userRepository) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
        this.ticketBusiness = new TicketBusiness(ticketRepository, userRepository);
    
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createTicket(
        @Valid
        @RequestBody
        NewTicket newTicket) {
        ticketBusiness.criarTicket(newTicket);
    }

    
    // @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    // public ResponseEntity<Ticket> buscarTicket(Integer id) {
    //     return ResponseEntity.ok(ticketRepository.findById(id).orElse(null));
    // }

    
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Ticket>> getTickets() {
        return ResponseEntity.ok(ticketRepository.findAll());
    }

    
    
}

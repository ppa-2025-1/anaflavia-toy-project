package com.example.demo.controller;

import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.NewTicket;
import com.example.demo.model.business.TicketBusiness;
import com.example.demo.model.business.UserBusiness;
import com.example.demo.model.entity.Ticket;
import com.example.demo.model.enums.TicketStatusEnum;
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

    @GetMapping(value = "/buscar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ticket> buscarTicket(@PathVariable("id") Integer id) {
        System.out.println("=============> ID" + id);
        return ticketRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping(value = "/mudar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<Ticket> alterarStatusTicket(
        @PathVariable("id") Integer id,
        @RequestParam("status") String status) {

    TicketStatusEnum novoStatus;
    try {
        novoStatus = TicketStatusEnum.valueOf(status.toUpperCase());
    } catch (IllegalArgumentException e) {
        return ResponseEntity.badRequest().build();
    }

    try {
        Ticket ticketAtualizado = ticketBusiness.atualizarStatusTicket(id, novoStatus);
        return ResponseEntity.ok(ticketAtualizado);
    } catch (IllegalArgumentException e) {
        return ResponseEntity.notFound().build();
    }
}

    
    
}

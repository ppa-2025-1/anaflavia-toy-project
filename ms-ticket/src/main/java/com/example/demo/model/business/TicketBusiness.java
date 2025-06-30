package com.example.demo.model.business;

import java.util.List;
import java.util.Set;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.dto.NewTicket;
import com.example.demo.dto.UserResponse;
import com.example.demo.facade.UserFacade;
import com.example.demo.model.entity.Ticket;
import com.example.demo.model.enums.TicketStatusEnum;
import com.example.demo.repository.TicketRepository;

@Business
public class TicketBusiness {

    private TicketRepository ticketRepository;
    private final UserFacade userFacade;


    public TicketBusiness(TicketRepository ticketRepository, UserFacade userFacade) {
        this.ticketRepository = ticketRepository;
        this.userFacade = userFacade;
    }

    public Ticket criarTicket(NewTicket newTicket) {

        UserResponse user = userFacade.getUserById(newTicket.userId());
        if (user == null) {
            throw new RuntimeException("Usuário não encontrado");
        }

        System.out.println("new ticket: " + newTicket.toString());
        Ticket ticket = new Ticket();
        ticket.setAction(newTicket.action());
        ticket.setObject(newTicket.object());
        ticket.setDetails(newTicket.details());
        ticket.setStatus(TicketStatusEnum.OPEN);
        ticket.setUserId(user.getId());
        // User user = userRepository.findById(newTicket.userId()).orElseThrow(() -> new IllegalArgumentException("User not found"));
        // System.out.println("user: " + user.toString());
        // ticket.setUser(user);

        return ticketRepository.save(ticket);
    }

    public Ticket atualizarStatusTicket(Integer id, TicketStatusEnum novoStatus) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ticket não encontrado"));

        ticket.setStatus(novoStatus);
        ticketRepository.save(ticket);

        return ticket;
    }

    public Ticket buscarTicket(Integer id) {
        return ticketRepository.findById(id).orElse(null);
    }

}

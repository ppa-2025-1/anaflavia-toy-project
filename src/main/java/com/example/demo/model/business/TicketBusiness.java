package com.example.demo.model.business;

import java.util.List;
import java.util.Set;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.dto.NewTicket;
import com.example.demo.model.entity.Ticket;
import com.example.demo.model.entity.User;
import com.example.demo.model.enums.TicketStatusEnum;
import com.example.demo.repository.TicketRepository;
import com.example.demo.repository.UserRepository;

@Business
public class TicketBusiness {

    private TicketRepository ticketRepository;
    private UserRepository userRepository;


    public TicketBusiness(TicketRepository ticketRepository, UserRepository userRepository) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
    }

    public void criarTicket(NewTicket newTicket) {

        System.out.println("CHEGUEI AQUI!!!!!!!!");
        System.out.println("new ticket: " + newTicket.toString());
        Ticket ticket = new Ticket();
        ticket.setAction(newTicket.action());
        ticket.setObject(newTicket.object());
        ticket.setDetails(newTicket.details());
        ticket.setStatus(TicketStatusEnum.OPEN);
        User user = userRepository.findById(newTicket.userId()).orElseThrow(() -> new IllegalArgumentException("User not found"));
        System.out.println("user: " + user.toString());
        ticket.setUser(user);

        ticketRepository.save(ticket);
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

    // public Ticket updateStaTicket(Ticket ticket, TicketStatusEnum status) {
    //     // Implementar lógica de atualização de status do ticket
    //     return ticketRepository.updateStatusTicket(ticket, status);
    // }


    
}

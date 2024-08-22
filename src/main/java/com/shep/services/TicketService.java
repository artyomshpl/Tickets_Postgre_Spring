package com.shep.services;

import com.shep.DTO.TicketRequestDTO;
import com.shep.entities.Ticket;
import com.shep.entities.User;
import com.shep.repositories.TicketRepository;
import com.shep.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;

    public Ticket createTicket(TicketRequestDTO ticketRequest) {
        User user = userRepository.findById(ticketRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setTicketClass(ticketRequest.getTicketClass());
        ticket.setTicketType(ticketRequest.getTicketType());
        ticket.setStartDate(ticketRequest.getStartDate());
        ticket.setPrice(ticketRequest.getPrice());

        return ticketRepository.save(ticket);
    }
}

package com.shep.services;

import com.shep.entities.Ticket;
import com.shep.entities.User;
import com.shep.repositories.TicketRepository;
import com.shep.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserTicketService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Value("${app.update.user.and.create.ticket}")
    private String updateUserAndCreateTicket;

    @Transactional
    public void updateUserAndCreateTicket(Long userId, String newStatus, Ticket newTicket) {
        if ("OFF".equalsIgnoreCase(updateUserAndCreateTicket)) {
            throw new RuntimeException("Updating user and creating ticket is turned off.");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setStatus(newStatus);
        userRepository.save(user);

        newTicket.setUser(user);
        ticketRepository.save(newTicket);
    }
}

package com.shep.controllers;

import com.shep.DTO.TicketRequestDTO;
import com.shep.entities.Ticket;
import com.shep.entities.TicketData;
import com.shep.entities.User;
import com.shep.repositories.TicketDataRepository;
import com.shep.repositories.TicketRepository;
import com.shep.repositories.UserRepository;
import com.shep.services.TicketDataService;
import com.shep.services.TicketService;
import com.shep.services.UserTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserTicketController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserTicketService userTicketService;

    @Autowired
    private TicketDataService ticketDataService;
    @Autowired
    private TicketDataRepository ticketDataRepository;
    @Autowired
    private TicketService ticketService;

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping("/users/{id}/tickets")
    public List<Ticket> getTicketsByUser(@PathVariable Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return user.getTickets();
    }

    @PostMapping("/tickets")
    public Ticket createTicket(@RequestBody TicketRequestDTO ticketRequest) {
        return ticketService.createTicket(ticketRequest);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @GetMapping("/tickets/{id}")
    public Ticket getTicketById(@PathVariable Long id) {
        return ticketRepository.findById(id).orElseThrow(() -> new RuntimeException("Ticket not found"));
    }

    @PostMapping("/updateUserAndCreateTicket")
    public void updateUserAndCreateTicket(@RequestParam Long userId, @RequestParam String newStatus, @RequestBody Ticket newTicket) {
        userTicketService.updateUserAndCreateTicket(userId, newStatus, newTicket);
    }

    @PostMapping("/loadTicketData")
    public void loadTicketData() {
        ticketDataService.loadTicketData();
    }

    @GetMapping("/TicketData")
    public List<TicketData> getTicketData() {
        return ticketDataRepository.findAll();
    }
}

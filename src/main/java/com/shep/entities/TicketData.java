package com.shep.entities;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "ticket_data")
public class TicketData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ticketClass;
    private String ticketType;
    private LocalDate startDate;
    private BigDecimal price;
}
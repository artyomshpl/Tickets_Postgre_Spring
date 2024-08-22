package com.shep.DTO;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class TicketRequestDTO {
    private Long userId;
    private String ticketClass;
    private String ticketType;
    private LocalDate startDate;
    private BigDecimal price;
}
package com.shep.repositories;

import com.shep.entities.TicketData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketDataRepository extends JpaRepository<TicketData, Long> {
}
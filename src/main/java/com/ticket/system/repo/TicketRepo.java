package com.ticket.system.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ticket.system.model.Ticket;

@Repository
public interface TicketRepo extends JpaRepository<Ticket, Long>{

}

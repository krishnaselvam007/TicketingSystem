package com.ticket.system.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ticket.system.model.TicketResponse;

@Repository
public interface ResponseRepo extends JpaRepository<TicketResponse, Long>{

}

package com.ticket.system.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ticket.system.model.Agent;

@Repository
public interface AgentRepo extends JpaRepository<Agent, Long>{

}

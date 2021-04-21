package com.ticket.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticket.system.repo.AgentRepo;

@Service
public class AgentService {

	@Autowired
	AgentRepo agentRepo;
	
	public void assignTicketEqual() {
		
	}
}

package com.ticket.system.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticket.system.config.mailSender;
import com.ticket.system.model.Status;
import com.ticket.system.model.Ticket;
import com.ticket.system.model.TicketResponse;
import com.ticket.system.model.User;
import com.ticket.system.repo.ResponseRepo;
import com.ticket.system.repo.TicketRepo;
import com.ticket.system.repo.UserRepo;

@Service
public class TicketService {
	
	@Autowired
	TicketRepo ticketRepo;
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	ResponseRepo responseRepo;
	
	@Autowired
	mailSender mailer;
	
	public void saveTicket(Ticket ticket) {
		ticketRepo.save(ticket);
	}
	public List<Ticket> getAllTickets() {
		List<Ticket> tickets = new ArrayList<>();
		tickets = ticketRepo.findAll();
		return tickets;
	}
	
	public List<Ticket> getTicketByAgent(Long agentId) {
		List<Ticket> tickets = new ArrayList<>();
		tickets = ticketRepo.findAll().stream().filter(t -> t.getAgentId() == agentId).
				collect(Collectors.toList());
		return tickets;
	}
	
	public List<Ticket> getTicketByUser(Long userId) {
		List<Ticket> tickets = new ArrayList<>();
		tickets = ticketRepo.findAll().stream().filter(t -> t.getUserId() == userId).
				collect(Collectors.toList());
		return tickets;
	}
	
	public List<Ticket> getTicketByStatus(String status) {
		List<Ticket> tickets = new ArrayList<>();
		tickets = ticketRepo.findAll().stream().filter(t -> t.getStatus().label.equalsIgnoreCase(Status.valueOf(status).label))
				.collect(Collectors.toList());
		return tickets;
	}
	
	public Optional<Ticket> getTicketById(Long ticketId) {
		Optional<Ticket> ticket = ticketRepo.findById(ticketId);
		return ticket;
	}
	
	public boolean updateTicket(Ticket ticket) {
		try {
			ticketRepo.save(ticket);
			return true;
		} catch (Exception e) {
			return false;			
		}		
	}
	
	public boolean updateTicketStatus(String status, Long ticketId) {
		Optional<Ticket> ticket = getTicketById(ticketId);
		ticket.get().setStatus(Status.valueOf(status));
		ticketRepo.save(ticket.get());
		return false;
	}
	
	public boolean assignTicketAgent(Long agentId, Long ticketId) {
		Optional<Ticket> ticket = getTicketById(ticketId);
		ticket.get().setAgentId(agentId);
		ticketRepo.save(ticket.get());
		return false;
	}
	
	public boolean addTicketResponse(String response, Long ticketId, String status) {
		Optional<Ticket> ticket = getTicketById(ticketId);
		ticket.get().setStatus(Status.valueOf(status));
		ticketRepo.save(ticket.get());
		TicketResponse tResponse = new TicketResponse();
		tResponse.setAgentId(ticket.get().getAgentId());
		tResponse.setTicketId(ticketId);
		tResponse.setCreatedDate(LocalDate.now());
		tResponse.setResponse(response);
		tResponse.setStatus(Status.valueOf(status));
		tResponse.setUpdatedDate(LocalDate.now());
		tResponse.setUserId(ticket.get().getUserId());
		responseRepo.save(tResponse);
		//send mail
		Optional<User> user = userRepo.findById(ticket.get().getUserId());
		mailer.buildEmail(ticketId, user.get().getEmail(), response);
		return false;
	}
	
	public boolean deleteTicket(long ticketId) {
		ticketRepo.deleteById(ticketId);
		return false;
	}
	
	public boolean closeTicket(int noOfDays) {
		try {
			ticketRepo.findAll().stream().filter(t -> t.getStatus().label.equalsIgnoreCase("Resolved") && 
			t.getUpdatedDate().isBefore(LocalDate.now().minusDays(noOfDays))).peek(t -> t.setStatus(Status.C))
			.collect(Collectors.toList());
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}

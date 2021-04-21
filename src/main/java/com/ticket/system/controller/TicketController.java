package com.ticket.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ticket.system.model.Ticket;
import com.ticket.system.service.TicketService;

@RestController
public class TicketController {

	@Autowired
	TicketService ticketService;

	@GetMapping("/getAllTickets")
	public ResponseEntity<List<Ticket>> getAllTickets() {
		return ResponseEntity.ok(ticketService.getAllTickets());
	}

	@PostMapping(value="/createTicket", consumes= MediaType.APPLICATION_JSON_VALUE)
	public void createTicket(@RequestBody Ticket ticket) {
		try {
			ticketService.saveTicket(ticket);
		} catch (Exception e) {
			ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/getTicketsByAgent/{agentId}")
	public ResponseEntity<List<Ticket>> getTicketByAgent(@PathVariable("agentId") Long agentId) {
		return ResponseEntity.ok(ticketService.getTicketByAgent(agentId));
	}

	@GetMapping("/getTicketsByUser/{userId}")
	public ResponseEntity<List<Ticket>> getTicketByUser(@PathVariable("userId") Long userId) {
		return ResponseEntity.ok(ticketService.getTicketByUser(userId));
	}

	@GetMapping("/getTicketsByStatus/{status}")
	public ResponseEntity<List<Ticket>> getTicketByStatus(@PathVariable("status") String status) {
		return ResponseEntity.ok(ticketService.getTicketByStatus(status));
	}

	@GetMapping("/getTicketById/{ticketId}")
	public ResponseEntity<Ticket> getTicketById(@PathVariable("ticketId") Long ticketId) {
		
		return ResponseEntity.ok(ticketService.getTicketById(ticketId).get());
	}

	@PutMapping(value="/updateTicket", consumes= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> updateTicket(@RequestBody Ticket ticket) {
		try {
			ticketService.updateTicket(ticket);
			return ResponseEntity.ok(true);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PutMapping("/updateTicketStatus/{status}/{ticketId}")
	public ResponseEntity<Boolean> updateTicketStatus(@PathVariable("status") String status, @PathVariable("ticketId") Long ticketId) {
		try {
			ticketService.updateTicketStatus(status, ticketId);
			return ResponseEntity.ok(true);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PutMapping("/assignTicketAgent/{agentId}/{ticketId}")
	public ResponseEntity<Boolean> assignTicketAgent(@PathVariable("agentId") Long agentId, @PathVariable("ticketId") Long ticketId) {
		try {
			ticketService.assignTicketAgent(agentId, ticketId);
			return ResponseEntity.ok(true);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping("/addTicketResponse/{response}/{ticketId}/{status}")
	public ResponseEntity<Boolean> addTicketResponse(@PathVariable("response") String response,@PathVariable("ticketId") Long ticketId, @PathVariable("status") String status) {
		try {
			ticketService.addTicketResponse(response, ticketId, status);
			return ResponseEntity.ok(true);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DeleteMapping("/deleteTicket/{ticketId}")
	public ResponseEntity<Boolean> deleteTicket(@PathVariable("ticketId") long ticketId) {
		try {
			ticketService.deleteTicket(ticketId);
			return ResponseEntity.ok(true);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PutMapping("/closeTicket/{days}")
	public ResponseEntity<Boolean> closeTicket(@PathVariable("days") int days) {
		try {
			ticketService.closeTicket(days);
			return ResponseEntity.ok(true);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}

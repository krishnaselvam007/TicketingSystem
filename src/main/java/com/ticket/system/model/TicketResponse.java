package com.ticket.system.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketResponse {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long responseId;
	
	private Long ticketId;
	
	private Long agentId;
	
	private Long userId;
	
	private Status status;
	
	private String response;
	
	private LocalDate createdDate;
	
	private LocalDate updatedDate;
}

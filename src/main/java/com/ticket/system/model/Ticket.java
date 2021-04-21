package com.ticket.system.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Ticket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ticketId;
	
	private String tiketType;
	
	private String description;
	
	private String title;
	
	private String createdBy;

	private long userId;
	
	private long agentId;
	
	private int priority;
	
	private Status status;
	
	private Date createdDate;
	
	private LocalDate updatedDate;

}

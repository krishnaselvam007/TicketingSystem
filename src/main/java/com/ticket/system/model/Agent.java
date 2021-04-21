package com.ticket.system.model;

import javax.persistence.Column;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Agent {

	@Id
	private long agentId;
	
	@Column
	private String name;
	
	@Column
	private String userName;
	
	@Column
	private String password;
	
	@Column
	@NonNull
	private String email;
	
	@Column
	private long mobileNo;
}

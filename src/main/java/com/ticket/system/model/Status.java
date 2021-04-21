package com.ticket.system.model;

public enum Status {
	
	O("open"),
	W("waiting on customer"),
	U("customer responded"),
	R("resolved"),
	C("closed");
	
	public final String label;

    private Status(String label) {
        this.label = label;
    }

}

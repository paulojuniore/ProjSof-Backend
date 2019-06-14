package com.backend.psoft.exception;

import java.util.Date;

public class CustomRestError {
	
	private Date date;
	private String message;
	private String description;
	
	public CustomRestError() {
		
	}

	public CustomRestError(Date date, String message, String description) {
		this.date = date;
		this.message = message;
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}

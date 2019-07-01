package com.backend.psoft.exception.users;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NonExistentUserException extends RuntimeException {
	
	public NonExistentUserException(String msg) {
		super(msg);
	}
	
}

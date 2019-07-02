package com.backend.psoft.exception.users;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.FOUND)
public class UserAlreadyExistsException extends RuntimeException {
	
	public UserAlreadyExistsException(String msg) {
		super(msg);
	}

}

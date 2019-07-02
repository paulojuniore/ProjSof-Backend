package com.backend.psoft.exception.login;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidPasswordException extends RuntimeException {
	
	public InvalidPasswordException(String msg) {
		super(msg);
	}
	
}

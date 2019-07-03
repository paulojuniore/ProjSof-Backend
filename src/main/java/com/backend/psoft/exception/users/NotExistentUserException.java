package com.backend.psoft.exception.users;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotExistentUserException extends RuntimeException {
	
	public NotExistentUserException(String msg) {
		super(msg);
	}
	
}

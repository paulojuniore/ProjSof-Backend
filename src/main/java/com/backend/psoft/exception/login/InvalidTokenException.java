package com.backend.psoft.exception.login;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.GATEWAY_TIMEOUT)
public class InvalidTokenException extends RuntimeException {
	
	public InvalidTokenException(String msg) {
		super(msg);
	}

}

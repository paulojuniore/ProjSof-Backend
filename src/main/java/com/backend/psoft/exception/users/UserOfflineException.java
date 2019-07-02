package com.backend.psoft.exception.users;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.LOCKED)
public class UserOfflineException extends RuntimeException {
	
	public UserOfflineException(String msg) {
		super(msg);
	}

}

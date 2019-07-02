package com.backend.psoft.exception.subjects;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NonExistentDisciplineException extends RuntimeException {
	
	public NonExistentDisciplineException(String msg) {
		super(msg);
	}

}

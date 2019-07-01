package com.backend.psoft.exception.subjects;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FOUND)
public class ExistingDisciplineException extends RuntimeException {
	
	public ExistingDisciplineException(String msg) {
		super(msg);
	}
	
}

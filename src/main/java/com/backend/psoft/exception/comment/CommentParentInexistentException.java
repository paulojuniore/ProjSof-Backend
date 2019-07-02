package com.backend.psoft.exception.comment;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.NOT_FOUND)
public class CommentParentInexistentException extends RuntimeException {
	
	public CommentParentInexistentException(String msg) {
		super(msg);
	}

}

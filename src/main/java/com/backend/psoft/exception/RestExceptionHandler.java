package com.backend.psoft.exception;

import java.util.Date;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class RestExceptionHandler {
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<CustomRestError> handleAnyException(Exception ex, WebRequest request) {
	    CustomRestError errorMessage = new CustomRestError(new Date(), ex.getMessage(), request.getDescription(false));
	    return new ResponseEntity<CustomRestError>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler({ UserNotFoundException.class })
    public ResponseEntity<CustomRestError> notFound(Exception ex, WebRequest request) {
    	CustomRestError errorMessage = new CustomRestError(new Date(), ex.getMessage(), request.getDescription(false));
    	return new ResponseEntity<CustomRestError>(errorMessage, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

}

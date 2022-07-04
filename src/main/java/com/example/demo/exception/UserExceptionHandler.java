package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.example.demo.entity.ErrorResponse;

@RestControllerAdvice
public class UserExceptionHandler {
	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<?> handlerNotFoundException(NotFoundException ex,WebRequest rq)
	{
		 ErrorResponse err = new ErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage(),404);
	        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
	    
	}
	
	@ExceptionHandler(DuplicateRecordException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<?> handlerDumplicateException(DuplicateRecordException ex,WebRequest rq)
	{
		 ErrorResponse err = new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(),400);
	        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	    
	}
	

	@ExceptionHandler(InternalServerException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<?> handlerInternalException(InternalServerException ex,WebRequest rq)
	{
		 ErrorResponse err = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(),500);
	        return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
	    
	}
}

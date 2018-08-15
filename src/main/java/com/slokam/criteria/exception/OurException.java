package com.slokam.criteria.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class OurException {

	@ExceptionHandler(FileGeneratorException.class)
	public ResponseEntity<String> getOurException(FileGeneratorException e)
	{  ResponseEntity<String> re 
				 =new ResponseEntity<String>(e.getMessage()+":"+e.getCause().getMessage(),HttpStatus.SERVICE_UNAVAILABLE);
		
		return re;
	}

	@ExceptionHandler(ApplicationMessageException.class)
	public ResponseEntity<String> getOurException(ApplicationMessageException e)
	{  ResponseEntity<String> re 
				 =new ResponseEntity<String>(e.getMessage()+":"+e.getCause().getMessage(),HttpStatus.BAD_REQUEST);
		
		return re;
	}
}

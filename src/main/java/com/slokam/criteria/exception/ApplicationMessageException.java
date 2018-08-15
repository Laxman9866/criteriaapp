package com.slokam.criteria.exception;

public class ApplicationMessageException extends Exception {
	public ApplicationMessageException(String message,Throwable cause){
		super(message, cause);
	}
	public ApplicationMessageException(String message){
		super(message);
	}
	public ApplicationMessageException(){
		super("ApplicationMessageException");
	}
}

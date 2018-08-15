package com.slokam.criteria.exception;

public class FileGeneratorException  extends Exception{

	public FileGeneratorException(String message, Throwable t){
		super(message,t);
	}

	public FileGeneratorException(String message){
		super(message);
	}
	public FileGeneratorException(){
		super("FileGeneratorException");
	}
}

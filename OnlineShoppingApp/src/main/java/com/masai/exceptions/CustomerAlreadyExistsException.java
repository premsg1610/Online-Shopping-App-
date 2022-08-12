package com.masai.exceptions;

public class CustomerAlreadyExistsException extends RuntimeException{

	public CustomerAlreadyExistsException() {
	}
	
	public CustomerAlreadyExistsException(String message) {
		super(message);
	}
	
}

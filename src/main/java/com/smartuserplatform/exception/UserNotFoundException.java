package com.smartuserplatform.exception;

public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 2323591222518042154L;

	public UserNotFoundException(String message) {
		super(message);
	}
	
}
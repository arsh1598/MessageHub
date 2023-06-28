package com.github.messagehub.exceptions;

public class UserDoesNotExistException extends RuntimeException {

	private static final long serialVersionUID = 757910873259014063L;
	static String message = "User with '%s' phone number does not exist";
	static String altMessage = "User does not exist";

	public UserDoesNotExistException(String phone) {
		super(String.format(message, phone));
	}

	public UserDoesNotExistException() {
		super(altMessage);
	}
}

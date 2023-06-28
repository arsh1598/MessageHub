package com.github.messagehub.exceptions;

public class UserAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = -6815934691226560025L;

	static String message = "User with '%s' phone number already exists";

	public UserAlreadyExistsException(String phone) {
		super(String.format(message, phone));
	}

}

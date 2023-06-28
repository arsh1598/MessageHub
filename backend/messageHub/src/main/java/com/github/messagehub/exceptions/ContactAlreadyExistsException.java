package com.github.messagehub.exceptions;

public class ContactAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = -6158840690512030407L;
	static String message = "This contact already exist";

	public ContactAlreadyExistsException() {
		super(message);
	}
}

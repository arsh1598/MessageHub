package com.github.messagehub.exceptions;

public class MessageNotAllowedException extends RuntimeException {

	private static final long serialVersionUID = 757910873259014063L;
	static String message = "User cannot send message to self";

	public MessageNotAllowedException() {
		super(message);
	}
	
	public MessageNotAllowedException(String altMessage) {
		super(altMessage);
	}

}

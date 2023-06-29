package com.github.messagehub.exceptions;

public class InvalidCredentialsException extends RuntimeException{
	
	private static final long serialVersionUID = 6092801925634700614L;
	private static String message = "Invalid Credentials";
	
	public InvalidCredentialsException(){
		super(message);
	}

}

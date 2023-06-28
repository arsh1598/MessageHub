package com.github.messagehub.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandlers {

	@ExceptionHandler(UserAlreadyExistsException.class)
	@ResponseBody
	public ResponseEntity<String> handleUserAlreadyExistsException(UserAlreadyExistsException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UserDoesNotExistException.class)
	@ResponseBody
	public ResponseEntity<String> handleUserDoesNotExistException(UserDoesNotExistException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
}

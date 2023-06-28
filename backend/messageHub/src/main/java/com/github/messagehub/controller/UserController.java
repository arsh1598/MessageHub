package com.github.messagehub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.messagehub.dto.LoginUserDTO;
import com.github.messagehub.exceptions.UserAlreadyExistsException;
import com.github.messagehub.model.User;
import com.github.messagehub.service.UserService;

@RestController
@RequestMapping("/messagehub/rest/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<List<User>> getAllUsers() {
		return new ResponseEntity<List<User>>((List<User>) this.userService.getAllUsers(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getUser(@PathVariable int id) {
		return new ResponseEntity<User>(this.userService.getUser(id), HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<User> createUser(@RequestBody User request) throws UserAlreadyExistsException {
		return new ResponseEntity<User>(this.userService.createUser(request), HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody LoginUserDTO request) {
		return new ResponseEntity<User>(this.userService.loginUser(request), HttpStatus.OK);
	}

}

package com.github.messagehub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/messagehub")
public class AppController {

	@GetMapping()
	public String login() {
		return "forward:/index.html"; // Forward to the React app's main HTML file
	}

	@GetMapping("/contacts")
	public String getContacts() {
		return "forward:/index.html"; // Forward to the React app's main HTML file
	}
}
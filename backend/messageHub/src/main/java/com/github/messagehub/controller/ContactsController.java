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

import com.github.messagehub.dto.AddContactDTO;
import com.github.messagehub.exceptions.ContactAlreadyExistsException;
import com.github.messagehub.model.Contacts;
import com.github.messagehub.model.User;
import com.github.messagehub.service.ContactService;

@RestController
@RequestMapping("/messagehub/rest/contacts")
public class ContactsController {

	@Autowired
	ContactService contactService;

	@PostMapping("/create")
	public ResponseEntity<Contacts> createContact(@RequestBody AddContactDTO request)
			throws ContactAlreadyExistsException {
		return new ResponseEntity<Contacts>(this.contactService.createContact(request), HttpStatus.OK);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<List<User>> getContacts(@PathVariable int userId) {
		return new ResponseEntity<>(this.contactService.getContacts(userId), HttpStatus.OK);

	}
}

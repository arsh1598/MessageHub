package com.github.messagehub.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.messagehub.dto.AddContactDTO;
import com.github.messagehub.model.Contacts;
import com.github.messagehub.model.User;
import com.github.messagehub.repository.ContactRepository;

@Service
public class ContactService {
	@Autowired
	private ContactRepository contactRepository;

	@Autowired
	private UserService userService;

	public Contacts createContact(AddContactDTO dto) {
		User user = this.userService.getUser(dto.getPhone());
		Contacts contact = new Contacts(dto.getUserId(), user.getId());
		this.contactRepository.save(contact);
		Contacts contact2 = new Contacts(user.getId(), dto.getUserId());
		this.contactRepository.save(contact2);
		return contact;
	}

	public List<User> getContacts(int id) {
		List<Contacts> contacts = this.contactRepository.findById1(id);
		List<User> users = new ArrayList<>();
		for (Contacts contact : contacts) {
			users.add(this.userService.getUser(contact.getId2()));
		}
		return users;
	}
}

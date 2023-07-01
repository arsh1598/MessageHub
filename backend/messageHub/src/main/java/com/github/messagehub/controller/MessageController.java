package com.github.messagehub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.messagehub.dto.GetMessagesRequestDTO;
import com.github.messagehub.model.Message;
import com.github.messagehub.service.MessageService;

@RestController
@RequestMapping("/messagehub/rest/messages")
public class MessageController {

	@Autowired
	private MessageService messageService;

	@GetMapping
	public ResponseEntity<List<Message>> getAllMessages() {
		return new ResponseEntity<List<Message>>((List<Message>) this.messageService.getAllMessages(), HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<List<Message>> getMessage(@RequestBody GetMessagesRequestDTO request) {
		return new ResponseEntity<List<Message>>((List<Message>) this.messageService.getMessage(request), HttpStatus.OK);
	}

	@PostMapping("/send")
	public ResponseEntity<Message> sendMessage(@RequestBody Message request) {
		return new ResponseEntity<Message>(this.messageService.sendMessage(request), HttpStatus.OK);
	}
	
	@PostMapping("/sendoffline")
	public ResponseEntity<String> sendMessageOffline(@RequestBody Message request) {
		this.messageService.sendMessageOffline(request);
		return new ResponseEntity<>("sent", HttpStatus.OK);
	}

}

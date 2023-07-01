package com.github.messagehub.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.messagehub.dto.GetMessagesRequestDTO;
import com.github.messagehub.exceptions.MessageNotAllowedException;
import com.github.messagehub.messaging.KafkaProducer;
import com.github.messagehub.model.Message;
import com.github.messagehub.repository.MessageRepository;

@Service
public class MessageService {

	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private KafkaProducer kafkaProducer;

	public List<Message> getAllMessages() {
		return (List<Message>) this.messageRepository.findAll();
	}

	public List<Message> getMessage(GetMessagesRequestDTO request) {
		List<Message> result = this.messageRepository.fetchByIdAndSort(request.getSenderId(), request.getReceiverId());
		return result;
	}

	public Message sendMessage(Message request) {
		if(request.getSenderId()==request.getReceiverId()) {
			throw new MessageNotAllowedException();
		}
		if(request.getSenderId()==0 || request.getReceiverId()==0) {
			throw new MessageNotAllowedException("Wrong Request body");
		}
		Message message = new Message();
		message.setSenderId(request.getSenderId());
		message.setReceiverId(request.getReceiverId());
		message.setMessageBody(request.getMessageBody());
		message.setTimestamp(new Date());
		return this.messageRepository.save(message);
	}
	
	public void sendMessageOffline(Message request) {
		if(request.getSenderId()==request.getReceiverId()) {
			throw new MessageNotAllowedException();
		}
		if(request.getSenderId()==0 || request.getReceiverId()==0) {
			throw new MessageNotAllowedException("Wrong Request body");
		}
		Message message = new Message();
		message.setSenderId(request.getSenderId());
		message.setReceiverId(request.getReceiverId());
		message.setMessageBody(request.getMessageBody());
		message.setTimestamp(new Date());
		kafkaProducer.sendMessage("messagehub", message);
	}
}

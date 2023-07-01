package com.github.messagehub.messaging;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.github.messagehub.model.Message;
import com.github.messagehub.repository.MessageRepository;

@Component
public class KafkaMessageListener {
	
	@Autowired
	private MessageRepository messageRepository;
	
    @KafkaListener(topics = "messagehub", groupId="consumergroup1", containerFactory="messageKafkaListenerContainerFactory")
    public void listen(Message record) {
    	this.messageRepository.save(record);
    	System.out.println("Consumed");
    }
    
}
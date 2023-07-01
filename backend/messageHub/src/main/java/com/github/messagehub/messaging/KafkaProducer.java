package com.github.messagehub.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.github.messagehub.model.Message;

@Component
public class KafkaProducer {

    private final KafkaTemplate<String, Message> kafkaTemplate;

    @Autowired
    public KafkaProducer(KafkaTemplate<String, Message> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic, Message message) {
        kafkaTemplate.send(topic, message);
    }
}

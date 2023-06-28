package com.github.messagehub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.github.messagehub.model.Message;

@Repository
public interface MessageRepository extends CrudRepository<Message, Integer> {

	List<Message> findBySenderId(int id);

	List<Message> findByReceiverId(int id);
	
	@Query("SELECT message FROM Message message " +
            "WHERE (message.senderId = :senderId AND message.receiverId = :receiverId)" +
			"OR (message.senderId = :receiverId AND message.receiverId = :senderId) " +
            "ORDER BY message.timestamp ASC")
    List<Message> fetchByIdAndSort( int senderId, int receiverId);
}

package com.github.messagehub.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.github.messagehub.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	User findByPhone(String phone);
}

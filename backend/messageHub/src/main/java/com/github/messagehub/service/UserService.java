package com.github.messagehub.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.messagehub.dto.LoginUserDTO;
import com.github.messagehub.exceptions.UserAlreadyExistsException;
import com.github.messagehub.exceptions.UserDoesNotExistException;
import com.github.messagehub.model.User;
import com.github.messagehub.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> getAllUsers() {
		return (List<User>) this.userRepository.findAll();
	}

	public User getUser(int id) {
		User user = this.userRepository.findById(id).orElse(null);
		if (user == null) {
			throw new UserDoesNotExistException();
		}
		return user;
	}

	public User getUser(String phone) {
		User user = this.userRepository.findByPhone(phone);
		if (user == null) {
			throw new UserDoesNotExistException();
		}
		return user;
	}

	public User createUser(User request) throws UserAlreadyExistsException {
		if (this.userRepository.findByPhone(request.getPhone()) != null) {
			throw new UserAlreadyExistsException(request.getPhone());
		}
		User user = new User();
		user.setName(request.getName());
		user.setPhone(request.getPhone());
		user.setPassword(request.getPassword());
		System.out.println(user.getName());
		return this.userRepository.save(user);
	}

	public User loginUser(LoginUserDTO request) throws UserDoesNotExistException {
		User user = this.userRepository.findByPhone(request.getPhone());
		if (user == null) {
			throw new UserDoesNotExistException(request.getPhone());
		}
		return user;
	}

}

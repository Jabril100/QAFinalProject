package com.cohort.service;

import java.util.List;

import com.cohort.repository.UserRepository;
import com.cohort.to.User;

public class UserService {

//	public boolean validateUser(String username, String password) throws Exception {
//
//		UserRepository userRepository = new UserRepository();
//
//		boolean validatedUserStatus = userRepository.validateUserCredentials(username, password);
//		
//		return validatedUserStatus;
//	}

	public User validateUser(String username, String password) throws Exception {

		UserRepository userRepository = new UserRepository();

		User user = userRepository.validateUserCredentials(username, password);

		return user;
	}

	public List<User> getAllUsers() throws Exception {

		UserRepository userDao = new UserRepository();

		List<User> users = userDao.getUsers();

		return users;
	}

	public void deleteUser(String username) throws Exception {

		UserRepository userRepository = new UserRepository();

		userRepository.delete(username);

	}

	public void updateUser(String password, String firstName, String lastName, String email, String username)
			throws Exception {
		UserRepository userRepository = new UserRepository();
		userRepository.update(password, firstName, lastName, email, username);
	}

	public User getUser(String username) throws Exception {

		UserRepository userRepository = new UserRepository();

		User user = userRepository.getUser(username);

		return user;
	}
}

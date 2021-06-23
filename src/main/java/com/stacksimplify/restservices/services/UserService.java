package com.stacksimplify.restservices.services;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.exception.UserExistException;
import com.stacksimplify.restservices.exception.UserNotFoundException;
import com.stacksimplify.restservices.repositories.UserRepository;

//service
@Service
public class UserService {
	
	//Autowired the UserRepository
	@Autowired
	private UserRepository userRepository;
	
	//getAllUser Method
	public List<User>getAllUsers(){
		
		return userRepository.findAll();
		
	}
	//createUser method
	public User createUser(User user) throws UserExistException {
		User existingUser = userRepository.findByUsername(user.getUsername());
		if(existingUser != null) {
			throw new UserExistException("User already exist");
		}
		return userRepository.save(user);
	}
	//GetUserById
	public Optional<User> getUserById(Long id) throws UserNotFoundException{
		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent()){
			throw new UserNotFoundException("User is not present on Repository");
		}
		return user;
	}
	//UpdateUserById
	public User updateUserById(Long id, User user) throws UserNotFoundException {
		if (!userRepository.findById(id).isPresent()) {
			throw new UserNotFoundException("User not found in repo");
		}
		user.setId(id);
		return userRepository.save(user);
		
	}
	//deleteByUserId
	public void deleteById(Long id){
		if(!userRepository.findById(id).isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User Not found in user repository enter correct ID");
		}
			userRepository.deleteById(id);
		}
		
	
	//getUsersByUserName
	public User getUserByusername(String username) {
		return userRepository.findByUsername(username);
		
	}
	
	
	
	
}

package com.stacksimplify.restservices.services;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stacksimplify.restservices.entities.User;
import com.stacksimplify.restservices.repositories.UserRepository;

//service
@Service
public class UserService {
	
	//Autowire the UserRepository
	@Autowired
	private UserRepository userRepository;
	
	//getAllUser Method
	public List<User>getAllUsers(){
		
		return userRepository.findAll();
		
	}
	//createUser method
	public User createUser(User user) {
		return userRepository.save(user);
	}
	//GetUserById
	public Optional<User>getUserById(Long id) {
		Optional<User> user = userRepository.findById(id);
		return user;
	}
	//UpdateUserById
	public User updateUserById(Long id, User user) {
		user.setId(id);
		return userRepository.save(user);
		
	}
	//deleteByUserId
	public void deleteById(Long id) {
		if(userRepository.findById(id).isPresent()) {
			userRepository.deleteById(id);
		}
		
	}
	//getUsersByUserName
	public User getUserByusername(String username) {
		return userRepository.findByUsername(username);
		
		
	}
	
	
	
	
}

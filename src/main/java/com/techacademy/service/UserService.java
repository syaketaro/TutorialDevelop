package com.techacademy.service;

import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.techacademy.entity.User;
import com.techacademy.repository.UserRepository;

@Service
public class UserService {
	private final UserRepository userRepository;
	
	public UserService(UserRepository repository) {
		this.userRepository = repository;
	}
	
	public List<User> getUserList() {
		
		return userRepository.findAll();
	}
	
	public User getUser(Integer id) {
		return userRepository.findById(id).get();
	}
	
	@Transactional
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
	@Transactional
	public void  deleteUser(Set<Integer> idck) {
		for(Integer id : idck) {
			userRepository.deleteById(id);
		}
	}
}

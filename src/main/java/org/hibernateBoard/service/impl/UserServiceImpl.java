package org.hibernateBoard.service.impl;

import java.util.List;

import org.hibernateBoard.entity.User;
import org.hibernateBoard.repository.UserRepository;
import org.hibernateBoard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<User> userList() {

		List<User> resultList = userRepository.findAll();
		
		return resultList;
	}
	
	@Override
	public void create(User user) {
		
		userRepository.save(user);
		
	}

}

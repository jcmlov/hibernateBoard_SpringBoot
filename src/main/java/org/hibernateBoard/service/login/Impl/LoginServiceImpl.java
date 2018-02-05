package org.hibernateBoard.service.impl;

import org.hibernateBoard.entity.User;
import org.hibernateBoard.repository.UserRepository;
import org.hibernateBoard.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User findByUserIdAndUserPw(String userId, String userPw) {

		User result = userRepository.findByUserIdAndUserPw(userId, userPw);
				
		return result;
	}
	
	
}

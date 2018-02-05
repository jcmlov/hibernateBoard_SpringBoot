package org.hibernateBoard.service.login.Impl;

import org.hibernateBoard.entity.user.User;
import org.hibernateBoard.repository.user.UserRepository;
import org.hibernateBoard.service.login.LoginService;
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

package org.hibernateBoard.service;

import org.hibernateBoard.entity.User;

public interface LoginService {

	public User findByUserIdAndUserPw(String userId, String userPw);
	
}

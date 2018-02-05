package org.hibernateBoard.service.login;

import org.hibernateBoard.entity.user.User;

public interface LoginService {

	public User findByUserIdAndUserPw(String userId, String userPw);
	
}

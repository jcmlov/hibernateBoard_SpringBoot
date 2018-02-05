package org.hibernateBoard.service.user;

import java.util.List;

import org.hibernateBoard.entity.user.User;

public interface UserService {
	
	public List<User> userList();
	
	public User userDetail(long userNo);
	
	public void create(User user);
	
	public void update(User newUser);
	
}

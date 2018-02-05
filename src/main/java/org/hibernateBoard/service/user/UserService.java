package org.hibernateBoard.service;

import java.util.List;

import org.hibernateBoard.entity.User;

public interface UserService {
	
	public List<User> userList();
	
	public User userDetail(long userNo);
	
	public void create(User user);
	
	public void update(User newUser);
	
}

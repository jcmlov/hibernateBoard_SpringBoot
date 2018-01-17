package org.hibernateBoard.service;

import java.util.List;

import org.hibernateBoard.entity.User;

public interface UserService {
	
	public List<User> userList();
	
	public void create(User user);
}

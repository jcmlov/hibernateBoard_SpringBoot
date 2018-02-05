package org.hibernateBoard.service.user.Impl;

import java.util.List;

import org.hibernateBoard.entity.user.User;
import org.hibernateBoard.repository.user.UserRepository;
import org.hibernateBoard.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<User> userList() {

		List<User> resultList = userRepository.findAll(new Sort(new Order(Direction.DESC, "userNo")));
		
		return resultList;
	}
	
	@Override
	public User userDetail(long userNo) {

		User result = userRepository.findOne(userNo);
				
		return result;
	}
	
	@Override
	public void create(User user) {
		
		userRepository.save(user);
		
	}

	@Override
	public void update(User newUser) {

		User user = userRepository.findOne(newUser.getUserNo());
		user.update(newUser);
		userRepository.save(user);
		
	}

}

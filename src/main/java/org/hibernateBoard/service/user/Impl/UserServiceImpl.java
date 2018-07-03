package org.hibernateBoard.service.user.Impl;

import java.util.List;

import org.hibernateBoard.entity.member.Member;
import org.hibernateBoard.repository.member.MemberRepository;
import org.hibernateBoard.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private MemberRepository userRepository;
	
	@Override
	public List<Member> userList() {

		List<Member> resultList = userRepository.findAll(new Sort(new Order(Direction.DESC, "userNo")));
		
		return resultList;
	}
	
	@Override
	public Member userDetail(long userNo) {

		Member result = userRepository.findOne(userNo);
				
		return result;
	}
	
	@Override
	public Member create(Member user) {
		
		return userRepository.save(user);
		
	}

	@Override
	public void update(Member newUser) {

		Member user = userRepository.findOne(newUser.getUserNo());
		user.update(newUser);
		userRepository.save(user);
		
	}

	@Override
	public Member validateUser(String userEmail) {
		
		return userRepository.findByUserEmail(userEmail);
	}

	@Override
	public boolean userDelete(String userId) {
		
		Member user = userRepository.findByUserId(userId);
		boolean result = false;
		
		if(user != null) {
			userRepository.delete(user);
			result = true;
		}
		
		return result;
		
	}

}

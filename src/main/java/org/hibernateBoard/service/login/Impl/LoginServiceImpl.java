package org.hibernateBoard.service.login.Impl;

import org.hibernateBoard.entity.member.Member;
import org.hibernateBoard.repository.member.MemberRepository;
import org.hibernateBoard.service.login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private MemberRepository userRepository;

	@Override
	public Member findByUserIdAndUserPw(String userId, String userPw) {

		Member result = userRepository.findByUserIdAndUserPw(userId, userPw);
				
		return result;
	}
	
	
}

package org.hibernateBoard.service.login.Impl;

import org.hibernateBoard.entity.member.Member;
import org.hibernateBoard.repository.member.MemberRepository;
import org.hibernateBoard.service.login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private MemberRepository memberRepository;

	@Override
	public Member findByMemberIdAndMemberPw(String memberId, String memberPw) {

		Member result = memberRepository.findByMemberIdAndMemberPw(memberId, memberPw);
				
		return result;
	}
	
	
}

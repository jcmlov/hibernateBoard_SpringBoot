package org.hibernateBoard.service.login;

import org.hibernateBoard.entity.member.Member;

public interface LoginService {

	public Member findByMemberIdAndMemberPw(String memberId, String memberPw);
	
}

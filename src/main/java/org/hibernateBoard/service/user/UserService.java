package org.hibernateBoard.service.user;

import java.util.List;

import org.hibernateBoard.entity.member.Member;

public interface UserService {
	
	public List<Member> userList();
	
	public Member userDetail(long userNo);
	
	public Member create(Member user);
	
	public void update(Member newUser);

	public Member validateUser(String userEmail);

	public boolean userDelete(String userId);
	
}

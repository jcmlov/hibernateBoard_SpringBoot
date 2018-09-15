package org.hibernateBoard.service.member;

import java.util.List;

import org.hibernateBoard.entity.member.Member;
import org.hibernateBoard.entity.member.MemberRole;

public interface MemberService {
	
	public List<Member> memberList();
	
	public Member memberDetail(long memberNo);
	
	public Member create(Member member, MemberRole memberRole);
	
	public void update(Member newMember);

	public Member validateMember(String memberEmail);

	public boolean memberDelete(String memberId);
	
}

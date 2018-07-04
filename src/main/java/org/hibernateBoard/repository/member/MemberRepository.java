package org.hibernateBoard.repository.member;

import java.util.List;

import org.hibernateBoard.entity.member.Member;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, Long> {

	public Member findByMemberIdAndMemberPw(String memberId, String memberPw);
	
	public Member findByMemberEmail(String memberEmail);
	
	public Member findByMemberId(String memberId);

	public List<Member> findAll(Sort sort);
}

package org.hibernateBoard.repository.member;

import java.util.List;

import org.hibernateBoard.entity.member.Member;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, Long> {

	public Member findByUserIdAndUserPw(String userId, String userPw);
	
	public Member findByUserEmail(String userEmail);
	
	public Member findByUserId(String userId);

	public List<Member> findAll(Sort sort);
}

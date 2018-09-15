package org.hibernateBoard.service.member.Impl;

import java.util.List;

import org.hibernateBoard.entity.member.Member;
import org.hibernateBoard.entity.member.MemberRole;
import org.hibernateBoard.repository.member.MemberRepository;
import org.hibernateBoard.repository.member.MemberRoleRepository;
import org.hibernateBoard.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private MemberRoleRepository memberRoleRepository;
	
	@Override
	public List<Member> memberList() {

		List<Member> resultList = memberRepository.findAll(new Sort(new Order(Direction.DESC, "memberNo")));
		
		return resultList;
	}
	
	@Override
	public Member memberDetail(long memberNo) {

		Member result = memberRepository.findOne(memberNo);
				
		return result;
	}
	
	@Override
	public Member create(Member member, MemberRole memberRole) {
		
		Member result = memberRepository.save(member);
		memberRoleRepository.save(memberRole);
		
		return result;
		
	}

	@Override
	public void update(Member newmember) {

		Member member = memberRepository.findOne(newmember.getMemberNo());
		member.update(newmember);
		memberRepository.save(member);
		
	}

	@Override
	public Member validateMember(String memberEmail) {
		
		return memberRepository.findByMemberEmail(memberEmail);
	}

	@Override
	public boolean memberDelete(String memberId) {
		
		Member member = memberRepository.findByMemberId(memberId);
		boolean result = false;
		
		if(member != null) {
			memberRepository.delete(member);
			result = true;
		}
		
		return result;
		
	}

}

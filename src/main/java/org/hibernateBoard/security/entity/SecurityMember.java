package org.hibernateBoard.security.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernateBoard.entity.member.Member;
import org.hibernateBoard.entity.member.MemberRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class SecurityMember extends User {

	private static final String ROLE_PREFIX = "ROLE_";
	private static final long serialVersionUID = 1L;
	private Member member;
	
	public SecurityMember(Member member) {
		super(member.getMemberId(), member.getMemberPw(), makeGrantedAuthority(member.getRoles()));
		this.member = member;
	}
	
	public SecurityMember(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}
	
	private static List<GrantedAuthority> makeGrantedAuthority(List<MemberRole> roles){
		List<GrantedAuthority> list = new ArrayList<>();
		roles.forEach(role -> list.add(new SimpleGrantedAuthority(ROLE_PREFIX + role.getRoleName())));
		return list;
	}
	
	

}

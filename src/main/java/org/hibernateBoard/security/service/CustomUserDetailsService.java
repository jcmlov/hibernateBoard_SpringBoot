package org.hibernateBoard.security.service;

import java.util.Optional;

import org.hibernateBoard.repository.member.MemberRepository;
import org.hibernateBoard.security.entity.SecurityMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private MemberRepository memberRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return
				Optional.ofNullable(memberRepository.findByUserId(username))
				.filter(m -> m!= null)
				.map(m -> new SecurityMember(m)).get();

	}

}

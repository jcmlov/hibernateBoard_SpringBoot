package org.hibernateBoard.security.social;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernateBoard.entity.user.User;
import org.hibernateBoard.entity.user.UserConnection;
import org.hibernateBoard.security.service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class SocialService {
	
	private UserService userService;

    public UsernamePasswordAuthenticationToken doAuthentication(UserConnection userConnection) {

        if (userService.isExistUser(userConnection)) {
            // 기존 회원일 경우에는 데이터베이스에서 조회해서 인증 처리
            final User user = userService.findBySocial(userConnection);
            return setAuthenticationToken(user);
        } else {
            // 새 회원일 경우 회원가입 이후 인증 처리
            final User user = userService.signUp(userConnection);
            return setAuthenticationToken(user);

        }
    }


    private UsernamePasswordAuthenticationToken setAuthenticationToken(Object user) {
        return new UsernamePasswordAuthenticationToken(user, null, getAuthorities("ROLE_USER"));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));
        return authorities;
    }

}

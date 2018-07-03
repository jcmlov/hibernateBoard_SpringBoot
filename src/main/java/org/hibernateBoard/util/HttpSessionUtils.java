package org.hibernateBoard.util;

import javax.servlet.http.HttpSession;

import org.hibernateBoard.entity.member.Member;

public class HttpSessionUtils {

	public static final String USER_SESSION_KEY = "userInfo";
	
	public static boolean isLoginUser(HttpSession session) {
		
		boolean result = false;
		Object sessionedUser = session.getAttribute(USER_SESSION_KEY);
		if(sessionedUser != null) {
			result = true;
		} else {
			result = false;
		}
		
		return result;
	}
	
	public static Member getUserFormSession(HttpSession session) {
		if(!isLoginUser(session)) {
			return null;
		}
		
		return (Member) session.getAttribute(USER_SESSION_KEY);
	}
	
}

package org.hibernateBoard.vo.member;

import org.apache.commons.lang.builder.ToStringBuilder;

public class MemberVO {

	private int userNo;
	private String userId;
	private String userNm;
	private String userEmail;
	private String userPw;

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserNm() {
		return userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	
	public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
	
}

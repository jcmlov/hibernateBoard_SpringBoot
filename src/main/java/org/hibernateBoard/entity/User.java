package org.hibernateBoard.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
@Table(name="user")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long userNo;

	@Column(nullable=false, length=20)
	private String userId;
	@Column(nullable=false, length=20)
	private String userNm;
	@Column(nullable=false, length=100)
	private String userEmail;
	@Column(nullable=false, length=500)
	private String userPw;

	public long getUserNo() {
		return userNo;
	}

	public void setUserNo(long userNo) {
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

	public void update(User newUser) {
		this.userNm = newUser.userNm;
		this.userEmail = newUser.userEmail;
		this.userPw = newUser.userPw;
	}


}
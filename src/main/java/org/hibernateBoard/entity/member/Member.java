package org.hibernateBoard.entity.member;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="member")
public class Member {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long userNo;

	@Column(nullable=false, length=20, unique=true)
	private String userId;
	
	@Column(nullable=false, length=20)
	private String userNm;
	
	@Column(nullable=false, length=100, unique=true)
	private String userEmail;
	
	@Column(nullable=false, length=500)
	private String userPw;
	
	@CreationTimestamp
	private Date registDt;
	
	@UpdateTimestamp
	private Date updateDt;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="userId")
	private List<MemberRole> roles;
	
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

	public Date getRegistDt() {
		return registDt;
	}

	public void setRegistDt(Date registDt) {
		this.registDt = registDt;
	}

	public Date getUpdateDt() {
		return updateDt;
	}

	public void setUpdateDt(Date updateDt) {
		this.updateDt = updateDt;
	}

	public List<MemberRole> getRoles() {
		return roles;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public void update(Member newUser) {
		this.userNm = newUser.userNm;
		this.userEmail = newUser.userEmail;
		this.userPw = newUser.userPw;
		this.updateDt = new Date();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Member other = (Member) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

}

package org.hibernateBoard.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
@TableGenerator(name="USER_NO_SEQ_GENERATOR", table="USER", pkColumnValue="USER_NO_SEQ", allocationSize=1)
//name=식별자 생성기 이름, table=키생성 테이블 이름, pkColumnValue=DB에 등록될 시퀀스이름)
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType. TABLE, generator = "USER_NO_SEQ_GENERATOR")
	private Long userNo;

	@Column(nullable = false, length=20)
	private String userId;
	@Column(nullable = false, length=20)	
	private String userNm;
	@Column(nullable = false, length=100)
	private String userEmail;
	@Column(nullable = false, length=500)
	private String userPw;

	public void setUserNo(Long userNo) {
		this.userNo = userNo;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
	
}

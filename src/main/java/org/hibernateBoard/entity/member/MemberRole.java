package org.hibernateBoard.entity.member;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="memberRole")
public class MemberRole {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long roleNo;

	@Column(nullable=false, length=100, unique=true)
	private String roleName;

	public long getRoleNo() {
		return roleNo;
	}

	public void setRoleNo(long roleNo) {
		this.roleNo = roleNo;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}

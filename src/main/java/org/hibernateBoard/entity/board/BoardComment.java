package org.hibernateBoard.entity.board;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernateBoard.entity.member.Member;

@Entity
@Table(name="boardComment")
public class BoardComment {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long commentNo;
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name="fk_comment_register"))
	private Member register;
	
	@Column(length=20)
	private String updateId;
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name="fk_comment_boardNo"))
	private Board board;
	
	@Lob
	private String comment;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date registDate = new Date();
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;
	
	@Transient
	private boolean equalUserId;
	
	public long getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(long commentNo) {
		this.commentNo = commentNo;
	}

	public Member getRegister() {
		return register;
	}

	public void setRegister(Member register) {
		this.register = register;
	}

	public String getUpdateId() {
		return updateId;
	}

	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getRegistDate() {
		return registDate;
	}

	public void setRegistDate(Date registDate) {
		this.registDate = registDate;
	}
	
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public boolean getEqualUserId() {
		return equalUserId;
	}

	public void setEqualUserId(boolean equalUserId) {
		this.equalUserId = equalUserId;
	}

	public BoardComment() {
	}
	
	public BoardComment(Member register, String comment, Board board) {
		this.register = register;
		this.comment = comment;
		this.board = board;
		this.isEqualRegistId(register);
	}
	
	public void update(String comment, Member loginUser) {
		this.comment = comment;
		this.updateId = loginUser.getUserId();
		this.updateDate = new Date();
	}
	
	public boolean isEqualRegistId(Member userInfo) {
		
		equalUserId = userInfo.getUserId().equals(register.getUserId());
		
		return equalUserId;
	}
	
	public String getFormattedRegistDate() {
		if(registDate == null) {
			return "";
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String returnFormat = sdf.format(registDate);
		
		return returnFormat;
	}
	
	public String getFormattedUpdateDate() {
		if(updateDate == null) {
			return "";
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String returnFormat = sdf.format(updateDate);
		
		return returnFormat;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (commentNo ^ (commentNo >>> 32));
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
		BoardComment other = (BoardComment) obj;
		if (commentNo != other.commentNo)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BoardComment [commentNo=" + commentNo + ", register=" + register + ", comment=" + comment
				+ ", registDate=" + registDate + ", hashCode()=" + hashCode() + ", getClass()=" + getClass()
				+ ", toString()=" + super.toString() + "]";
	}

	
	
}

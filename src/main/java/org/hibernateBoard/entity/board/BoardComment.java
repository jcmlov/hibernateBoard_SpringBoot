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

import org.hibernateBoard.entity.user.User;

@Entity
@Table(name="boardComment")
public class BoardComment {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long commentNo;
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name="fk_comment_register"))
	private User register;
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name="fk_comment_boardNo"))
	private Board board;
	
	@Lob
	private String comment;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date registDate = new Date();
	
	public BoardComment() {
	}
	
	public BoardComment(User register, String comment, Board board) {
		this.register = register;
		this.comment = comment;
		this.board = board;
	}
	
	public String getFormattedRegistDate() {
		if(registDate == null) {
			return "";
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String returnFormat = sdf.format(registDate);
		
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

	public long getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(long commentNo) {
		this.commentNo = commentNo;
	}

	public User getRegister() {
		return register;
	}

	public void setRegister(User register) {
		this.register = register;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
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
	
}

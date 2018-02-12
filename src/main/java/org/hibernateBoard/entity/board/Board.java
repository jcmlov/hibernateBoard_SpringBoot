package org.hibernateBoard.entity.board;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="board")
public class Board {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long boardNo;

	@Column(nullable=false, length=20)
	private String boardTitle;
	
	@Column(nullable=false, length=4000)
	private String boardContent;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date registDate = new Date();
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date deleteDate;
	
	@Column(nullable=false, length=20)
	private String registId;
	
	@Column(length=20)
	private String updateId;
	
	@Column(length=20)
	private String deleteId;
	
	@Column(nullable=false, length=1)
	private String deleteYn;

	public long getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(long boardNo) {
		this.boardNo = boardNo;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
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

	public Date getDeleteDate() {
		return deleteDate;
	}

	public void setDeleteDate(Date deleteDate) {
		this.deleteDate = deleteDate;
	}

	public String getRegistId() {
		return registId;
	}

	public void setRegistId(String registId) {
		this.registId = registId;
	}

	public String getUpdateId() {
		return updateId;
	}

	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}

	public String getDeleteId() {
		return deleteId;
	}

	public void setDeleteId(String deleteId) {
		this.deleteId = deleteId;
	}

	public String getDeleteYn() {
		return deleteYn;
	}

	public void setDeleteYn(String deleteYn) {
		this.deleteYn = deleteYn;
	}
	
}

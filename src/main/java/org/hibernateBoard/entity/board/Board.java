package org.hibernateBoard.entity.board;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernateBoard.entity.member.Member;


@Entity
@Table(name="board")
public class Board {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long boardNo;

	@Column(nullable=false, length=20)
	private String boardTitle;
	
	@Column(nullable=false)
	@Lob
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

	@OneToMany(mappedBy="board")
	@OrderBy("registDate desc")
	private List<BoardComment> comments;
	
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

	public List<BoardComment> getComments() {
		return comments;
	}

	public boolean isEqualRegistId(Member memberInfo) {
		return this.registId.equals(memberInfo.getMemberId());
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
	
	public String getFormattedDeleteDate() {
		if(deleteDate == null) {
			return "";
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String returnFormat = sdf.format(deleteDate);
		
		return returnFormat;
	}
	
	public void update(Board newBoard, Member memberInfo) {
		this.boardTitle = newBoard.getBoardTitle();
		this.boardContent = newBoard.getBoardContent();
		this.updateId = memberInfo.getMemberId();
		this.updateDate = new Date();
	}

	public void delete(long boardNo, Member memberInfo) {
		this.deleteId = memberInfo.getMemberId();
		this.deleteDate = new Date();
		this.deleteYn = "Y";
	}
	
	public int commentsSize() {
		
		int totLength = this.comments.size();
		
		return totLength;
	}
	
}

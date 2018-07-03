package org.hibernateBoard.service.board;

import org.hibernateBoard.entity.board.BoardComment;
import org.hibernateBoard.entity.member.Member;

public interface BoardCommetService {

	public void commentRegist(BoardComment boardComment);
	
	public BoardComment commentRegistAjax(BoardComment boardComment);

	public BoardComment boardCommentDetail(long commentNo);

	public BoardComment commentUpdateAjax(long commentNo, String comment, Member loginUser);

	public BoardComment commentDeleteAjax(long commentNo, Member loginUser);

}

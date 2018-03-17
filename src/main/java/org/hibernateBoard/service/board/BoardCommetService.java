package org.hibernateBoard.service.board;

import org.hibernateBoard.entity.board.BoardComment;
import org.hibernateBoard.entity.user.User;

public interface BoardCommetService {

	public void commentRegist(BoardComment boardComment);
	
	public BoardComment commentRegistAjax(BoardComment boardComment);

	public BoardComment boardCommentDetail(long commentNo);

	public BoardComment commentUpdateAjax(long commentNo, String comment, User loginUser);

	public BoardComment commentDeleteAjax(long commentNo, User loginUser);

}

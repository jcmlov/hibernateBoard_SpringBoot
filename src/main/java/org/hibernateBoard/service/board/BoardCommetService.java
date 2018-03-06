package org.hibernateBoard.service.board;

import org.hibernateBoard.entity.board.BoardComment;

public interface BoardCommetService {

	public void commentRegist(BoardComment boardComment);
	
	public BoardComment commentRegistAjax(BoardComment boardComment);

}

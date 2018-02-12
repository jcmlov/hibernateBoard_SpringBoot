package org.hibernateBoard.service.board;

import java.util.List;

import org.hibernateBoard.entity.board.Board;

public interface BoardService {

	public List<Board> boardList();
	
	public void boardRegist(Board board);
}

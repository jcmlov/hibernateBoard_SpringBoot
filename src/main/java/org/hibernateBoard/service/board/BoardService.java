package org.hibernateBoard.service.board;

import java.util.List;

import org.hibernateBoard.entity.board.Board;
import org.hibernateBoard.entity.user.User;

public interface BoardService {

	public List<Board> boardList();
	
	public void boardRegist(Board board);
	
	public Board boardDetail(long boardNo);

	public void boardUpdate(Board newBoard, User userInfo);

	public void boardDelete(long boardNo, User userInfo);
	
}

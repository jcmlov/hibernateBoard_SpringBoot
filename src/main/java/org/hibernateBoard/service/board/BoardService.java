package org.hibernateBoard.service.board;

import java.util.List;

import org.hibernateBoard.entity.board.Board;
import org.hibernateBoard.entity.member.Member;

public interface BoardService {

	public List<Board> boardList();
	
	public void boardRegist(Board board);
	
	public Board boardDetail(long boardNo);

	public void boardUpdate(Board newBoard, Member userInfo);

	public void boardDelete(long boardNo, Member userInfo);
	
}

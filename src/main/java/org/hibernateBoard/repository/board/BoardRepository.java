package org.hibernateBoard.repository.board;

import org.hibernateBoard.entity.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

	public Board findByBoardNo(long boardNo);
}

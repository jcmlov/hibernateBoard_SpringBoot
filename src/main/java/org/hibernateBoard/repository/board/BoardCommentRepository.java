package org.hibernateBoard.repository.board;

import org.hibernateBoard.entity.board.BoardComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardCommentRepository extends JpaRepository<BoardComment, Long> {

}

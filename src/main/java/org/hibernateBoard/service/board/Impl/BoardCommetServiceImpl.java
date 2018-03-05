package org.hibernateBoard.service.board.Impl;

import org.hibernateBoard.entity.board.BoardComment;
import org.hibernateBoard.repository.board.BoardCommentRepository;
import org.hibernateBoard.service.board.BoardCommetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardCommetServiceImpl implements BoardCommetService {

	@Autowired
	private BoardCommentRepository boardCommentRepository;
	
	@Override
	public void commentRegist(BoardComment boardComment) {
		
		boardCommentRepository.save(boardComment);
		
	}

}
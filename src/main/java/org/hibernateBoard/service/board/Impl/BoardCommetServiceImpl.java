package org.hibernateBoard.service.board.Impl;

import org.hibernateBoard.entity.board.BoardComment;
import org.hibernateBoard.entity.member.Member;
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

	@Override
	public BoardComment commentRegistAjax(BoardComment boardComment) {

		BoardComment result = boardCommentRepository.save(boardComment);
		
		return result;
	}

	@Override
	public BoardComment boardCommentDetail(long commentNo) {

		BoardComment result = boardCommentRepository.findOne(commentNo);
				
		return result;
	}

	@Override
	public BoardComment commentUpdateAjax(long commentNo, String comment, Member loginUser) {

		BoardComment boardComment = boardCommentRepository.findOne(commentNo);
		boardComment.update(comment, loginUser);
		
		return boardCommentRepository.save(boardComment);
	}

	@Override
	public BoardComment commentDeleteAjax(long commentNo, Member loginUser) {
		
		boardCommentRepository.delete(commentNo);
		BoardComment result = boardCommentRepository.findOne(commentNo);
		
		return result;
	}

}

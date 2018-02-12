package org.hibernateBoard.service.board.Impl;

import java.util.List;

import org.hibernateBoard.entity.board.Board;
import org.hibernateBoard.repository.board.BoardRepository;
import org.hibernateBoard.service.board.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	@Override
	public List<Board> boardList() {

		List<Board> resultList = boardRepository.findAll(new Sort(new Order(Direction.DESC, "registDate")));
		
		return resultList;
	}

	@Override
	public void boardRegist(Board board) {
		
		boardRepository.save(board);
		
	}

}

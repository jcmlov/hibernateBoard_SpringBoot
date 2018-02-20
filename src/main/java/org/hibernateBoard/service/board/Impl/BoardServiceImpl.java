package org.hibernateBoard.service.board.Impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernateBoard.entity.board.Board;
import org.hibernateBoard.entity.user.User;
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

		List<Board> list = boardRepository.findAll(new Sort(new Order(Direction.DESC, "registDate")));
		
		List<Board> resultList = new ArrayList<Board>();
		
		for(Board board : list) {
			if(board.getDeleteYn().equals("N")) {
				resultList.add(board);
			}
		}
		
		return resultList;
	}

	@Override
	public void boardRegist(Board board) {
		
		boardRepository.save(board);
		
	}

	@Override
	public Board boardDetail(long boardNo) {
		
		Board result = boardRepository.findByBoardNo(boardNo);
		
		return result;
	}

	@Override
	public void boardUpdate(Board newBoard, User userInfo) {
		
		Board board = boardRepository.findByBoardNo(newBoard.getBoardNo());
		
		board.update(newBoard, userInfo);
		boardRepository.save(board);
		
	}

	@Override
	public void boardDelete(long boardNo, User userInfo) {

		Board board = boardRepository.findByBoardNo(boardNo);
		board.delete(boardNo, userInfo);
		
		boardRepository.save(board);
	}

}

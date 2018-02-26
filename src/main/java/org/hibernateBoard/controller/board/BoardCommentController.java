package org.hibernateBoard.controller.board;

import javax.servlet.http.HttpSession;

import org.hibernateBoard.entity.board.Board;
import org.hibernateBoard.entity.board.BoardComment;
import org.hibernateBoard.entity.user.User;
import org.hibernateBoard.service.board.BoardCommetService;
import org.hibernateBoard.service.board.BoardService;
import org.hibernateBoard.util.HttpSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/boardComment")
public class BoardCommentController {
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private BoardCommetService boardCommetService;
	
	@PostMapping(value="/boardCommentRegist")
	public String create(Model model, HttpSession session, long boardNo, String comment) {
		if(!HttpSessionUtils.isLoginUser(session)) {
			return "/login/loginForm";
		}
		
		User loginUser = HttpSessionUtils.getUserFormSession(session);
		
		Board board = boardService.boardDetail(boardNo);
		
		BoardComment boardComment = new BoardComment(loginUser, comment, board);
		boardCommetService.commentRegist(boardComment);
		
		return "redirect:/board/boardDetail?boardNo=" + boardNo;
	}

}

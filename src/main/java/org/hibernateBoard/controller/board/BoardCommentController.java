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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	@PostMapping(value="/ajax/boardCommentRegist")
	@ResponseBody
	public BoardComment createAjax(Model model, HttpSession session, long boardNo, String comment) {
		
		if(!HttpSessionUtils.isLoginUser(session)) {
			return null;
		}
		
		User loginUser = HttpSessionUtils.getUserFormSession(session);
		
		Board board = boardService.boardDetail(boardNo);
		BoardComment boardComment = new BoardComment(loginUser, comment, board);
		
		return boardCommetService.commentRegistAjax(boardComment);
	}
	
	@GetMapping(value="/ajax/boardCommentUpdateForm")
	@ResponseBody
	public BoardComment updateFormAjax(Model model, HttpSession session, long commentNo) {
		
		if(!HttpSessionUtils.isLoginUser(session)) {
			return null;
		}
		
		BoardComment boardComment = boardCommetService.boardCommentDetail(commentNo);
		
		return boardComment;
	}
	
}

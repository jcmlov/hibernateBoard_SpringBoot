package org.hibernateBoard.controller.board;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernateBoard.entity.board.Board;
import org.hibernateBoard.entity.board.BoardComment;
import org.hibernateBoard.entity.member.Member;
import org.hibernateBoard.service.board.BoardService;
import org.hibernateBoard.util.HttpSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@GetMapping(value="/boardList")
	public String list(Model model, HttpSession session) {
		
		if(!HttpSessionUtils.isLoginUser(session)) {
			return "redirect:/login/loginForm";
		}
		
		List<Board> boardList = boardService.boardList();
		model.addAttribute("boardList", boardList);
		
		return "/board/boardList";
	}
	
	@GetMapping(value="/boardDetail")
	public String detail(long boardNo, Model model, HttpSession session) {
		
		if(!HttpSessionUtils.isLoginUser(session)) {
			return "redirect:/login/loginForm";
		}
		
		Member userInfo = (Member) HttpSessionUtils.getUserFormSession(session);
		Board board = boardService.boardDetail(boardNo);
		boolean isEqualRegistId = board.isEqualRegistId(userInfo);
		
		for(BoardComment comment : board.getComments()) {
			comment.isEqualRegistId(userInfo);
		}
		
		model.addAttribute("board", board);
		model.addAttribute("isEqualRegistId", isEqualRegistId);
		
		return "/board/boardDetail";
	}
	
	@GetMapping(value="/boardForm")
	public String form(Model model, HttpSession session) {
		
		if(!HttpSessionUtils.isLoginUser(session)) {
			return "redirect:/login/loginForm";
		}
		
		return "/board/boardForm";
	}
	
	@GetMapping(value="/boardUpdateForm")
	public String updateForm(long boardNo, Model model, HttpSession session) {
		
		if(!HttpSessionUtils.isLoginUser(session)) {
			return "redirect:/login/loginForm";
		}
		
		Member userInfo = (Member) HttpSessionUtils.getUserFormSession(session);
		Board board = boardService.boardDetail(boardNo);
		boolean isEqualRegistId = board.isEqualRegistId(userInfo);
		
		model.addAttribute("board", board);
		model.addAttribute("isEqualRegistId", isEqualRegistId);
		
		return "/board/boardUpdateForm";
	}
	
	@PostMapping(value="/boardRegist")
	public String create(Board board, Model model, HttpSession session) {
		
		if(!HttpSessionUtils.isLoginUser(session)) {
			return "redirect:/login/loginForm";
		}
		
		Member userInfo = (Member) HttpSessionUtils.getUserFormSession(session);
		board.setRegistId(userInfo.getUserId());
		board.setDeleteYn("N");
		boardService.boardRegist(board);
		
		return "redirect:/board/boardList";
	}
	
	@PostMapping(value="boardUpdate")
	public String update(Board newBoard, Model model, HttpSession session) {
		
		if(!HttpSessionUtils.isLoginUser(session)) {
			return "redirect:/login/loginForm";
		}
		
		Member userInfo = (Member) HttpSessionUtils.getUserFormSession(session);
		Board board = boardService.boardDetail(newBoard.getBoardNo());
		
		if(!board.isEqualRegistId(userInfo)) {
			model.addAttribute("message", "작성자가 로그인한 사용자가 아닙니다.");
			return "redirect:/board/boardList";
		}
		
		boardService.boardUpdate(newBoard, userInfo);
		
		return "redirect:/board/boardDetail?boardNo=" + newBoard.getBoardNo();
	}
	
	@GetMapping(value="boardDelete")
	public String delete(long boardNo, Model model, HttpSession session) {
		
		if(!HttpSessionUtils.isLoginUser(session)) {
			return "redirect:/login/loginForm";
		}
		
		Member userInfo = (Member) HttpSessionUtils.getUserFormSession(session);
		Board board = boardService.boardDetail(boardNo);
		
		if(!board.isEqualRegistId(userInfo)) {
			model.addAttribute("message", "작성자가 로그인한 사용자가 아닙니다.");
			return "redirect:/board/boardList";
		}
		
		boardService.boardDelete(boardNo, userInfo);
		
		return "redirect:/board/boardList";
	}
	
}

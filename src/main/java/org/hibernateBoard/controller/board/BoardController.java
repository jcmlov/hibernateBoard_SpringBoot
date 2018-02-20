package org.hibernateBoard.controller.board;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernateBoard.entity.board.Board;
import org.hibernateBoard.entity.user.User;
import org.hibernateBoard.service.board.BoardService;
import org.hibernateBoard.util.HttpSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@GetMapping(value="/boardList")
	public String list(Model model, HttpSession session) {
		
		String result = "";
		
		User userInfo = (User) HttpSessionUtils.getUserFormSession(session);
		
		if(userInfo != null) {
			List<Board> boardList = boardService.boardList();
			model.addAttribute("boardList", boardList);
			
			result = "/board/boardList";
		} else {
			result = "redirect:/login/loginForm";
		}
		
		return result;
	}
	
	@GetMapping(value="/boardDetail")
	public String detail(@PathVariable long boardNo, Model model, HttpSession session) {
		
		String result = "";
		
		User userInfo = (User) HttpSessionUtils.getUserFormSession(session);
		
		if(userInfo != null) {
			Board board = boardService.boardDetail(boardNo);
			model.addAttribute("board", board);
			
			result = "/board/boardDetail";
		} else {
			result = "redirect:/login/loginForm";
		}
		
		return result;
	}
	
	@GetMapping(value="/boardForm")
	public String form(Model model, HttpSession session) {
		
		String result = "";
		
		User userInfo = (User) HttpSessionUtils.getUserFormSession(session);
		
		if(userInfo != null) {
			result = "/board/boardForm";
		} else {
			result = "redirect:/login/loginForm";
		}
		
		return result;
	}
	
	@GetMapping(value="/boardUpdateForm")
	public String updateForm(@PathVariable long boardNo, Model model, HttpSession session) {
		
		String result = "";
		
		User userInfo = (User) HttpSessionUtils.getUserFormSession(session);
		
		if(userInfo != null) {
			Board board = boardService.boardDetail(boardNo);
			model.addAttribute("board", board);
			
			result = "/board/boardUpdateForm";
		} else {
			result = "redirect:/login/loginForm";
		}
		
		return result;
	}
	
	@PostMapping(value="/boardRegist")
	public String create(@PathVariable Board board, Model model, HttpSession session) {
		
		String result = "";
		
		User userInfo = (User) HttpSessionUtils.getUserFormSession(session);
		
		if(userInfo != null) {
			board.setRegistId(userInfo.getUserId());
			board.setDeleteYn("N");
			boardService.boardRegist(board);
			result = "redirect:/board/boardList";
		} else {
			result = "redirect:/login/loginForm";
		}
		
		return result;
	}
	
	@PostMapping(value="boardUpdate")
	public String update(@PathVariable Board newBoard, Model model, HttpSession session) {
		
		String result = "";
		
		User userInfo = (User) HttpSessionUtils.getUserFormSession(session);
		
		if(userInfo != null) {
			
			Board board = boardService.boardDetail(newBoard.getBoardNo());
			
			if(!board.isEqualRegistId(userInfo)) {
				model.addAttribute("message", "작성자가 로그인한 사용자가 아닙니다.");
				return "redirect:/board/boardList";
			}
			
			board.update(newBoard, userInfo);
			boardService.boardRegist(newBoard);
			result = "redirect:/board/boardList";
		} else {
			result = "redirect:/login/loginForm";
		}
		
		return result;
	}
	
}

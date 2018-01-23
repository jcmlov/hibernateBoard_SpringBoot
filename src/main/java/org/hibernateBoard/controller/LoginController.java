package org.hibernateBoard.controller;

import javax.servlet.http.HttpSession;

import org.hibernateBoard.entity.User;
import org.hibernateBoard.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/login")
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@GetMapping(value="/loginForm")
	public String loginForm() {
		
		return "/login/loginForm";
	}
	
	@PostMapping(value="/loginAction")
	public String loginAction(HttpSession session, User user, Model model) {
		
		User userInfo = loginService.findByUserIdAndUserPw(user.getUserId(), user.getUserPw());
		
		if(userInfo == null) {
			model.addAttribute("message", "아이디, 비밀번호가 일치하지 않습니다.");
			return "redirect:/login/loginForm";
		}
		
		session.setAttribute("userInfo", userInfo);
		
		return "redirect:/";
	}
	
}

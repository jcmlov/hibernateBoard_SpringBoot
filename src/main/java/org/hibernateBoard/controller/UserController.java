package org.hibernateBoard.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernateBoard.entity.User;
import org.hibernateBoard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping(value="/userList")
	public String list(Model model, HttpSession session) {
		
		String result = "";
		
		Object sessionInfo = session.getAttribute("userInfo");
		
		if(sessionInfo != null) {
			List<User> userList = userService.userList();
			model.addAttribute("userList", userList);
			
			result = "/user/userList";
		} else {
			result = "redirect:/login/loginForm";
		}
		
		return result;
		
	}
	
	@GetMapping(value="/userDetail")
	public String detail(Model model, long userNo, HttpSession session) {
		
		String result = "";
		
		Object sessionInfo = session.getAttribute("userInfo");
		
		if(sessionInfo != null) {
			User user = userService.userDetail(userNo);
			model.addAttribute("user", user);
			
			result = "/user/userDetail";
		} else {
			result = "redirect:/login/loginForm";
		}
		
		return result;
	}
	
	@GetMapping(value="/userForm")
	public String form(Model model) {
		
		return "/user/userForm";
	}
	
	@GetMapping(value="/userUpdateForm")
	public String updateForm(Model model, long userNo, HttpSession session) {
		
		String result = "";
		
		Object sessionInfo = session.getAttribute("userInfo");
		User userInfo = (User) sessionInfo;
		
		if(sessionInfo != null) {
			if(userNo == userInfo.getUserNo()) {
				User user = userService.userDetail(userInfo.getUserNo());
				model.addAttribute("user", user);
				
				result = "/user/userUpdateForm";
			} else {
				result = "redirect:/";
			}
		} else {
			result = "redirect:/login/loginForm";
		}
		
		return result;
	}
	
	
	@PostMapping(value="/userCreate")
	public String create(User user, Model model) {
		
		userService.create(user);
		
		return "redirect:/user/userList";
	}
	
	@PostMapping(value="/userUpdate")
	public String update(User newUser, Model model, HttpSession session) {
		
		String result = "";
		
		Object sessionInfo = session.getAttribute("userInfo");
		User userInfo = (User) sessionInfo;
		
		if(sessionInfo != null) {
			long userNo = newUser.getUserNo();
			
			if(userNo == userInfo.getUserNo()) {
				userService.update(newUser);
				
				result = "redirect:/user/userDetail?userNo=" + userNo;
			} else {
				result = "redirect:/";
			}
		} else {
			result = "redirect:/login/loginForm";
		}
		
		return result;
	}
	
}

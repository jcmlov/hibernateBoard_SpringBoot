package org.hibernateBoard.controller.user;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernateBoard.entity.user.User;
import org.hibernateBoard.service.user.UserService;
import org.hibernateBoard.util.HttpSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
		
		User userInfo = (User) HttpSessionUtils.getUserFormSession(session);
		
		if(userInfo != null) {
			List<User> userList = userService.userList();
			model.addAttribute("userList", userList);
			
			result = "/user/userList";
		} else {
			result = "redirect:/login/loginForm";
		}
		
		return result;
		
	}
	
	@GetMapping(value="/userDetail")
	public String detail(@PathVariable long userNo, Model model, HttpSession session) {
		
		String result = "";
		
		User userInfo = (User) HttpSessionUtils.getUserFormSession(session);
		
		if(userInfo != null) {
			User user = userService.userDetail(userNo);
			model.addAttribute("user", user);
			
			result = "/user/userDetail";
		} else {
			result = "redirect:/login/loginForm";
		}
		
		return result;
	}
	
	@GetMapping(value="/userForm")
	public String form(Model model, HttpSession session) {
		
		String result = "";
		
		User userInfo = (User) HttpSessionUtils.getUserFormSession(session);
		
		if(userInfo != null) {
			result = "/user/userForm";
		} else {
			result = "redirect:/login/loginForm";
		}
		
		return result;
	}
	
	@GetMapping(value="/userUpdateForm")
	public String updateForm(@PathVariable long userNo, Model model, HttpSession session) {
		
		String result = "";
		
		User userInfo = (User) HttpSessionUtils.getUserFormSession(session);
		
		if(userInfo != null) {
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
	public String create(@PathVariable User user, Model model, HttpSession session) {
		
		String result = "";
		
		User userInfo = (User) HttpSessionUtils.getUserFormSession(session);
		
		if(userInfo != null) {
			userService.create(user);
			result = "redirect:/user/userList";
		} else {
			result = "redirect:/login/loginForm";
		}
		
		return result;
	}
	
	@PostMapping(value="/userUpdate")
	public String update(@PathVariable User newUser, Model model, HttpSession session) {
		
		String result = "";
		
		User userInfo = (User) HttpSessionUtils.getUserFormSession(session);
		
		if(userInfo != null) {
			long userNo = newUser.getUserNo();
			
			if(userNo == userInfo.getUserNo()) {
				userService.update(newUser);
				result = "redirect:/";
			} else {
				result = "redirect:/";
			}
		} else {
			result = "redirect:/login/loginForm";
		}
		
		return result;
	}
	
}

package org.hibernateBoard.controller;

import java.util.List;

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
	public String list(Model model) {
		
		List<User> userList = userService.userList();
		model.addAttribute("userList", userList);
		
		return "/user/userList";
		
	}
	
	@GetMapping(value="/userDetail")
	public String detail(Model model, long userNo) {
		
		User user = userService.userDetail(userNo);
		model.addAttribute("user", user);
		
		return "/user/userDetail";
	}
	
	@GetMapping(value="/userForm")
	public String form(Model model) {
		
		return "/user/userForm";
	}
	
	@GetMapping(value="/userUpdateForm")
	public String updateForm(Model model, long userNo) {
		
		User user = userService.userDetail(userNo);
		model.addAttribute("user", user);
		
		return "/user/userUpdateForm";
	}
	
	
	@PostMapping(value="/userCreate")
	public String create(User user, Model model) {
		
		userService.create(user);
		
		return "redirect:/user/userList";
	}
	
	@PostMapping(value="/userUpdate")
	public String update(User newUser, Model model) {
		
		long userNo = newUser.getUserNo();
		userService.update(newUser);
		
		return "redirect:/user/userDetail?userNo=" + userNo;
	}
	
}

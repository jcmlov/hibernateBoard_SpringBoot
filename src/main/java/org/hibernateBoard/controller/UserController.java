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
	
	@GetMapping(value="/userForm")
	public String form(Model model) {
		
		return "/user/userForm";
	}
	
	
	@PostMapping(value="/userCreate")
	public String create(User user, Model model) {
		
		userService.create(user);
		
		return "redirect:/user/userList";
	}
}

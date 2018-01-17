package org.hibernateBoard.controller;

import java.util.List;

import org.hibernateBoard.VO.UserVO;
import org.hibernateBoard.entity.User;
import org.hibernateBoard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping(value="/userList")
	public String list(Model model) {
		
		List<User> userList = userService.userList();

		model.addAttribute("userList", userList);
		
		return "userList";
		
	}
	
	@PostMapping(value="/userCreate")
	public String create(User user, Model model) {
		
		userService.create(user);
		
		return "redirect:/userList";
	}
}

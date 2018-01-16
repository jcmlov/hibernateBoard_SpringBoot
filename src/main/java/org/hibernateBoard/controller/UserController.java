package org.hibernateBoard.controller;

import org.hibernateBoard.entity.User;
import org.hibernateBoard.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

	private UserRepository userRp;
	
	@PostMapping(value="/create")
	public String create(User user, Model model) {
		
		System.out.println(user.toString());
		
		// userRp.save(user);
		
		return "welcome";
	}
}

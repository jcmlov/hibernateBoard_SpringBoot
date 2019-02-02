package org.hibernateBoard.controller.login;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/login")
public class LoginController {

	@GetMapping(value="/loginForm")
	public String loginForm(HttpServletRequest req) {
		
		return "/login/loginForm";
	}
	
}

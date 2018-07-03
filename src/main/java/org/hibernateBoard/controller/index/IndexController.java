package org.hibernateBoard.controller.index;

import javax.servlet.http.HttpSession;

import org.hibernateBoard.entity.member.Member;
import org.hibernateBoard.util.HttpSessionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/")
public class IndexController {

	@GetMapping(value="")
	public String index() {
		return "/index";
	}
	
	@GetMapping(value="main")
	public String indexMain(Model model, HttpSession session) {
		
		String result = "";
		
		Member userInfo = (Member) HttpSessionUtils.getUserFormSession(session);
		
		if(userInfo != null) {
			result = "/index/main";
		} else {
			result = "redirect:/login/loginForm";
		}
		
		return result;
	}
}

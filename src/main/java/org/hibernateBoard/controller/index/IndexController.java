package org.hibernateBoard.controller.index;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/")
public class IndexController {

	@GetMapping(value="")
	public String index() {
		return "/index";
	}
	
	@RequestMapping(value="main", method = RequestMethod.GET)
	public String indexMain(Model model, Principal principal) {
		return "/index/main";
	}
}

package org.hibernateBoard.controller.index;

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
	public String indexMain(Model model) {
		return "/index/main";
	}
}

package org.hibernateBoard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/index")
public class IndexController {

	@GetMapping(value="/main")
	public String index() {
		return "/index/main";
	}
}

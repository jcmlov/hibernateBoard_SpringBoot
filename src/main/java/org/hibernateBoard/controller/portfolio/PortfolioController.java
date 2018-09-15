package org.hibernateBoard.controller.portfolio;

import javax.servlet.http.HttpSession;

import org.hibernateBoard.service.portfolio.PortfolioService;
import org.hibernateBoard.util.HttpSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/portfolio")
public class PortfolioController {

	@Autowired
	private PortfolioService portfolioService;
	
	@GetMapping(value="/portfolioList")
	public String list(Model model, HttpSession session) {
		return "/portfolio/portfolioList";
	}
	
}

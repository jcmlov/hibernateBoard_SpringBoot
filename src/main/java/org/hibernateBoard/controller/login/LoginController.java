package org.hibernateBoard.controller.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernateBoard.entity.user.User;
import org.hibernateBoard.service.login.LoginService;
import org.hibernateBoard.util.HttpSessionUtils;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/login")
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@GetMapping(value="/loginForm")
	public String loginForm() {
		
		return "/login/loginForm";
	}
	
	@PostMapping(value="/loginAction")
	public String loginAction(User user, Model model, HttpSession session) {
		
		User userInfo = loginService.findByUserIdAndUserPw(user.getUserId(), user.getUserPw());
		
		if(userInfo == null) {
			model.addAttribute("message", "아이디, 비밀번호가 일치하지 않습니다.");
			return "redirect:/login/loginForm";
		}
		
		session.setAttribute(HttpSessionUtils.USER_SESSION_KEY, userInfo);
		
		return "redirect:/main";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/ajax/loginAction")
	@ResponseBody
	public JSONObject androidLogin(HttpServletRequest request) {
		
		JSONObject result = new JSONObject();
		
		User userInfo = loginService.findByUserIdAndUserPw(request.getParameter("userId"), request.getParameter("userPw"));
		if(userInfo != null) {
			result.put("success", true);
			result.put("userId", userInfo.getUserId());
			result.put("userPw", userInfo.getUserPw());
			result.put("userNm", userInfo.getUserNm());
			result.put("userEmail", userInfo.getUserEmail());
		} else {
			result.put("success", false);
		}

		return result;
	}
	
	@GetMapping(value="/logOutAction")
	public String logOutAction(HttpSession session) {
		
		session.removeAttribute(HttpSessionUtils.USER_SESSION_KEY);
		
		return "redirect:/";
	}
	
}

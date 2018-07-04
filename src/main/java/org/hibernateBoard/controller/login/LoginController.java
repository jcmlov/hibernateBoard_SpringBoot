package org.hibernateBoard.controller.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernateBoard.entity.member.Member;
import org.hibernateBoard.security.service.CustomUserDetailsService;
import org.hibernateBoard.service.login.LoginService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/login")
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@GetMapping(value="/loginForm")
	public String loginForm(HttpServletRequest req) {
		
		String referer = req.getHeader("Referer");
		req.getSession().setAttribute("prevPage", referer);
		
		return "/login/loginForm";
	}
	
	@PostMapping(value="/loginAction")
	public String loginAction(Member member, Model model, HttpSession session) {
		
		// memberDetails memberInfo = custommemberDetailsService.loadmemberBymembername(member.getmemberId());
		// memberInfo.getAuthorities();
		
		return "redirect:/main";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/ajax/loginAction")
	@ResponseBody
	public JSONObject androidLogin(HttpServletRequest request) {
		
		JSONObject result = new JSONObject();
		
		Member memberInfo = loginService.findByMemberIdAndMemberPw(request.getParameter("memberId"), request.getParameter("memberPw"));
		if(memberInfo != null) {
			result.put("success", true);
			result.put("memberId", memberInfo.getMemberId());
			result.put("memberPw", memberInfo.getMemberPw());
			result.put("memberNm", memberInfo.getMemberNm());
			result.put("memberEmail", memberInfo.getMemberEmail());
		} else {
			result.put("success", false);
		}

		return result;
	}
	
	@GetMapping(value="/logOutAction")
	public String logOutAction(HttpSession session) {
		
		return "redirect:/";
	}
	
}

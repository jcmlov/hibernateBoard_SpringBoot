package org.hibernateBoard.controller.member;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernateBoard.entity.member.Member;
import org.hibernateBoard.entity.member.MemberRole;
import org.hibernateBoard.service.user.UserService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/user")
public class MemberController {

	@Autowired
	private UserService userService;

	@GetMapping(value="/userList")
	public String list(Model model, HttpSession session) {
		
		List<Member> userList = userService.userList();
		model.addAttribute("userList", userList);
			
		return "/user/userList";
		
	}
	
	@GetMapping(value="/userDetail")
	public String detail(long userNo, Model model, HttpSession session) {
		
		Member user = userService.userDetail(userNo);
		model.addAttribute("user", user);
		
		return "/user/userDetail";
	}
	
	@GetMapping(value="/userForm")
	public String form(Model model, HttpSession session) {
		
		return "/user/userForm";
	}
	
	@GetMapping(value="/userUpdateForm")
	public String updateForm(long userNo, Model model, HttpSession session) {
		
		Member user = userService.userDetail(userNo);
		model.addAttribute("user", user);
		
		return "/user/userUpdateForm";
	}
	
	
	@PostMapping(value="/userCreate")
	public String create(Member user, Model model, HttpSession session) {
		
		MemberRole role = new MemberRole();
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		user.setUserPw(passwordEncoder.encode(user.getUserPw()));
		role.setRoleName("DEFAULT_USER");
		
		userService.create(user);
		
		return "redirect:/user/userList";
	}

	
	@PostMapping(value="/userUpdate")
	public String update(Member newUser, Model model, HttpSession session) {
		
		String result = "";
		
		long userNo = newUser.getUserNo();
		String userNumber = (String) session.getAttribute("userNo");
		
		if(userNo == Integer.parseInt(userNumber)) {
			userService.update(newUser);
			result = "redirect:/";
		} else {
			result = "redirect:/login/loginForm";
		}
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/ajax/userCreate")
	@ResponseBody
	public JSONObject androidCreate(HttpServletRequest request) {
		
		JSONObject result = new JSONObject();
		
		Member user = new Member();
		user.setUserId(request.getParameter("userId"));
		user.setUserPw(request.getParameter("userPw"));
		user.setUserNm(request.getParameter("userNm"));
		user.setUserEmail(request.getParameter("userEmail"));
		
		Member returnUser = userService.validateUser(request.getParameter("userEmail"));
		if(returnUser != null) {
			result.put("success", false);
		} else {
			userService.create(user);
			result.put("success", true);
		}

		return result;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/ajax/userList")
	@ResponseBody
	public JSONObject ajaxUserList(HttpServletRequest request) {
		
		JSONObject result = new JSONObject();
		List<Member> userList = userService.userList();
		
		if(userList != null) {
			result.put("response", userList);
		} else {
			result.put("response", new ArrayList<Member>());
		}

		return result;
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/ajax/userDelete")
	@ResponseBody
	public JSONObject androidDelete(HttpServletRequest request) {
		
		JSONObject result = new JSONObject();
		
		Member user = new Member();
		user.setUserId(request.getParameter("userId"));
		
		boolean deleteResult = userService.userDelete(user.getUserId());
		result.put("success", deleteResult);

		return result;
	}
	
}

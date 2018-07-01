package org.hibernateBoard.controller.user;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernateBoard.entity.user.User;
import org.hibernateBoard.service.user.UserService;
import org.hibernateBoard.util.HttpSessionUtils;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping(value="/userList")
	public String list(Model model, HttpSession session) {
		
		String result = "";
		
		User userInfo = (User) HttpSessionUtils.getUserFormSession(session);
		
		if(userInfo != null) {
			List<User> userList = userService.userList();
			model.addAttribute("userList", userList);
			
			result = "/user/userList";
		} else {
			result = "redirect:/login/loginForm";
		}
		
		return result;
		
	}
	
	@GetMapping(value="/userDetail")
	public String detail(long userNo, Model model, HttpSession session) {
		
		String result = "";
		
		User userInfo = (User) HttpSessionUtils.getUserFormSession(session);
		
		if(userInfo != null) {
			User user = userService.userDetail(userNo);
			model.addAttribute("user", user);
			
			result = "/user/userDetail";
		} else {
			result = "redirect:/login/loginForm";
		}
		
		return result;
	}
	
	@GetMapping(value="/userForm")
	public String form(Model model, HttpSession session) {
		
		return "/user/userForm";
	}
	
	@GetMapping(value="/userUpdateForm")
	public String updateForm(long userNo, Model model, HttpSession session) {
		
		String result = "";
		
		User userInfo = (User) HttpSessionUtils.getUserFormSession(session);
		
		if(userInfo != null) {
			if(userNo == userInfo.getUserNo()) {
				User user = userService.userDetail(userInfo.getUserNo());
				model.addAttribute("user", user);
				
				result = "/user/userUpdateForm";
			} else {
				result = "redirect:/";
			}
		} else {
			result = "redirect:/login/loginForm";
		}
		
		return result;
	}
	
	
	@PostMapping(value="/userCreate")
	public String create(User user, Model model, HttpSession session) {
		
		String result = "";
		
		User userInfo = (User) HttpSessionUtils.getUserFormSession(session);
		
		if(userInfo != null) {
			userService.create(user);
			result = "redirect:/user/userList";
		} else {
			result = "redirect:/login/loginForm";
		}
		
		return result;
	}

	
	@PostMapping(value="/userUpdate")
	public String update(User newUser, Model model, HttpSession session) {
		
		String result = "";
		
		User userInfo = (User) HttpSessionUtils.getUserFormSession(session);
		
		if(userInfo != null) {
			long userNo = newUser.getUserNo();
			
			if(userNo == userInfo.getUserNo()) {
				userService.update(newUser);
				result = "redirect:/";
			} else {
				result = "redirect:/";
			}
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
		
		User user = new User();
		user.setUserId(request.getParameter("userId"));
		user.setUserPw(request.getParameter("userPw"));
		user.setUserNm(request.getParameter("userNm"));
		user.setUserEmail(request.getParameter("userEmail"));
		
		User returnUser = userService.validateUser(request.getParameter("userEmail"));
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
		List<User> userList = userService.userList();
		
		if(userList != null) {
			result.put("response", userList);
		} else {
			result.put("response", new ArrayList<User>());
		}

		return result;
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/ajax/userDelete")
	@ResponseBody
	public JSONObject androidDelete(HttpServletRequest request) {
		
		JSONObject result = new JSONObject();
		
		User user = new User();
		user.setUserId(request.getParameter("userId"));
		
		boolean deleteResult = userService.userDelete(user.getUserId());
		result.put("success", deleteResult);

		return result;
	}
	
}

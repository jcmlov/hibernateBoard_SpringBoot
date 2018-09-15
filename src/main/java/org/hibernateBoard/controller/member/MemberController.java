package org.hibernateBoard.controller.member;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernateBoard.entity.member.Member;
import org.hibernateBoard.entity.member.MemberRole;
import org.hibernateBoard.service.member.MemberService;
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
@RequestMapping(value="/member")
public class MemberController {

	@Autowired
	private MemberService memberService;

	@GetMapping(value="/memberList")
	public String list(Model model, HttpSession session) {
		
		List<Member> memberList = memberService.memberList();
		model.addAttribute("memberList", memberList);
			
		return "/member/memberList";
		
	}
	
	@GetMapping(value="/memberDetail")
	public String detail(long memberNo, Model model, HttpSession session) {
		
		Member member = memberService.memberDetail(memberNo);
		model.addAttribute("member", member);
		
		return "/member/memberDetail";
	}
	
	@GetMapping(value="/memberForm")
	public String form(Model model, HttpSession session) {
		
		return "/member/memberForm";
	}
	
	@GetMapping(value="/memberUpdateForm")
	public String updateForm(long memberNo, Model model, HttpSession session) {
		
		Member member = memberService.memberDetail(memberNo);
		model.addAttribute("member", member);
		
		return "/member/memberUpdateForm";
	}
	
	
	@PostMapping(value="/memberCreate")
	public String create(Member member, MemberRole memberRole, Model model, HttpSession session) {
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		member.setMemberPw(passwordEncoder.encode(member.getMemberPw()));
		memberRole.setMember(member);
		memberService.create(member, memberRole);
		
		return "redirect:/member/memberList";
	}

	
	@PostMapping(value="/memberUpdate")
	public String update(Member newmember, Model model, HttpSession session) {
		
		String result = "";
		
		long memberNo = newmember.getMemberNo();
		String memberNumber = (String) session.getAttribute("memberNo");
		
		if(memberNo == Integer.parseInt(memberNumber)) {
			memberService.update(newmember);
			result = "redirect:/";
		} else {
			result = "redirect:/login/loginForm";
		}
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/ajax/memberCreate")
	@ResponseBody
	public JSONObject androidCreate(HttpServletRequest request) {
		
		JSONObject result = new JSONObject();
		
		Member member = new Member();
		member.setMemberId(request.getParameter("memberId"));
		member.setMemberPw(request.getParameter("memberPw"));
		member.setMemberNm(request.getParameter("memberNm"));
		member.setMemberEmail(request.getParameter("memberEmail"));
		
		Member returnmember = memberService.validateMember(request.getParameter("memberEmail"));
		if(returnmember != null) {
			result.put("success", false);
		} else {
			// memberService.create(member);
			result.put("success", true);
		}

		return result;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/ajax/memberList")
	@ResponseBody
	public JSONObject ajaxmemberList(HttpServletRequest request) {
		
		JSONObject result = new JSONObject();
		List<Member> memberList = memberService.memberList();
		
		if(memberList != null) {
			result.put("response", memberList);
		} else {
			result.put("response", new ArrayList<Member>());
		}

		return result;
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/ajax/memberDelete")
	@ResponseBody
	public JSONObject androidDelete(HttpServletRequest request) {
		
		JSONObject result = new JSONObject();
		
		Member member = new Member();
		member.setMemberId(request.getParameter("memberId"));
		
		boolean deleteResult = memberService.memberDelete(member.getMemberId());
		result.put("success", deleteResult);

		return result;
	}
	
}

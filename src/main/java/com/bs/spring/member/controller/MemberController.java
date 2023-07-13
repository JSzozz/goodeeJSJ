package com.bs.spring.member.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bs.spring.member.model.dto.Member;
import com.bs.spring.member.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	public MemberService service;
	
	@RequestMapping("/enrollMember.do")
	public String enrollMember() {
		return "/member/enrollMember";
	}
	
	@RequestMapping(value="/insertMember.do",
			method=RequestMethod.POST)
	public String insertMember(Member member, Model m) {
//		System.out.println(member);
		int result=service.insertMember(member);
		m.addAttribute("msg",result>0?"저장성공":"저장실패");
		m.addAttribute("loc","/");
		return "common/msg";
		//return "redirect:/";
	}
	
	@RequestMapping("/login.do")
	public String login(@RequestParam Map param, Model model, HttpSession session) {
		Member m= service.selectMemberById(param);
		System.out.println(m);
		
		if(m!=null&&m.getPassword().equals(param.get("password"))) {
			
			session.setAttribute("loginMember", m);
			
		}else {
			model.addAttribute("msg", "로그인 실패");
			model.addAttribute("loc","/");
			return "common/msg";
		}
		
		return "redirect:/";
	}
	
	@RequestMapping("/logout.do")
	public String logout(HttpServletRequest request) {
		
		HttpSession session=request.getSession(false);
		if(session!=null) {
			session.invalidate();
		}
		return "redirect:/";
	}
	
	@RequestMapping("/mypage.do")
	public String mypage() {
		
		return "member/mypage";
	}
}

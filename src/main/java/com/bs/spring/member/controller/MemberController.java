package com.bs.spring.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bs.spring.member.model.dto.Member;
import com.bs.spring.member.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	public MemberService service;
	
	@RequestMapping("/member/enrollMember.do")
	public String enrollMember() {
		return "/member/enrollMember";
	}
	
	@RequestMapping(value="/member/insertMember.do",
			method=RequestMethod.POST)
	public String insertMember(Member member, Model m) {
		System.out.println(member);
		int result=service.insertMember(member);
		m.addAttribute("msg",result>0?"저장성공":"저장실패");
		m.addAttribute("loc","/");
		return "common/msg";
	}
	
}

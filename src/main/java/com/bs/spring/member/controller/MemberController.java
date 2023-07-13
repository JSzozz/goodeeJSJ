package com.bs.spring.member.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.bs.spring.member.model.dto.Member;
import com.bs.spring.member.service.MemberService;

@Controller
@RequestMapping("/member")
@SessionAttributes({"loginMember"})
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
			
//			session.setAttribute("loginMember", m);
			model.addAttribute("loginMember", m);//@SessionAttributes({"loginMember"}) 덕분임
			
			
		}else {
			model.addAttribute("msg", "로그인 실패");
			model.addAttribute("loc","/");
			return "common/msg";
		}
		
		return "redirect:/";
	}
	
	@RequestMapping("/logout.do")
	//public String loginout(HttpSession session) {
	//@SessionAttributes로 등록된 내용삭제하기
	//SessionStatus객체를 이용해서 삭제
	public String logout(SessionStatus status) {
//		if(session!=null)
//			session.invalidate();
		
		if(!status.isComplete()) status.setComplete();
		
		return "redirect:/";
	}
	
	
	
	@RequestMapping("/mypage.do")
	public String mypage(/* String userId, Model m */) {
//		m.addAttribute("member", service.selectMemberById(Map.of("userId", userId)));
		
		return "member/mypage";
	}
}

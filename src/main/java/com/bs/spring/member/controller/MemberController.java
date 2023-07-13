package com.bs.spring.member.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.bs.spring.member.model.dto.Member;
import com.bs.spring.member.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member")
@SessionAttributes({"loginMember"})
@Slf4j
public class MemberController {

	@Autowired
	public MemberService service;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@RequestMapping("/enrollMember.do")
	public String enrollMember() {
		return "/member/enrollMember";
	}
	
	@RequestMapping(value="/insertMember.do",
			method=RequestMethod.POST)
	public String insertMember(Member member, Model model) {
//		System.out.println(member);

		//패스워드를 암호화 처리하자
		String oriPassword=member.getPassword();
		log.debug(oriPassword);
		String encodePassword=passwordEncoder.encode(oriPassword);
		log.debug(encodePassword);
		member.setPassword(encodePassword);
		System.out.println(member.getPassword());
		//$2a$10$6vOZQaomQvT/fBZ.wJXUnuW/hrbm083btZFyOwopDYhQSxTvyWxCi
		
		int result=service.insertMember(member);
		model.addAttribute("msg",result>0?"저장성공":"저장실패");
		model.addAttribute("loc","/");
		return "common/msg";
		//return "redirect:/";
	} 
	
	@RequestMapping("/login.do")
	public String login(@RequestParam Map param, Model model, HttpSession session) {
		Member m= service.selectMemberById(param);
		System.out.println(m);
		
		//암호화된 값을 비교하기 위해서는 제공되는 BCryptPasswordEncoder가 제공하는 메소드를 이용해야한다
		if(m!=null&&
			passwordEncoder.matches((String)param.get("password"), m.getPassword())
//			m.getPassword().equals(param.get("password"))
				) {
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

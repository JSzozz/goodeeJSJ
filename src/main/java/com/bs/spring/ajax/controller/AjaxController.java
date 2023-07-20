package com.bs.spring.ajax.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bs.spring.board.model.dto.Board;
import com.bs.spring.common.exception.AuthenticationException;
import com.bs.spring.member.model.dto.Member;
import com.bs.spring.member.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/ajax")
@Slf4j
public class AjaxController {

	@Autowired
	private MemberService memberService;
	
	@GetMapping("/basicTest.do")
	public void basic(HttpServletRequest req, HttpServletResponse res)
			throws IOException,ServletException{
		Board b=Board.builder().boardTitle("냉무").boardContent("냉무").build();
		ObjectMapper mapper=new ObjectMapper();
//		res.setContentType("text/csv;charset=utf-8");
//		res.getWriter().write("test");
		res.setContentType("application/json;charset=utf-8");
		res.getWriter().write(mapper.writeValueAsString(b));
		
	}

	//리턴값에 반환할 객체를 선언
	//@ResponseBody -> json으로 반환할 수 있게 처리
	@GetMapping("/converter")
	@ResponseBody
	public Board convertTest() {
		return Board.builder().boardTitle("spring좋다!").boardContent("하하하하하").build();
	}
	
	
	@PostMapping("/idduplicate")
	public @ResponseBody Member idduplicate(@RequestParam Map param) {
		log.info("{}",param);
		return memberService.selectMemberById(param);
	}
	
	@GetMapping("/basic2")
	public String basic2() {
		return "demo/demo";
	} 
	
	@GetMapping("/memberAll.do")
	@ResponseBody//v
	public List<Member> selectMemberAll(){
	//	if(1==1)throw new AuthenticationException("권한에러");
		return memberService.selectMemberAll();
	}
	
	@PostMapping("/insertData.do")
	@ResponseBody
	public Member inserData(@RequestBody Member m) {
		//@RequestBody가 문자열로 넘어온 정보를 알아서 파싱해서 데이터를 매칭시켜줌
		log.info("{}",m);
		return m;
	}
}









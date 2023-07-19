package com.bs.spring.ajax.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bs.spring.board.model.dto.Board;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/ajax")
public class AjaxController {

	
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
	
}









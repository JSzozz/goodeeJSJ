package com.bs.spring.ajax.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
}









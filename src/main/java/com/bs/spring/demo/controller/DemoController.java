package com.bs.spring.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DemoController {

	@RequestMapping("/demo/demo.do")
	public String demo() {
		// /WEB-INF/views/demo/demo.jsp
		// prefix : /WEB-INF/views
		return "/demo/demo";
		// suffix : .jsp
	}
}

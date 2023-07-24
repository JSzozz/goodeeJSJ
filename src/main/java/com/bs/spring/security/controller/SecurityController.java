package com.bs.spring.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {
	
	@GetMapping("/loginpage")
	public String loginpage() {
		return "member/loginpage";
	}
}

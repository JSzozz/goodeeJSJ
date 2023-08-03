package com.bs.spring.jpa.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bs.spring.jpa.entity.WebMember;
import com.bs.spring.jpa.model.service.WebService;

@RestController
@RequestMapping("/web")
public class WebJpaController {
	
	private WebService service;
	
	public WebJpaController(WebService service) {
		super();
		this.service = service;
	}



	@GetMapping("/members")
	public List<WebMember> selectMemberAll(){
		return service.selectMemberAll();
	}
}





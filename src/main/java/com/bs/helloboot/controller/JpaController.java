package com.bs.helloboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bs.helloboot.dto.MemberDto;
import com.bs.helloboot.service.JpaService;

@RestController
@RequestMapping("/jpa/member")
public class JpaController {
	
	private JpaService service;
	
	public JpaController(JpaService service) {
		super();
		this.service = service;
	}

	@GetMapping("/{id}")
	public MemberDto selectByUserId(@PathVariable("id") String id) {
		
		return service.selectById(id);
		
	}
	
}

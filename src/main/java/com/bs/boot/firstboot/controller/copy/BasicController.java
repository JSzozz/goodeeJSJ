package com.bs.boot.firstboot.controller.copy;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicController {

	@RequestMapping("/")
	public String index() {
		return "하이하이하이!";
	}
	
	@RequestMapping("/test")
	public List<String> test(){
		return List.of("1","2","3","4","5");
	}
}

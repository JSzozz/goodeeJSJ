package com.bs.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bs.spring.beantest.Animal;

@Controller
public class Homecontroller {
	//springbean으로 등록된 객체는 field에 가져와 사용할 수 있음
	@Autowired
	private Animal a;
	

	@RequestMapping("/")
	public String hone() {
		System.out.println(a+"(*Homecontroller)");//Animal(name=null, age=0, weight=0.0)(*Homecontroller)
		return "index";
	}
	
}

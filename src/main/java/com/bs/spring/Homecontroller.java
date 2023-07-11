package com.bs.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bs.spring.beantest.Animal;

@Controller
public class Homecontroller {
	//@Autowired : springbean으로 등록된 객체는 field에 가져와 사용할 수 있음
	//@Qualifier : 중복된 타입이 있는 경우 @Quealifier어노테이션을 
	//             이용해서 특정 bren을 선택할 수 있음
	@Autowired
	@Qualifier("dog")
	private Animal bbo;
	@Autowired
	@Qualifier("bbo")
	private Animal dog;

	

	@RequestMapping("/")
	public String hone() {
		System.out.println(bbo+"(*Homecontroller)");//Animal(name=null, age=0, weight=0.0)(*Homecontroller)
		System.out.println(dog+"(*Homecontroller)");
		return "index";
	}
	
}

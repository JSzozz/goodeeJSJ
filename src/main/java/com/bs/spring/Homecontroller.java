package com.bs.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bs.spring.beantest.Animal;
import com.bs.spring.beantest.Employee;

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
	//springBean으로 등록되지 않은 객체를 Autowired하면 에러 발생!
	@Autowired(required = false)
	private Employee emp;
	
	@Autowired
	private Employee emp2;

	//java로 등록한  bean 가져오기
	@Autowired
	@Qualifier("ani")
	private Animal c;
	
	@Autowired
	//@Qualifier("getEmployee")
	@Qualifier("sol")
	private Employee sol;
	
	@RequestMapping("/")
	public String hone() {
		System.out.println(bbo+"(*Homecontroller1)");//Animal(name=null, age=0, weight=0.0)(*Homecontroller)
		System.out.println(dog+"(*Homecontroller2)");
		System.out.println(emp+"(*Homecontroller3)");
		System.out.println(emp2+"(*Homecontroller4)");
		System.out.println(c+"(*Homecontroller5)");
		System.out.println(sol+"(*Homecontroller6)");

		return "index";
	}
	
}

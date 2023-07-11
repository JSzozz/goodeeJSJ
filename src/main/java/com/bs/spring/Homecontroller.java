package com.bs.spring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bs.spring.beantest.Animal;
import com.bs.spring.beantest.Employee;
import com.bs.spring.beantest.FuntionalTest;
import com.bs.spring.include.TargetComponent;

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
	
	@Autowired
	List<Animal> animals;
	
	@Autowired
	private TargetComponent tc;
	
	@Autowired
	private FuntionalTest ft;
	
	//basepackage 외부에 있는 @Component
	@Autowired
	private Test test;
	
	@RequestMapping("/")
	public String hone() {
		System.out.println(bbo+"(*Homecontroller1)");//Animal(name=null, age=0, weight=0.0)(*Homecontroller)
		System.out.println(dog+"(*Homecontroller2)");
		System.out.println(emp+"(*Homecontroller3)");
		System.out.println(emp2+"(*Homecontroller4)");
		System.out.println(c+"(*Homecontroller5)");
		System.out.println(sol+"(*Homecontroller6)");
		animals.forEach(System.out::println);
		System.out.println(tc+"(*Homecontroller7)");
		System.out.println(ft+"(*Homecontroller8-1)");//com.bs.spring.beantest.FuntionalTest@24dfa151(ToString 오버라이드 안되어있어서 나옴)
		System.out.println(ft.getA()+"(*Homecontroller8-2)");

		return "index";
	}
	
}

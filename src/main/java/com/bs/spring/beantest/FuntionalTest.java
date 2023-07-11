package com.bs.spring.beantest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import lombok.Data;

//pojo클래스를 생성하고 선언부에서 bean으로 등록할 수 있다.
//@Component, @Controller, @Serice, @Repository
//어노테이션을 이용해서 spring bean으로 등록
//1. @Component : 기본 spring bean으로 등록할 때 사용
//2. @Controller, @Serice, @Repository(dao) : mvc패턴에 의해 역할 지정된 클래스를 등록할 때 사용

//@Data
@Component//클래스를 bean으로 등록
public class FuntionalTest {

	private String name="test name";
	
	//@Autowired
	private Animal a;
	
	//생성자를 이용한 DI(의존성주입)
//	public FuntionalTest(@Qualifier("dog") Animal a) {
//		this.a=a;
//	}
	
	@Autowired
	public void setA(@Qualifier("dog") Animal a) {
		this.a=a;
	}
	public Animal getA() {
		return this.a;
	}
}

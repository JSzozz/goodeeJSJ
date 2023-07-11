package com.bs.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bs.spring.beantest.Animal;

//클래식 방식으로 bean등록해서 사용하기
//pojo클래스를 configuraion으로 사용할 수 있음
// -> @Configuration 어노테이션 이용

@Configuration
public class BeanTestConfiguration {
	//springbeanconfiguration.xml과 동일한 기능
	
	//spring에서 사용할 bean을 자바 코드로 등록할 수 있다.
	//@Bean어노테이션을 이용
	//메소드선언을 통해 등록함.
	@Bean
	public Animal ani() {
		return Animal.builder().name("킥킥").age(5).height(80).build();
	}
	
	
}

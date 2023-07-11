package com.bs.spring.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bs.spring.beantest.Animal;
import com.bs.spring.beantest.Department;
import com.bs.spring.beantest.Employee;

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
	
	@Bean
	//등록된 bean에 특정 id값 부여하기
	@Qualifier("sol")
	public Employee getEmployee(Department dept) {
		return Employee.builder()
				.name("최솔")
				.age(27)
				.address("경기도 안양시")
				.dept(dept)
				.salary(200).build();
	}
	
	
}

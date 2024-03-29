package com.bs.spring.beantest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor

public class Employee {

	private String name;
	private int age;
	private String address;
	private int salary;
	private Department dept;
	
	public Employee(Department dept) {
		this.dept=dept;
	}
	
	
	public void initialMethod() {
		System.out.println(this.getClass().getName()+"클래스 생성했다(*Employee)");
	}
	
	public void destroyMethod() {
		System.out.println(this.getClass().getName()+"클래스 소멸했다(*Employee)");
	}
}

package com.bs.spring.jpa.entity;

import java.io.Serializable;

import lombok.Data;

//id식별자 클래스
//1. 기본생성자가 있어야한다.
//2. 클래스가 public 으로 선언되어있어야한다.
//3. Serialiable인터페이스 구현되어있어야한다.
//4. equals,hashCode가 오버라이딩 되어있어야한다.
@Data
public class StudentClubsId implements Serializable {
	
	private long student;
	private long club;
	
}

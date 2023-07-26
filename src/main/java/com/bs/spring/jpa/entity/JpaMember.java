package com.bs.spring.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

//일반 pojo클래스를 Entity로 등록하기 위해서는
//@Entity를 이용한다 -> 클래스 선언부에 선언.
//(유의)기본생성자는 필수로 있어야함, final클래스(enum, interface, inner)는 사용불가 필드에 final사용 불가
@Entity//(name = "JapMember")
//import javax.persistence.Entity;

//@Table이용하기 -> Table에 대한 설정을 하는 어노에티엿
//schema,catalog, 테이블레벨 제약조건 설정, DB에 생성될 테이블명 설정(생략하면 클래스 명으로 테이블 생성)
//@Table()

public class JpaMember {

	@Id //entity를 식별하는 식별자, DB에서는 Primary key제약조건 설정
	private Long memberNo;
	private String memberId;
	private String memberPwd;
	private Integer age;//not null x
	//private int age;//not null o
	private double height;
	
}

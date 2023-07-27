package com.bs.spring.jpa.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import com.bs.spring.jpa.common.Level;
import com.bs.spring.jpa.common.Role;

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

//@SequenceGenerator -> DB에서 사용할 sequence를 생성하는 어노테이션, ID값을 자동부여할 때 사용
//name 시퀀스 이름 / sequenceName DB에 등록될(된) 시퀀스 이름 / initValue allocationSize

//(+)@TableGenerator -> ID값을 중복없이 발급하는 테이블 생성해서 ID부여하는 용도

//@JsonIdentityInfo -> Entity 객체를 가져올 때 양방향으로 일대다, 다대일 관계에 있으면 무한루핑이 발생하는데 이를 차단하는 어노테이션 

//@Table(name="memberJpa")
@SequenceGenerator(
		name = "seq_jpamemberno", 
		sequenceName = "seq_jpamemberno", 
		initialValue = 1, 
		allocationSize = 1)

public class JpaMember {

	@Id //entity를 식별하는 식별자, DB에서는 Primary key제약조건 설정
	@GeneratedValue(generator = "seq_jpamemberno",strategy = GenerationType.SEQUENCE)
	@Column(name = "member_no")
	private Long memberNo;//not null o
	@Column(name = "member_id", unique = true, nullable=false, length = 20)
	private String memberId;//not null x
	@Column(name = "member_pwd", nullable=false, length = 20)
	private String memberPwd;//not null x
	
	//BigDecimal 타입에 사용하는 숫자설정
	//precision : 전체자리수
	//scale : 소수점자리수
	@Column(precision = 10,scale = 3)
	private BigDecimal age;//not null x
	//private Integer age;//not null x
	//private int age;//not null o
	@Column(columnDefinition = "number default 100.0")
	private double height;//not null o
	 
	//EnumType을 이용해서 처리하기
	@Column(name = "member_role")
	@Enumerated(EnumType.STRING)//문자열 자체를 저장(권장)
	private Role role;
	@Column(name = "member_level")
	@Enumerated(EnumType.ORDINAL)//문자열과 연결되어 있는 숫자를 저장(비권장 - 자료꼬임위험있음)
	private Level level;
	
	
} 

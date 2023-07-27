package com.bs.spring.jpa.model.dao;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.bs.spring.jpa.common.Level;
import com.bs.spring.jpa.common.Role;
import com.bs.spring.jpa.entity.JpaMember;

@Repository
public class JpaDaoImpl implements JpaDao {

	@Override
	public void basictest(EntityManager em) {
		//em 메소드 이용
		//1. JpaMember클래스 영속성 등록하기
		JpaMember m=JpaMember.builder()
					.memberId("20200120")
					.memberPwd("rhrhrh22")
					.age(new BigDecimal(27))
					.height(178.2)
					.level(Level.DIAMOND)
					.role(Role.ADMIN)
					.build(); // ->비영속

		//영속성 처리하기
		em.persist(m);//매개변수로 전달된 객체가 영속성 컨텍스트에 저장이 되고 영속성 컨텍스트에 새로 저장되면 insert sql문을 자동으로 생성

		JpaMember m2=JpaMember.builder()
				.memberId("abc")
				.memberPwd("abc123")
				.age(new BigDecimal(26))
				.height(165.2)
				.level(Level.GOLD)
				.role(Role.USER)
				.build(); // ->비영속
		
		em.persist(m2);
		
		//저장된 객체 불러오기 -> select문
		JpaMember selectM = em.find(JpaMember.class, 1L);
		System.out.println(selectM);
	}
}

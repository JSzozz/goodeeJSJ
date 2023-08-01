package com.bs.spring.jpa.model.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.bs.spring.jpa.common.Level;
import com.bs.spring.jpa.common.Role;
import com.bs.spring.jpa.entity.Address;
import com.bs.spring.jpa.entity.BoardEntity;
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
					.birthDay(new Date(java.sql.Date.valueOf(LocalDate.of(1998, 8, 3)).getTime())) 
					.startDate(new Date(java.sql.Timestamp.valueOf(LocalDateTime.of(1998, 8, 3, 10, 30)).getTime()))
					.addr(Address.builder().statement("경기도").detailAddress("시흥시 배곧동").zipcode("123-456").build())
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
				.birthDay(new Date(java.sql.Date.valueOf(LocalDate.of(1998, 8, 3)).getTime()))
				.startDate(new Date(java.sql.Timestamp.valueOf(LocalDateTime.of(1998, 8, 3, 10, 30)).getTime()))
				.build(); // ->비영속
		
		em.persist(m2);
		
		//저장된 객체 불러오기 -> select문
		JpaMember selectM = em.find(JpaMember.class, 1L);
		System.out.println(selectM);
	}

	@Override
	public void manytoone(EntityManager em) {
//x		BoardEntity b=BoardEntity.builder()
//						.boardTitle("첫번째 게시글")
//						.boardContent("나의 첫 게시글")
//						.writeDate(java.sql.Date.valueOf(LocalDate.now()))
//						.boardWriter(
//								JpaMember.builder()
//								.memberId("202001201")
//								.memberPwd("rhrhrh22")
//								.age(new BigDecimal(27))
//								.height(178.2)
//								.level(Level.DIAMOND)
//								.role(Role.ADMIN)
//								.birthDay(new Date(java.sql.Date.valueOf(LocalDate.of(1998, 8, 3)).getTime()))
//								.startDate(new Date(java.sql.Timestamp.valueOf(LocalDateTime.of(1998, 8, 3,10,30)).getTime()))
//								.addr(Address.builder().statement("경기도").detailAddress("시흥시 배곧동")
//										.zipcode("123-456").build())
//								.build())
//						.build();
//		em.persist(b);
		JpaMember m=JpaMember.builder()
				.memberId("202001201")
				.memberPwd("rhrhrh22")
				.age(new BigDecimal(27))
				.height(178.2)
				.level(Level.DIAMOND)
				.role(Role.ADMIN)
				.birthDay(new Date(java.sql.Date.valueOf(LocalDate.of(1998, 8, 3)).getTime()))
				.startDate(new Date(java.sql.Timestamp.valueOf(LocalDateTime.of(1998, 8, 3,10,30)).getTime()))
				.addr(Address.builder().statement("경기도").detailAddress("시흥시 배곧동")
						.zipcode("123-456").build())
				.build();
		em.persist(m);	//v(유의) : 영속성을 단계적으로 만들어줘야함	
		BoardEntity b=BoardEntity.builder()
						.boardTitle("첫번째 게시글")
						.boardContent("나의 첫 게시글")
						.writeDate(java.sql.Date.valueOf(LocalDate.now()))
						.boardWriter(m)
						.build();
		em.persist(b);		
	}
}

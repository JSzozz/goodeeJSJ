package com.bs.spring.jpa.model.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.bs.spring.jpa.common.Level;
import com.bs.spring.jpa.common.Role;
import com.bs.spring.jpa.entity.Address;
import com.bs.spring.jpa.entity.BoardEntity;
import com.bs.spring.jpa.entity.Club;
import com.bs.spring.jpa.entity.JpaMember;
import com.bs.spring.jpa.entity.LockerEntity;
import com.bs.spring.jpa.entity.StudentClubs;
import com.bs.spring.jpa.entity.StudentEntity;

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
		
		m.setBoards(new ArrayList());
		m.getBoards().add(b);
		
		em.persist(b);		
	}
	

	@Override
	public void boardBydId(EntityManager em, long no) {
//		BoardEntity b=em.find(BoardEntity.class, no);
//		System.out.println("boardEntity조회결과");
//		System.out.println(b);
		JpaMember m=em.find(JpaMember.class, no);
		System.out.println("jpaMember조회결과");
//		System.out.println(m.getMemberNo()+m.getMemberId()+m.getMemberPwd());
		System.out.println(m);

//		System.out.println("jpaMember조회결과");
//		System.out.println(m.getBoards());
	}

	@Override
	public void insertStudent(EntityManager em) {
		StudentEntity s=StudentEntity.builder()
				.studentNo(1)
				.studentName("유병승")
				.classNumber(3)
				.grade(1)
				.build();
		
		LockerEntity l=LockerEntity.builder()
				.lockerNo(1).lockerColor("파랑")
				.lockerPosition("3층 E강의장")
				.build();
		
		s.setMylocker(l);
		
		em.persist(s);
		
	}

	@Override
	public void selectStudentById(EntityManager em, long no) {
		StudentEntity search=em.find(StudentEntity.class, 1L);
		System.out.println(search);
	}

	@Override
	public void deleteStudent(EntityManager em, long no) {
		LockerEntity l=em.find(LockerEntity.class, no);	
		System.out.println(l);
		em.remove(l);
	}
	
	@Override
	public void updateStudent(EntityManager em, Map<String, Object> param) {
		StudentEntity student=em.find(StudentEntity.class, Long.parseLong((String)param.get("no")));
		student.setStudentName((String)param.get("name"));
		student.setGrade(Integer.parseInt((String)param.get("grade")));
	}
	

	@Override
	public void insertClub(EntityManager em) {
		Club c=Club.builder()
				.clubNo(1).clubName("낚시").location("안양천")
				.build();
		Club c1=Club.builder()
				.clubNo(2).clubName("등산").location("남산")
				.build();
		Club c2=Club.builder()
				.clubNo(3).clubName("컴퓨터").location("정보화교육실")
				.build();
		Club c3=Club.builder()
				.clubNo(4).clubName("축구").location("운동장")
				.build();
		
		StudentEntity s=StudentEntity.builder()
				.studentNo(1)
				.studentName("유병승")
				.classNumber(3)
				.grade(1)
				.build();
		StudentEntity s2=StudentEntity.builder()
				.studentNo(2)
				.studentName("강민기")
				.classNumber(2)
				.grade(1)
				.build();
		StudentEntity s3=StudentEntity.builder()
				.studentNo(3)
				.studentName("이은지")
				.classNumber(4)
				.grade(2)
				.build();
		
	
		
		//s.setClubs(new ArrayList<>());
//		s.getClubs().add(c1);
//		s.getClubs().add(c2);
		
	//	s2.setClubs(new ArrayList<>());
//		s2.getClubs().add(c3);
		
		//s3.setClubs(new ArrayList<>());
//		s3.getClubs().add(c);
//		s3.getClubs().add(c1);
//		s3.getClubs().add(c2);
//		s3.getClubs().add(c3);
		
//		em.persist(s);
//		em.persist(s2);
//		em.persist(s3);
		StudentClubs sc=StudentClubs.builder()
				.student(s).club(c).enrollDate(new Date())
				.build();
		
		StudentClubs sc2=StudentClubs.builder()
				.student(s).club(c).enrollDate(new Date())
				.build();
		em.persist(sc);
//		em.persist(sc2); // 주석해제하는 경우 복합키 설정 상 sc와 sc2의 동일 값으로 인한 오류 발생 확인 가능함
		
	}
	
}

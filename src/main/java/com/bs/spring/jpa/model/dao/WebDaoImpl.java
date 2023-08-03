package com.bs.spring.jpa.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.bs.spring.jpa.entity.WebMember;
@Repository
public class WebDaoImpl implements WebDao {

	@Override
	public List<WebMember> selectMemberAll(EntityManager em) {
		WebMember member=em.find(WebMember.class, "admin");
		System.out.println(member);
		//jpa가 제공하는 메소드를 이용하면 pk값으로 한개의 row만 가져올 수 있음.
		//JPQL이라는 객체지향 SQL문을 제공함 ->  insert문은 존재하지 않음
		//SELECT 별칭.필드명,...|| 별칭(전체컬럼) 
		//FROM 엔티티명 별칭 [INNER||LEFT||RIGHT] JOIN [FETCH] 별칭.has a 관계엔티티필드명  
		//[WHERE GROUP BY HAVING ORDER BY ]
		String sql="""
				SELECT m
				FROM WebMember m
				""";//== SELECT * FROM MEMBER
		//em.createQuery(sql);
		//2가지 
		//Query : 조회결과 타입이 지정되지 않았을때 사용
		//TypedQuery<T> : 조회결과 타입이 지정되었을때 사용
		TypedQuery<WebMember> tquery=em.createQuery(sql,WebMember.class);
		Query query=em.createQuery(sql);//import javax.persistence.Query;
		//생성된 query로 데이터가져오기
		// getResultList()메소드를 이용 : 리스트로 row를 가져옴
		// getSingleResult()메소드를 이용 : 한개 row만 반환하는 쿼리문 -> 다중 row면 Exception발생
		// getResultStream()메소드를 이용 : row들을 stream으로 반환
		
		//페이징처리하기
		//query클래스에서 페이징처리 메소드를 제공함.
		//setFirstResult() -> 시작인덱스 0 부터시작 -> cPage
		//setMaxResults() -> 조회할 갯수 -> numPerPage
		tquery.setFirstResult(0);
		tquery.setMaxResults(10);
		
		List<WebMember> result=tquery.getResultList();
		List result2=query.getResultList();
		
		System.out.println("tquery 실행결과");
		result.forEach(System.out::println);
		System.out.println("query 실행결과");
		result2.forEach(System.out::println);
		
		return result;
	}

	@Override
	public List<WebMember> selectMemberByName(EntityManager em, String name) {
		//매개변수를 받아서 처리하는 JPQL만들기
		String sql = """
				SELECT m
				FROM WebMember m
				WHERE m.userName LIKE '%'||:name||'%'
				""";
		TypedQuery<WebMember> tquery = em.createQuery(sql, WebMember.class);
		//setParameter()메소드로 매개변수 세팅
		tquery.setParameter("name", name);
		
		return tquery.getResultList();
	}

		
}

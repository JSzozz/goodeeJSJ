package com.bs.spring.jpa.model.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.springframework.stereotype.Service;

import com.bs.spring.jpa.model.dao.JpaDao;

@Service
public class JpaServiceImpl implements JpaService {

	private EntityManager em;
	private JpaDao dao;
	
	public JpaServiceImpl(EntityManager em, JpaDao dao) {
		this.em=em;
		this.dao=dao;
	}
	
	@Override
	public void basictest() {
		EntityTransaction et= em.getTransaction();
		et.begin();//트랜젝션 시작
			dao.basictest(em);
		et.commit();
//		et.rollback();
	}

	@Override
	public void manytoone() {
		EntityTransaction et=em.getTransaction();
		et.begin();
			dao.manytoone(em);
		et.commit();
		em.clear();//영속성 객체들 비우기
		dao.boardBydId(em, 1);
	}
}

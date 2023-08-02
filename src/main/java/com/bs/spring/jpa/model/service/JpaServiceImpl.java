package com.bs.spring.jpa.model.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.springframework.stereotype.Service;

import com.bs.spring.jpa.model.dao.JpaDao;

@Service
public class JpaServiceImpl implements JpaService {

	private EntityManager em;
	private JpaDao dao;
	private EntityManagerFactory factory;
	
	public JpaServiceImpl(EntityManager em , JpaDao dao, EntityManagerFactory factory) {
		this.em=em;
		this.dao=dao;
		this.factory=factory;
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
//		em.clear();//영속성 객체들 비우기
		dao.boardBydId(em, 1);
	}
	
	@Override
	public void insertStudent() {
		EntityTransaction et=em.getTransaction();
		em.clear();
		et.begin();
			dao.insertStudent(em);
		et.commit();
		em.clear();
		dao.selectStudentById(em,1);
	}
	
	@Override
	public void deleteStudent(long no) {
		EntityManager em=factory.createEntityManager();
		// TODO Auto-generated method stub
		EntityTransaction et=em.getTransaction();
		et.begin();
			dao.deleteStudent(em,no);
		et.commit();
		em.close();
	}
}

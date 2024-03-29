package com.bs.spring.jpa.model.service;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bs.spring.jpa.model.dao.JpaDao;

@Service
public class JpaServiceImpl implements JpaService {

//	private EntityManager em;
	private JpaDao dao;
	private EntityManagerFactory factory;
	
	public JpaServiceImpl(JpaDao dao, @Qualifier("bstest") EntityManagerFactory factory) {
//		this.em=em;
		this.dao=dao;
		this.factory=factory;
	}
	
	@Override
	public void basictest() {
		EntityManager em=factory.createEntityManager();
		EntityTransaction et= em.getTransaction();
		et.begin();//트랜젝션 시작
			dao.basictest(em);
		et.commit();
//		et.rollback();
		em.close();
	}

	@Override
	public void manytoone() {
		EntityManager em=factory.createEntityManager();
		EntityTransaction et=em.getTransaction();
		et.begin();
			dao.manytoone(em);
		et.commit();
//		em.clear();//영속성 객체들 비우기
		dao.boardBydId(em, 1);
		em.close();
	}
	
	@Override
	public void insertStudent() {
		
		EntityManager em=factory.createEntityManager();
		EntityTransaction et=em.getTransaction();
		et.begin();
			dao.insertStudent(em);
		et.commit();
		//em.clear();
		//dao.selectStudentById(em,1);
		em.close();
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
	
	@Override
	public void updateStudent(Map<String, Object> param) {
		EntityManager em=factory.createEntityManager();
		EntityTransaction et=em.getTransaction();
		et.begin();
			dao.updateStudent(em,param);
		et.commit();
		em.close();
	}
	
	@Override
	public void insertClub() {
		// TODO Auto-generated method stub
		EntityManager em=factory.createEntityManager();
		EntityTransaction et=em.getTransaction();
		et.begin();
			dao.insertClub(em);
		et.commit();
		em.close();
	}
}

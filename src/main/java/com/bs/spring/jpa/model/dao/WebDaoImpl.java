package com.bs.spring.jpa.model.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.bs.spring.jpa.entity.WebMember;

@Repository
public class WebDaoImpl implements WebDao {

	@Override
	public List<WebMember> selectMemberAll(EntityManager em) {
		WebMember member=em.find(WebMember.class, "admin");
		System.out.println(member);
		
		return null;
	}

}

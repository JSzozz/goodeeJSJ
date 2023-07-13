package com.bs.spring.member.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bs.spring.member.dao.MemberDao;
import com.bs.spring.member.model.dto.Member;

@Service
public class MemberServiceImpl implements MemberService { 

	@Autowired
	private MemberDao dao;
	
	@Autowired
	private SqlSessionTemplate session;
	
	@Override
	public int insertMember(Member member) {
		return dao.insertMember(session, member);
	}

}

package com.bs.spring.member.service;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bs.spring.board.model.dto.Board;
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

	@Override
	public Member selectMemberById(Map param) {
		// TODO Auto-generated method stub
		return dao.selectMemberById(session, param);
	}

	@Override
	public List<Member> selectMemberAll() {
		// TODO Auto-generated method stub
		return dao.selectMemberAll(session);
	}


	
}

package com.bs.helloboot.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.bs.helloboot.dao.MemberDao;
import com.bs.helloboot.dto.MemberDto;

@Service
public class MemberServiceImpl implements MemberService {

	private MemberDao dao;
	private SqlSession session;
	public MemberServiceImpl(MemberDao dao, SqlSession session) {
		this.dao=dao;
		this.session=session;
	}
	


	@Override
	public List<MemberDto> selectMemberAll() {
		// TODO Auto-generated method stub
		return dao.selectMemberAll(session);
	}

	@Override
	public int insertMember(MemberDto m) {
		// TODO Auto-generated method stub
		return dao.insertMember(session, m);
	}
	
	
	
	
}
package com.bs.helloboot.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.bs.helloboot.comm.mapper.MemberMapper;
import com.bs.helloboot.dto.MemberDto;
@Repository
public class MemberDaoImpl implements MemberDao {

	private MemberMapper mapper;
	
	public MemberDaoImpl(MemberMapper mapper) {
		this.mapper=mapper;
	}
	
	@Override
	public List<MemberDto> selectMemberAll(SqlSession session) {
		// TODO Auto-generated method stub
//		return session.selectList("member.selectMemberAll");
		return mapper.selectMemberAll();
	}

	@Override
	public int insertMember(SqlSession session, MemberDto m) {
		// TODO Auto-generated method stub
		return session.insert("member.insertMember",m);
	}

}

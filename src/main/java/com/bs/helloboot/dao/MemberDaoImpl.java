package com.bs.helloboot.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.bs.helloboot.dto.MemberDto;

@Repository
public class MemberDaoImpl implements MemberDao {

	@Override
	public List<MemberDto> selectMemberAll(SqlSession session) {
		// TODO Auto-generated method stub
		return session.selectList("member.selectMemberAll");
	}

	@Override
	public int insertMember(SqlSession session, MemberDto m) {
		// TODO Auto-generated method stub
		return session.selectOne("member.insertMember", m);
	}

}

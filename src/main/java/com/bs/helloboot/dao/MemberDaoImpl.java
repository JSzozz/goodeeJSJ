package com.bs.helloboot.dao;

import java.util.List;
import java.util.Map;

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

	@Override
	public MemberDto selectMemberById(String userId) {
		// TODO Auto-generated method stub
		return mapper.selectMemberById(userId);
	}

	@Override
	public List selectMemberByName(SqlSession session, Map<String, Object> param) {
		// TODO Auto-generated method stub
		return mapper.selectMemberByWhere(param);
	}

}

package com.bs.spring.member.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.bs.spring.board.model.dto.Board;
import com.bs.spring.member.model.dto.Member;

@Repository
public class MemberDaoImpl implements MemberDao{

	@Override
	public int insertMember(SqlSessionTemplate session, Member member) {
		return session.insert("member.insertMember",member);
	}

	@Override
	public Member selectMemberById(SqlSessionTemplate session, Map param) {
		// TODO Auto-generated method stub
		return session.selectOne("member.selectMemberById",param);
	}


	
}

package com.bs.spring.member.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.bs.spring.member.model.dto.Member;

@Repository
public class MemberDaoImpl implements MemberDao{

	@Override
	public int insertMember(SqlSessionTemplate session, Member member) {
		return session.insert("member.insertMember",member);
	}
	
}

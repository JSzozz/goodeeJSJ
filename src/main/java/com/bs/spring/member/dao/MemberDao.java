package com.bs.spring.member.dao;

import org.mybatis.spring.SqlSessionTemplate;

import com.bs.spring.member.model.dto.Member;

public interface MemberDao {
	
	int insertMember(SqlSessionTemplate session, Member member);
}

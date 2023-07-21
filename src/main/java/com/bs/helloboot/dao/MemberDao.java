package com.bs.helloboot.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.bs.helloboot.dto.MemberDto;

public interface MemberDao {
	
	List<MemberDto> selectMemberAll(SqlSession session);
	
	int insertMember (SqlSession session, MemberDto m);
}
package com.bs.spring.member.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import com.bs.spring.board.model.dto.Board;
import com.bs.spring.member.model.dto.Member;

public interface MemberDao {
	
	int insertMember(SqlSessionTemplate session, Member member);

	Member selectMemberById(SqlSessionTemplate session, Map param);


	
	
}

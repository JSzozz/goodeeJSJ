package com.bs.helloboot.service;

import java.util.List;

import com.bs.helloboot.dto.MemberDto;

public interface MemberService {

	List<MemberDto> selectMemberAll();
	
	int insertMember(MemberDto m);
	
	MemberDto selectMemberById(String userId);
}

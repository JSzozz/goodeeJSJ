package com.bs.spring.member.service;

import java.util.Map;

import com.bs.spring.member.model.dto.Member;

public interface MemberService {

	int insertMember(Member member);

	Member selectMemberById(Map param);
}

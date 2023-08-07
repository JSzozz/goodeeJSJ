package com.bs.helloboot.service;

import com.bs.helloboot.dto.MemberDto;

public interface JpaService {

	MemberDto selectById(String id);
}

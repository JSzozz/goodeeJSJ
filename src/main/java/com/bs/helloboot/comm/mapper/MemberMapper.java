package com.bs.helloboot.comm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.bs.helloboot.dto.MemberDto;

@Mapper
public interface MemberMapper {

	@Select("SELECT * FROM MEMBER")
	List<MemberDto> selectMemberAll();
	
	@Select("SELECT * FROM MEMBER WHERE USERID=#{id}")
	MemberDto selectMemberById(String id);
}

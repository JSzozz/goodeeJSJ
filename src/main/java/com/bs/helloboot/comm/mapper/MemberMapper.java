package com.bs.helloboot.comm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.bs.helloboot.dto.MemberDto;

@Mapper
public interface MemberMapper {

	@Select("SELECT * FROM MEMBER")
	List<MemberDto> selectMemberAll();
	
	@Select("SELECT * FROM MEMBER WHERE USERID=#{id}")
	MemberDto selectMemberById(String id);
	
//	@Select(value="SELECT * FROM MEMBER WHERE USERNAME LIKE '%'||#{NAME}||'%' AND ")
	
	@SelectProvider(type=MemberSelectBuilder.class, method="selectMemberByWhere")
	List<MemberDto> selectMemberByWhere(Map<String, Object> param);
	
}

package com.bs.helloboot.comm.mapper;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

public class MemberSelectBuilder {

	public static String selectMemberByWhere(Map<String,Object> param) {
		return new SQL() {{
			SELECT("*");
			FROM("MEMBER");
			if(param.get("userName")!=null && !param.get("userName").equals("")){
				WHERE("USERNAME like '%'||#{userName}||'%'");
			}
			}}.toString();
	}	
}

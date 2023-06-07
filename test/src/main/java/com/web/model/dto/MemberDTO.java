package com.web.model.dto;


import java.sql.Date;
import java.sql.ResultSet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@RequiredArgsConstructor

public class MemberDTO {
	
	private final String userId;
	private String password;
	private String userName;
	private char gender;
	private int age;
	private String email;
	private String phone;
	private String address;
	private String[] hobby;
	private Date enrollDate;
	
}

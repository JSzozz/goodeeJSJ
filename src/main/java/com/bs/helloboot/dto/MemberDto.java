package com.bs.helloboot.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class MemberDto {

	private String userId;
	private String password;
	private String name;
	private int age;
	private String gender;
	private String email;
	private String phone;
	private String address;
	private String hobby;
	private Date enrollDate;
}

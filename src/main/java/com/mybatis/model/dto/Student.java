package com.mybatis.model.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {
	private int studentNo;
	private String studentName;
	private String studentTel;
	private String studentAddress;
	private String studentEmail;
	private Date reg_date;
	
}
package com.bs.spring.member.model.dto;

import java.sql.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Member {
	
	@NotEmpty(message = "아이디를 입력해주세요(4글자 이상)")
	@Size(min=4)
	private String userId;
	
	//@Pattern(regexp="(?=.*?[a-z])(?=.*?[A-Z])(?=.*?[~!@#$%^&*()])[a-zA-Z!@#$%^&*()]")
	@Pattern(regexp = "(?=.*?[a-z])(?=.*?[A-Z])(?=.*?[~!@#$%^&*()])[a-zA-Z~!@#$%^&*()]{8,}", 
			message ="비밀번호를 입력해주세요(영대문자, 영소문자, 특수기호~!@#$%^&*()를 포함하고 8자 이상으로 작성해주세요)"  )
	private String password;
	
	private	String userName;
	
	private	String gender;
	
	@Min(value=14, message = "14세 이상 가입 가능합니다.")@Max(value=150)
	private	int age;
	
	@Email(message = "이메일을 입력해주세요")
	private String email;
	
	@NotEmpty(message = "휴대폰 번호를 입력해주세요.")
	private String phone;
	
	private String address;
	
	private String[] hobby;
	
	private Date enrollDate;

}

package com.btc.member.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {

	private int memberNo;
	private int memberType;
	private String memberName;
	private String email;
	private String nickName;
	private String password;
	private String phone;
	private String memberBlack;
}

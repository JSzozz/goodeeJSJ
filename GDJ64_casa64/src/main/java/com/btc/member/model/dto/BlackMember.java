package com.btc.member.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlackMember {

	private int memberNo;
	private String memberName;
	private String nickName;
	private String email;
	private String phone;
	private String reason;
	private String blackDate;
}

package com.btc.member.model.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SNSMember {

	private int memberNo;
	private String snsCode;
	private String snsType;
	private String Name;
	private String email;
	private String nickName;
	private Date snsConnectDate;
}

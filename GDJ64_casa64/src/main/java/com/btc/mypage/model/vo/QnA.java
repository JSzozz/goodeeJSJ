package com.btc.mypage.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QnA {
	private int questionNo;
	private int memberNo;
	private String categoryName;
	private Date questionDate;
	private String answer;
	private String questionContent;
	private String visibleCk;
	private String questionTitle;
	private String nickname;

}

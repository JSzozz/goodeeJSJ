package com.btc.notice.model.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Qna {
	private int questionNo;
	private int memberNo;
	private String categoryName;
	private Date questionDate;
	private String answer;
	private String questionContent;
	private char visibleCk;
	private String questionTitle;
}

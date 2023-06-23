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

public class Notice {
	private int noticeNo;
	private String noticeTitle;
	private String noticeContent;
	private Date dateCreated;
	private Date dateModified;
	private int noticeReadCount;
}

package com.btc.notice.model.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoticeComment {
	private int noticeCommentNo;
	private int level;
	private String noticeCommentWriter;
	private String noticeCommentContent;
	private Date noticeCommentDate;
	private int noticeCommentRef;
	private int noticeRef;
}

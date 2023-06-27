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
public class QnaComment {
	private int QnaCommentNo;
	private int QnaCommentLevel;
	private String QnaCommentWriter;
	private String QnaCommentContent;
	private Date QnaCommentDate;
	private int QnaCommentRef;
	private int QnaRef;
}

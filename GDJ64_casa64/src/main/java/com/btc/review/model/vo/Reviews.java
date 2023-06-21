package com.btc.review.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Reviews {
	private int no;
	private String title;
	private String contents;
	private String roomName;
	private String nickName;
	private int views;
	private Date dateCreated;
	private Date dateModified;
	private Date dateDeleted;
	private int isDeleted;
	private int roomNo;
	private int memberNo;
	private int bookingNo;
	private String reply;
	private Date lastReplyDate;
	private int isReply;
}

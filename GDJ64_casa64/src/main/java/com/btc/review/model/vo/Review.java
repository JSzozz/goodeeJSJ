<<<<<<< HEAD:GDJ64_casa64/src/main/java/com/btc/review/model/vo/Reviews.java
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
=======
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
public class Review {
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
>>>>>>> 4bea568d5e1f87a91dbe2a3e1507c02d64d09337:GDJ64_casa64/src/main/java/com/btc/review/model/vo/Review.java

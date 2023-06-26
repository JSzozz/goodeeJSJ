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
public class ReviewImages {
	private int no;
	private int reviewNo;
	private String fileName;
	private String saveFileName;
	private Date dateCreated;

}

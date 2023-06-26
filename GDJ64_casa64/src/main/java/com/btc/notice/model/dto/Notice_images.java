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

public class Notice_images {
	private int fileNo;
	private String saveFilename;
	private String fileName;
	private Date dateCreated;
	private int noticeNo;
}

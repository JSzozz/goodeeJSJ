package com.web.board.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor

public class Board {

	private int boardNo;
	private String boardTitle;
	private String boardWriter; // 관습적 사용 방식
//	private Member boardWriter; //참조관계에 따른 자바클래스
	private String boardContent;
	private String boardOriginalFilename;
	private String boardRenamedFilename;
	private Date boardDate;
	private int boardReadcount;
}

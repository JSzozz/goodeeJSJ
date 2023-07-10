package com.mybatis.model.dto;

import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Board {

	private int boardNo; // v
	private String boardTitle;
	private Member boardWriter;
	private String boardContent;
	private String boardOriginalFilename;
	private String boardRenamedFileName;
	private Date boardDate;
	private int boardReadcount;
	
	private List<BoardComment> boardComments;
	
}

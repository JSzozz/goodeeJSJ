package com.mybatis.model.service;

import java.util.List;

import com.mybatis.model.dto.Board;

public interface BoardService {

	List<Board> searchBoard(int no);
	
}

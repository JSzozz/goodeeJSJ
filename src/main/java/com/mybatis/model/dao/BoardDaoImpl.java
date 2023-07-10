package com.mybatis.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.mybatis.model.dto.Board;

public class BoardDaoImpl implements BoardDao {

	@Override
	public List<Board> searchBoard(SqlSession session, int no){
		return session.selectList("board.searchBoard", no);
	}
	
}

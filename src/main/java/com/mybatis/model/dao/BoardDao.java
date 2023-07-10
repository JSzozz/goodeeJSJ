package com.mybatis.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.mybatis.model.dto.Board;

public interface BoardDao {

	public List<Board> searchBoard(SqlSession session, int no);
}

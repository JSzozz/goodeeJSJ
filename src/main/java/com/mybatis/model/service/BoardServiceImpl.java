package com.mybatis.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.mybatis.common.SessionTemplate;
import com.mybatis.model.dao.BoardDao;
import com.mybatis.model.dao.BoardDaoImpl;
import com.mybatis.model.dto.Board;

public class BoardServiceImpl implements BoardService {

	private BoardDao dao= new BoardDaoImpl();
	
	public List<Board> searchBoard(int no){
		SqlSession session= SessionTemplate.getWebSession();
		List<Board> boards=dao.searchBoard(session, no);
		session.close();
		return boards;
	}
	
}

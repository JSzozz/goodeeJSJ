package com.bs.spring.board.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.bs.spring.board.model.dao.BoardDao;
import com.bs.spring.board.model.dto.Board;

@Service
public class BoardServiceImpl implements BoardService {
	
	private BoardDao dao;
	private SqlSession session;
	
	
	public BoardServiceImpl(BoardDao dao, SqlSession session) {
		this.dao = dao;
		this.session = session;
	}

	@Override
	public int insertBoard(Board b) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Board> selectBoardAll(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return dao.selectBoardAll(session, param);
	}

	@Override
	public int selectBoardCount() {
		// TODO Auto-generated method stub
		return dao.selectBoardCount(session);
	}

	@Override
	public Board selectBoardById(int no) {
		// TODO Auto-generated method stub
		return dao.selectBoardByNo(session, no);
	}

}

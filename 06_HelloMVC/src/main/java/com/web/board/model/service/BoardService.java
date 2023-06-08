package com.web.board.model.service;

import static com.web.common.JDBCTemplate.close;
import static com.web.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.web.board.model.dao.BoardDao;
import com.web.board.model.vo.Board;

public class BoardService {

	private BoardDao dao=new BoardDao();
	
	public List<Board> selectBoard(int cPage, int numPerpage){
		Connection conn=getConnection();
		List<Board> list= dao.selectBoard(conn, cPage, numPerpage);
		close(conn);
		return list;
	}
	
}

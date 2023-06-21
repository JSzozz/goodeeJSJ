package com.btc.board.model.service;

import static com.btc.common.JDBCTemplate.close;
import static com.btc.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.btc.notice.controller.Board;

public class BoardService {
	private BoardDao dao = new BoardDao();
	
	public List<Board> selectBoard(int cPage,int numPerpage){
		Connection conn=getConnection();
		List<Board> list=dao.selectBoard(conn,cPage,numPerpage);
		close(conn);
		return list;
	}
}

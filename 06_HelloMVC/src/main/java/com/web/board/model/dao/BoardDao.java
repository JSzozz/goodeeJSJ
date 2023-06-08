package com.web.board.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.web.board.model.vo.Board;

import static com.web.common.JDBCTemplate.*;

public class BoardDao {

	private final Properties sql=new Properties();
	{
		String path=BoardDao.class.getResource("/sql/board/boardsql.properties").getPath();
		try {
			sql.load(new FileReader(path));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private Board getBoard(ResultSet rs) throws SQLException {
		return Board.builder()
				.boardNo(rs.getInt("board_no"))
				.boardTitle(rs.getString("board_title"))
				.boardWriter(rs.getString("board_writer"))
				.boardContent(rs.getString("board_content"))
				.boardOriginalFilename(rs.getString("board_original_filename"))
				.boardRenamedFilename(rs.getString("board_renamed_filename"))
				.build();
	}
	
	public List<Board> selectBoard(Connection conn, int cPage, int numPerpage){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Board> list=new ArrayList();
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectBoard"));//SELECT * FROM BOARD
			pstmt.setInt(1, (cPage-1)*numPerpage+1);
			pstmt.setInt(2, cPage*numPerpage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				list.add(getBoard(rs));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return list;
	}

}

package com.web.board.model.dao;

import static com.web.common.JDBCTemplate.close;

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
import com.web.board.model.vo.BoardComment;

public class BoardDao {

	private final Properties sql = new Properties();
	{
		String path = BoardDao.class.getResource("/sql/board/boardsql.properties").getPath();
		// servlet이 아니라 context를 불러올 수 없어서 이와 같은 방식이 사용됨!
		try {
			sql.load(new FileReader(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Board getBoard(ResultSet rs) throws SQLException {
		return Board.builder().boardNo(rs.getInt("board_no")).boardTitle(rs.getString("board_title"))
				.boardWriter(rs.getString("board_writer")).boardContent(rs.getString("board_content"))
				.boardOriginalFilename(rs.getString("board_original_filename"))
				.boardRenamedFilename(rs.getString("board_renamed_filename")).boardDate(rs.getDate("board_date"))
				.boardReadcount(rs.getInt("board_readcount")).build();
	}
	
	private BoardComment getBoardComment(ResultSet rs) throws SQLException{
		return BoardComment.builder()
				.boardCommentNo(rs.getInt("board_comment_no"))
				.level(rs.getInt("board_comment_level"))
				.boardCommentWriter(rs.getString("board_comment_writer"))
				.boardCommentContent(rs.getString("board_comment_content"))
				.boardCommentDate(rs.getDate("board_comment_date"))
				.boardCommentRef(rs.getInt("board_comment_ref"))
				.boardRef(rs.getInt("board_ref"))
				.build();
	}

	public List<Board> selectBoard(Connection conn, int cPage, int numPerpage) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Board> list = new ArrayList();
		try {
			pstmt = conn.prepareStatement(sql.getProperty("selectBoard"));// SELECT * FROM (SELECT ROWNUM AS RNUM, B.*
																			// FROM(SELECT * FROM Board ORDER BY
																			// BOARD_DATE DESC)B) WHERE RNUM BETWEEN ?
																			// AND ?
			pstmt.setInt(1, (cPage - 1) * numPerpage + 1);
			pstmt.setInt(2, cPage * numPerpage);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(getBoard(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	public int selectNoticeCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("selectBoardCount"));// SELECT COUNT(*) AS RN FROM NOTICE
			rs = pstmt.executeQuery();
			if (rs.next())
				result = rs.getInt("RN");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}

	public Board selectBoardByNo(Connection conn, int no) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Board b = null;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("selectBoardByNo"));
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if (rs.next())
				b = getBoard(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return b;

	}
	
	public List<BoardComment> selectBoardCommentByNo(Connection conn, int boardNo) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<BoardComment> list=new ArrayList();
		try {
			pstmt = conn.prepareStatement(sql.getProperty("selectBoardCommentByNo"));//select * from board_comment where board_ref=?
			pstmt.setInt(1,boardNo);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				list.add(getBoardComment(rs));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return list;
	}

	public int updateBoardReadCount(Connection conn, int no) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("updateBoardReadCount"));// UPDATE BOARD SET BOARD_READCOUNT =
																					// BOARD_READCOUNT+1 WHERE
																					// BOARD_NO=?
			pstmt.setInt(1, no);
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int insertBoard(Connection conn, Board b) {
		return 0;
	}

	public int insertBoardComment(Connection conn, BoardComment bc) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("insertBoardCimment"));
			// INSERT INTO BOARD_COMMENT VALUES(SEQ_BOARD_COMMENT_NO.NEXTVAL,?,?,?,?,?,DEFAULT)
			pstmt.setInt(1, bc.getLevel());
			pstmt.setString(2, bc.getBoardCommentWriter());
			pstmt.setString(3, bc.getBoardCommentContent());
			pstmt.setInt(4, bc.getBoardRef());
			pstmt.setString(5, bc.getBoardCommentRef()==0?null:String.valueOf(bc.getBoardCommentRef()));//v : 오라클은 자동형변환을 이뤄줌(int ->string)
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
}

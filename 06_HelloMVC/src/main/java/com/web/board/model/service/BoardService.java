package com.web.board.model.service;

import static com.web.common.JDBCTemplate.close;
import static com.web.common.JDBCTemplate.commit;
import static com.web.common.JDBCTemplate.getConnection;
import static com.web.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.web.board.model.dao.BoardDao;
import com.web.board.model.vo.Board;
import com.web.board.model.vo.BoardComment;

public class BoardService {

	private BoardDao dao=new BoardDao();
	
	public List<Board> selectBoard(int cPage, int numPerpage){
		Connection conn=getConnection();
		List<Board> list= dao.selectBoard(conn, cPage, numPerpage);
		close(conn);
		return list;
	}
	public 	int selectBoardCount() {
		Connection conn=getConnection();
		int result=dao.selectNoticeCount(conn);
		close(conn);
		return result;
	}
	public Board selectBoardByNo(int no, boolean isRead) {
		Connection conn=getConnection();
		Board b=dao.selectBoardByNo(conn, no);
		if(b!=null&&isRead==false) {
			int result=dao.updateBoardReadCount(conn,no);
			if(result>0) {
				commit(conn);
				b.setBoardReadcount(b.getBoardReadcount()+1);//db불일치 가능성 있음
			}
			else rollback(conn);
			//중요한 업데이트인 경우이면 Exception 처리도 필요하다
		}
		close(conn);
		return b;
	}
	public List<BoardComment> selectBoardCommentByNo(int boardNo) {
		Connection conn=getConnection();
		List<BoardComment> list=dao.selectBoardCommentByNo(conn, boardNo);
		close(conn);
		return list;
	}
	public int insertBoard(Board b) {
		Connection conn=getConnection();
		int result=dao.insertBoard(conn, b);
		if(result>0)commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	public int updateBoard(Board b) {
		return 0;
	}
	public int deleteBoard(int no) {
		return 0;
	}
	public int insertBoardComment(BoardComment bc) {
		Connection conn=getConnection();
		int result=dao.insertBoardComment(conn, bc);
		if(result>0)commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
}

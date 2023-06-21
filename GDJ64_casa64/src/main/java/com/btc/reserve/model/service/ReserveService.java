package com.btc.reserve.model.service;

import static com.btc.common.JDBCTemplate.close;
import static com.btc.common.JDBCTemplate.commit;
import static com.btc.common.JDBCTemplate.getConnection;
import static com.btc.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.btc.reserve.model.dao.ReserveDao;
import com.btc.rooms.model.vo.Room;
public class ReserveService {
	private ReserveDao dao=new ReserveDao();
	
//	public List<Room> selectAllRoom(){
//		Connection conn=getConnection();
//		List<Room> list=dao.selectAllRoom(conn);
//		close(conn);
//		return list;
//	}
	
	
//	private NoticeDao dao=new NoticeDao();
//	
//	public List<Notice> selectNotice(int cPage, int numPerpage){
//		Connection conn=getConnection();
//		List<Notice> list=dao.selectNotice(conn,cPage,numPerpage);
//		close(conn);
//		return list;
//	}
//	public 	int selectNoticeCount() {
//		Connection conn=getConnection();
//		int result=dao.selectNoticeCount(conn);
//		close(conn);
//		return result;
//	}
//	
//	public int insertNotice(Notice n) {
//		Connection conn=getConnection();
//		int result=dao.insertNotice(conn,n);
//		if(result>0)commit(conn);
//		else rollback(conn);
//		close(conn);
//		return result;
//	}
//	
//	public Notice selectNoticeByNo(int no) {
//		Connection conn=getConnection();
//		Notice n=dao.selectNoticeByNo(conn, no);
//		close(conn);
//		return n;
//	}
}

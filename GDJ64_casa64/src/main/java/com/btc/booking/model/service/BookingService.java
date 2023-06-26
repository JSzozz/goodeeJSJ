package com.btc.booking.model.service;

import static com.btc.common.JDBCTemplate.close;
import static com.btc.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.btc.booking.model.dao.BookingDao;
import com.btc.booking.model.vo.Booking;
import com.btc.rooms.model.vo.Room;


public class BookingService {
	private BookingDao dao=new BookingDao();
	
	public List<Room> selectAllRoom(){
		Connection conn=getConnection();
		List<Room> list=dao.selectAllRoom(conn);
		close(conn);
		return list;
	}
	
	public List<Booking> selectAllBooking(){
		Connection conn=getConnection();
		List<Booking> list=dao.selectAllBooking(conn);
		close(conn);
		return list;
	}
	
	public int searchRoomNo(String roomNm) {
		Connection conn=getConnection();
		int roomNo=dao.searchRoomNo(conn, roomNm);
		close(conn);
		return roomNo;
	}
	public int insertBooking(Booking b) {
		Connection conn=getConnection();
		int result=dao.insertBooking(conn, b);
		close(conn);
		return result;
	}
	
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

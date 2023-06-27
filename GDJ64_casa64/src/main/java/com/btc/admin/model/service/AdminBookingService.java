package com.btc.admin.model.service;

import static com.btc.common.JDBCTemplate.close;
import static com.btc.common.JDBCTemplate.commit;
import static com.btc.common.JDBCTemplate.getConnection;
import static com.btc.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import com.btc.admin.model.dao.AdminBookingDao;
import com.btc.booking.model.vo.Booking;

public class AdminBookingService {
	private static AdminBookingService service = new AdminBookingService();
	private AdminBookingService() {};
	public static AdminBookingService getBookingService() {
		return service;
	}
	
	public List<Booking> allBookingList(int cPage,int numPerPage) {
		Connection conn = getConnection();
		List<Booking> bookingList = AdminBookingDao.getBookingDao().getAllBookingList(conn, cPage, numPerPage);
		close(conn);
		return bookingList;
	}
	
	public Booking infoBooking(int bookingNo) {
		Connection conn = getConnection();
		Booking infoBooking = AdminBookingDao.getBookingDao().getInfoBooking(conn, bookingNo);
		close(conn);
		return infoBooking;
	}
	
	public List<Booking> conditionBookingList(String state, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Booking> bookingList = AdminBookingDao.getBookingDao().getConditionBookingList(conn, state, cPage, numPerPage);
		close(conn);
		return bookingList;
	}
	
	public int bookingCount() {
		Connection conn = getConnection();
		int count = AdminBookingDao.getBookingDao().getBookingCount(conn);
		close(conn);
		return count;
	}
	
	public List<Booking> searchBookingList(String state, String type, String value, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Booking> bookingList = AdminBookingDao.getBookingDao().getSearchBookingList(conn, state, type, value, cPage, numPerPage);
		close(conn);
		return bookingList;
	}
	
	public int cancelBooking(int bookingNo) {
		Connection conn = getConnection();
		int result = AdminBookingDao.getBookingDao().getCancelBookingResult(conn, bookingNo);
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public List<Booking> todayBookingList(int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Booking> bookingList = AdminBookingDao.getBookingDao().getTodayBookingList(conn, cPage, numPerPage);
		close(conn);
		return bookingList;
	}
	
	public List<Booking> oneWeekAndMonthBookingList(String searchDate, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Booking> bookingList = AdminBookingDao.getBookingDao().getOneWeekAndMonthBookingList(conn, searchDate, cPage, numPerPage);
		close(conn);
		return bookingList;
	}
	
	public int todayBookingCount() {
		Connection conn = getConnection();
		int count = AdminBookingDao.getBookingDao().getTodayBookingCount(conn);
		close(conn);
		return count;
	}
	
	public int oneWeekAndMonthBookingCount(String searchDate) {
		Connection conn = getConnection();
		int count = AdminBookingDao.getBookingDao().getOneWeekAndMonthBookingCount(conn, searchDate);
		close(conn);
		return count;
	}
}

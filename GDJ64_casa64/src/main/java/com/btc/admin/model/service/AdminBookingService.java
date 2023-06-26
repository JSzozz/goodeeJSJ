package com.btc.admin.model.service;

import static com.btc.common.JDBCTemplate.close;
import static com.btc.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.btc.admin.model.dao.AdminBookingDao;
import com.btc.booking.model.vo.Booking;

public class AdminBookingService {
	private static AdminBookingService service = new AdminBookingService();
	private AdminBookingService() {};
	public static AdminBookingService getBookingService() {
		return service;
	}
	
	public List<Booking> allBookingList() {
		Connection conn = getConnection();
		List<Booking> bookingList = AdminBookingDao.getBookingDao().getAllBookingList(conn);
		close(conn);
		return bookingList;
	}
	
	public Booking infoBooking(int bookingNo) {
		Connection conn = getConnection();
		Booking infoBooking = AdminBookingDao.getBookingDao().getInfoBooking(conn, bookingNo);
		close(conn);
		return infoBooking;
	}
	
	public List<Booking> searchBookingList(String state) {
		Connection conn = getConnection();
		List<Booking> bookingList = AdminBookingDao.getBookingDao().getSearchBookingList(conn, state);
		close(conn);
		return bookingList;
	}
}

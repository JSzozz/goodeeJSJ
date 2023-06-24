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
	
	public List<Booking> allBookingList(){
		Connection conn = getConnection();
		List<Booking> allBookingList = AdminBookingDao.getBookingDao().getAllBookingList(conn);
		close(conn);
		return allBookingList;
	}
}

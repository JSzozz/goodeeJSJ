package com.btc.admin.model.service;

import static com.btc.common.JDBCTemplate.close;
import static com.btc.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.btc.admin.model.dao.AdminMainPageDao;
import com.btc.admin.model.vo.Chart;
import com.btc.booking.model.vo.Booking;
import com.btc.rooms.model.vo.Room;

public class AdminMainPageService {
	private static AdminMainPageService service = new AdminMainPageService();
	private AdminMainPageService() {};
	public static AdminMainPageService getAdminService() {
		return service;
	}
	
	public int memberCount() {
		Connection conn = getConnection();
		int result = AdminMainPageDao.getMainPageDao().getMemberCount(conn);
		close(conn);
		return result;
	}
	
	public int roomCount() {
		Connection conn = getConnection();
		int result = AdminMainPageDao.getMainPageDao().getRoomCount(conn);
		close(conn);
		return result;
	}
	
	public int bookingRoomCount() {
		Connection conn = getConnection();
		int result = AdminMainPageDao.getMainPageDao().getBookingRoomCount(conn);
		close(conn);
		return result;
	}
	
	public int requestCancelRoomCount() {
		Connection conn = getConnection();
		int result = AdminMainPageDao.getMainPageDao().getRequestCancelRoomCount(conn);
		close(conn);
		return result;
	}
	
	public int updateCardCount(String cardName) {
		Connection conn = getConnection();
		int result = AdminMainPageDao.getMainPageDao().getUpdateCardCount(conn, cardName);
		close(conn);
		return result;
	}
	
	public List<Room> roomList() {
		Connection conn = getConnection();
		List<Room> list = AdminMainPageDao.getMainPageDao().getRoomList(conn);
		close(conn);
		return list;
	}
	
	public List<Chart> chartBookingCount() {
		Connection conn = getConnection();
		List<Chart> chart = AdminMainPageDao.getMainPageDao().getChartBookingCount(conn);
		close(conn);
		return chart;
	}
	
	public List<Chart> chartBookingPayment() {
		Connection conn = getConnection();
		List<Chart> chart = AdminMainPageDao.getMainPageDao().getChartBookingPayment(conn);
		close(conn);
		return chart;
	}
	
	
}

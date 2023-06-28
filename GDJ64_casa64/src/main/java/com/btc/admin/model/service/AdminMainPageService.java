package com.btc.admin.model.service;

import java.sql.Connection;

import com.btc.admin.model.dao.AdminMainPageDao;

import static com.btc.common.JDBCTemplate.*;

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
}

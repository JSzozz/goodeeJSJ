package com.btc.admin.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import static com.btc.common.JDBCTemplate.*;

public class AdminMainPageDao {
	private static AdminMainPageDao dao = new AdminMainPageDao();
	private AdminMainPageDao() {};
	public static AdminMainPageDao getMainPageDao() {
		return dao;
	}
	
	Properties query = new Properties();
	{		
		String path = AdminMainPageDao.class.getResource("/sql/admin/sql_adminMainPage.properties").getPath();
		
		try {
			query.load(new FileReader(path));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public int getMemberCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(query.getProperty("memberCount"));
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return result;
	}
	
	public int getRoomCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(query.getProperty("roomCount"));
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return result;
	}
	
	public int getBookingRoomCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(query.getProperty("bookingRoomCount"));
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return result;
	}
	
	public int getRequestCancelRoomCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(query.getProperty("requestCancelRoomCount"));
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return result;
	}
	
	public int getUpdateCardCount(Connection conn, String cardName) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		
		String countQuery = "";
		switch(cardName) {
			case "가입회원": countQuery = "memberCount"; break;
			case "객실": countQuery = "roomCount"; break;
			case "예약객실": countQuery = "bookingRoomCount"; break;
			case "예약취소": countQuery = "requestCancelRoomCount"; break;
		}
		
		try {
			pstmt = conn.prepareStatement(query.getProperty(countQuery));
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return result;
	}
}

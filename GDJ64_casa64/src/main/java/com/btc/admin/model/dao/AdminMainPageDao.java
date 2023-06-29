package com.btc.admin.model.dao;

import static com.btc.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.btc.admin.model.vo.Chart;
import com.btc.booking.model.vo.Booking;
import com.btc.member.model.dto.Member;
import com.btc.rooms.model.vo.Room;

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
	
	public List<Room> getRoomList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Room> list = new ArrayList<Room>();
		
		try {
			pstmt = conn.prepareStatement(query.getProperty("getRoomList"));
			rs = pstmt.executeQuery();

			while(rs.next()) {
				list.add(getRoom(rs));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return list;
	}
	
	public List<Chart> getChartBookingCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Chart> chart = new ArrayList<Chart>();
		
		try {
			pstmt = conn.prepareStatement(query.getProperty("getChartBookingCount"));
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				chart.add(getChart(rs));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return chart;
	}
	
	public List<Chart> getChartBookingPayment(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Chart> chart = new ArrayList<Chart>();
		
		try {
			pstmt = conn.prepareStatement(query.getProperty("getChartBookingPayment"));
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				chart.add(getChart(rs));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return chart;
	}
	
	
	
	private Room getRoom(ResultSet rs) throws SQLException {
		return Room.builder()
				.roomNo(rs.getInt("ROOM_NO"))
				.roomName(rs.getString("ROOM_NAME"))
				.roomPrice(rs.getInt("ROOM_PRICE"))
				.roomSize(rs.getInt("ROOM_SIZE"))
				.roomCap(rs.getInt("ROOM_CAP"))
				.roomMaxCap(rs.getInt("ROOM_MAX_CAP"))
				.bookable(rs.getString("BOOKABLE").charAt(0))
				.dateCreated(rs.getDate("DATE_CREATED"))
				.dateModified(rs.getDate("DATE_MODIFIED"))
				.roomDescription(rs.getString("ROOM_DESCRIPTION"))
				.build();
	}
	
	private Chart getChart(ResultSet rs) throws SQLException {
		return Chart.builder()
				.roomName(rs.getString(1))
				.roomInfo(rs.getInt(2))
				.build();
	}
}

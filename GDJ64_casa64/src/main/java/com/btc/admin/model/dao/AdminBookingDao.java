package com.btc.admin.model.dao;

import static com.btc.common.JDBCTemplate.close;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.btc.booking.model.vo.Booking;
import com.btc.member.model.dto.Member;
import com.btc.rooms.model.vo.Room;

public class AdminBookingDao {
	private static AdminBookingDao dao = new AdminBookingDao();
	private AdminBookingDao() {};
	public static AdminBookingDao getBookingDao() {
		return dao;
	}
	
	Properties query = new Properties();
	
	{
		String path = AdminBookingDao.class.getResource("/sql/admin/sql_adminBooking.properties").getPath();
		try {
			query.load(new FileReader(path));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Booking> getAllBookingList(Connection conn,int cPage,int numPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Booking> bookingList = new ArrayList<Booking>();
		
		try {
			pstmt = conn.prepareStatement(query.getProperty("showAllBookingList"));
			pstmt.setInt(1, (cPage-1)*numPerPage+1);
			pstmt.setInt(2, cPage*numPerPage);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				bookingList.add(getBooking(rs));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return bookingList;
	}
	
	public Booking getInfoBooking(Connection conn, int bookingNo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Booking booking = null;
		
		try {
			pstmt = conn.prepareStatement(query.getProperty("infoBooking"));
			pstmt.setInt(1, bookingNo);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				booking = getBooking(rs);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return booking;
	}
	
	public List<Booking> getConditionBookingList(Connection conn, String state, int cPage, int numPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Booking> bookingList = new ArrayList<Booking>();
		
		try {
			pstmt = conn.prepareStatement(query.getProperty("conditionBookingList"));
			pstmt.setString(1, state);
			pstmt.setInt(2, (cPage-1)*numPerPage+1);
			pstmt.setInt(3, cPage*numPerPage);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				bookingList.add(getBooking(rs));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return bookingList;
	}
	
	public int getBookingCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(query.getProperty("bookingCount"));
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result=rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	public List<Booking> getSearchBookingList(Connection conn, String state, String type, String value, int cPage, int numPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String searchQuery = "";
		List<Booking> bookingList = new ArrayList<Booking>();
		
		if(state.equals("전체")) {
			switch(type) {
				case "user-name": searchQuery = "searchAllMemberName"; break;
				case "room-name": searchQuery = "searchAllRoomName"; break;
				case "user-email": searchQuery = "searchAllMemberEmail"; break;
				case "user-phone": searchQuery = "searchAllMemberPhone"; break;
			}
		}else {
			switch(type) {
				case "user-name": searchQuery = "searchMemberName"; break;
				case "room-name": searchQuery = "searchRoomName"; break;
				case "user-email": searchQuery = "searchMemberEmail"; break;
				case "user-phone": searchQuery = "searchMemberPhone"; break;
			}
		}
		
		try {
			if(state.equals("전체")) {
				pstmt = conn.prepareStatement(query.getProperty(searchQuery));
				pstmt.setString(1, "%" + value + "%");
				pstmt.setInt(2, (cPage-1)*numPerPage+1);
				pstmt.setInt(3, cPage*numPerPage);
			}else {
				pstmt = conn.prepareStatement(query.getProperty(searchQuery));
				pstmt.setString(1, state);
				pstmt.setString(2, "%" + value + "%");
				pstmt.setInt(3, (cPage-1)*numPerPage+1);
				pstmt.setInt(4, cPage*numPerPage);
			}

			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				bookingList.add(getBooking(rs));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return bookingList;
	}
	
	public int getCancelBookingResult(Connection conn, int bookingNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(query.getProperty("cancelBooking"));
			pstmt.setInt(1, bookingNo);			
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}
	
	public List<Booking> getTodayBookingList(Connection conn, int cPage, int numPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Booking> bookingList = new ArrayList<Booking>();
		
		try {
			pstmt = conn.prepareStatement(query.getProperty("todayBookingList"));
			pstmt.setInt(1, (cPage-1)*numPerPage+1);
			pstmt.setInt(2, cPage*numPerPage);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				bookingList.add(getBooking(rs));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return bookingList;
	}
	
	public List<Booking> getOneWeekAndMonthBookingList(Connection conn, String searchDate, int cPage, int numPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String searchQuery = "";
		List<Booking> bookingList = new ArrayList<Booking>();
		
		if(searchDate.equals("week")) {
			searchQuery = "oneWeekBooking";
		}else if(searchDate.equals("month")) {
			searchQuery = "oneMonthBooking";
		}
		try {
			
			pstmt = conn.prepareStatement(query.getProperty(searchQuery));
			pstmt.setInt(1, (cPage-1)*numPerPage+1);
			pstmt.setInt(2, cPage*numPerPage);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				bookingList.add(getBooking(rs));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return bookingList;
	}
	
	public int getOneWeekAndMonthBookingCount(Connection conn, String searchDate) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String searchQuery = "";
		int result = 0;
		
		if(searchDate.equals("week")) {
			searchQuery = "oneWeekBookingCount";
		}else if(searchDate.equals("month")) {
			searchQuery = "oneMonthBookingCount";
		}
		
		try {
			pstmt = conn.prepareStatement(query.getProperty(searchQuery));
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result=rs.getInt(1);
			}			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	public int getTodayBookingCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int result = 0;
		try {
			
			pstmt = conn.prepareStatement(query.getProperty("todayBookingCount"));
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result=rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	private Booking getBooking(ResultSet rs) throws SQLException {
		return Booking.builder()
				.bookingNo(rs.getInt("BOOKING_NO"))
				.member(Member.builder()
						.memberName(rs.getString("member_name"))
						.email(rs.getString("email"))
						.phone(rs.getString("phone"))
						.build())
				.room(Room.builder()
						.roomName(rs.getString("ROOM_NAME"))
						.build())
				.checkIn(rs.getDate("CHECK_IN"))
				.checkOut(rs.getDate("CHECK_OUT"))
				.guestAdult(rs.getInt("GUEST_ADULT"))
				.guestChild(rs.getInt("GUEST_CHILD"))
				.guestInfant(rs.getInt("GUEST_INFANT"))
				.bookingPrice(rs.getInt("BOOKING_PRICE"))
				.bookingComment(rs.getString("BOOKING_COMMENT"))
				.bookingState(rs.getString("BOOKING_STATE"))
				.paymentDate(rs.getDate("PAYMENT_DATE"))
				.build();
	}
}

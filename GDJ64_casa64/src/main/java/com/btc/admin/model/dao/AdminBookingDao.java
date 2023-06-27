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
			
			if(rs.next()) booking = getBooking(rs);
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
			pstmt.setInt(1, (cPage-1)*numPerPage+1);
			pstmt.setInt(2, cPage*numPerPage);
			pstmt.setString(3, state);
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
			
			if(rs.next()) result=rs.getInt(1);
			
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

		try {
			if(state.equals("전체")) {
				if(type.equals("user-name")) {
					searchQuery = "searchAllMemberName";
				}else if(type.equals("room-name")) {
					searchQuery = "searchAllRoomName";
				}else if(type.equals("user-email")) {
					searchQuery = "searchAllMemberEmail";
				}else if(type.equals("user-phone")) {
					searchQuery = "searchAllMemberPhone";
				}
				pstmt = conn.prepareStatement(query.getProperty(searchQuery));
				pstmt.setInt(1, (cPage-1)*numPerPage+1);
				pstmt.setInt(2, cPage*numPerPage);
				pstmt.setString(3, value);
			}else {
				if(type.equals("user-name")) {
					searchQuery = "searchMemberName";
				}else if(type.equals("room-name")) {
					searchQuery = "searchRoomName";
				}else if(type.equals("user-email")) {
					searchQuery = "searchMemberEmail";
				}else if(type.equals("user-phone")) {
					searchQuery = "searchMemberPhone";
				}
				pstmt = conn.prepareStatement(query.getProperty(searchQuery));
				pstmt.setInt(1, (cPage-1)*numPerPage+1);
				pstmt.setInt(2, cPage*numPerPage);
				pstmt.setString(3, state);
				pstmt.setString(4, "%" + value + "%");
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
			// pstmt.setDate(3, java.sql.Date.valueOf(java.time.LocalDate.now()));
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
		
		try {
			if(searchDate.equals("week")) {
				searchQuery = "oneWeekBooking";
			}else if(searchDate.equals("month")) {
				searchQuery = "oneMonthBooking";
			}
			
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
	
	private Booking getBooking(ResultSet rs) throws SQLException {
		return Booking.builder()
				.bookingNo(rs.getInt("BOOKING_NO"))
				.member(Member.builder().memberName(rs.getString("member_name"))
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

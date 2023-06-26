package com.btc.admin.model.dao;

import static com.btc.common.JDBCTemplate.close;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.btc.booking.model.vo.Booking;

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
	
	public List<Booking> getAllBookingList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Booking> bookingList = new ArrayList<Booking>();
		
		try {
			pstmt = conn.prepareStatement(query.getProperty("showAllBookingList"));
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
	
	public List<Booking> getSearchBookingList(Connection conn, String state) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Booking> bookingList = new ArrayList<Booking>();
		
		try {
			pstmt = conn.prepareStatement(query.getProperty("searchBookingList"));
			pstmt.setString(1, state);
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
	
	private Booking getBooking(ResultSet rs) throws SQLException {
		return Booking.builder()
				.bookingNo(rs.getInt("BOOKING_NO"))
				.memberNo(rs.getInt("MEMBER_NO"))
				.roomNo(rs.getInt("ROOM_NO"))
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

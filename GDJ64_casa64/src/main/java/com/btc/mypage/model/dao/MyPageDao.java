package com.btc.mypage.model.dao;

import static com.btc.common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.btc.mypage.model.vo.Booking;

public class MyPageDao {
	
	public List<Booking> selectBookingMyPage(Connection conn, int memberNo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Booking> list = new ArrayList();

		try {
			String sql = getBasicQuery(); // 실행할 기본 쿼리
			sql += " AND BOOKING.MEMBER_NO = ? ";
//         sql += " ORDER BY BOOKING.NO DESC";

			pstmt = conn.prepareStatement(sql); // 실제 쿼리 들어가고
			pstmt.setInt(1, memberNo);
			rs = pstmt.executeQuery(); // 쿼리 실행

			while (rs.next()) { // rs 다음 값이 있을 경우
				Booking bookings = getBooking(rs);
				list.add(bookings); //
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	private Booking getBooking(ResultSet rs) throws SQLException {
		return Booking.builder()
				.bookingNo(rs.getInt("BOOKING_NO"))
				.memberNo(rs.getInt("MEMBER_ID"))
				.checkIn(rs.getDate("CHECK_IN"))
				.checkOut(rs.getDate("CHECK_OUT"))
				.checkOut(rs.getDate("CHECK_OUT"))
				.bookingPrice(rs.getInt("BOOKING_PRICE"))
				.build();
		
//		 private int bookingNo;
//		   private int memberNo;
//		   private int roomNo;
//		   private Date checkIn;
//		   private Date checkOut;
//		   private int guestAdult;
//		   private int guestChild;
//		   private int guestInfant;
//		   private int xtraNo;
//		   private int bookingPrice;
//		   private String paid;
	}

	public String getBasicQuery() {
		return "SELECT B.*, R.NO FROM BOOKING B " +
				"JOIN MEMBER M ON M.EMAIL = B.MEMBER_NO " +
				"LEFT JOIN REVIEW R ON R.MEMBER_NO = M.MEMBER_NO AND R.BOOKING_NO = B.BOOKING_NO " +
				"WHERE B.MEMBER_NO = '?' AND R.NO IS NULL"; // 실행할 기본 쿼리
	}

}

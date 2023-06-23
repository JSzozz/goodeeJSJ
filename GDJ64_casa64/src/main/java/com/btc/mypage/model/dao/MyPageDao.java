package com.btc.mypage.model.dao;

import static com.btc.common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import com.btc.booking.model.vo.Booking;
import com.btc.review.model.vo.Review;

public class MyPageDao {
	
//	public List<Booking> selectBookingMyPage(Connection conn) {
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		List<Booking> list = new ArrayList();
//
//		try {
//			String sql = getBasicQuery(); // 실행할 기본 쿼리
//			sql += " AND Booking.MEMBER_ID = yooonnara@gmail.com ";
////         sql += " ORDER BY REVIEWS.NO DESC";
//
//			pstmt = conn.prepareStatement(sql); // 실제 쿼리 들어가고
////			pstmt.setInt(1, memberNo);
//			rs = pstmt.executeQuery(); // 쿼리 실행
//
//			while (rs.next()) { // rs 다음 값이 있을 경우
//				Booking bookings = getBooking(rs);
//				list.add(bookings); //
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			close(rs);
//			close(pstmt);
//		}
//		return list;
//	}
	
//	private Booking getBooking(ResultSet rs) throws SQLException {
////      Reviews reviews = new Reviews();
//
//		return Booking.builder().no(rs.getInt("NO")).title(rs.getString("TITLE")).contents(rs.getString("CONTENTS"))
//				.views(rs.getInt("VIEWS")).dateCreated(rs.getDate("DATE_CREATED"))
//				.dateModified(rs.getDate("DATE_MODIFIED")).dateDeleted(rs.getDate("DATE_DELETED"))
//				.isDeleted(rs.getInt("IS_DELETED")).roomName(rs.getString("ROOM_NAME"))
//				.nickName(rs.getString("NICKNAME")).roomNo(rs.getInt("ROOM_NO")).memberNo(rs.getInt("MEMBER_NO"))
//				.bookingNo(rs.getInt("BOOKING_NO")).adminReply(rs.getString("ADMIN_REPLY"))
//				.lastReplyDate(rs.getDate("LAST_REPLY_DATE")).isReply(rs.getInt("IS_REPLY")).build();
//	}

	public String getBasicQuery() {
		return "SELECT R.* FROM BOOKING B " +
				"JOIN MEMBER M ON M.EMAIL = RV.MEMBER_ID " +
				"JOIN REVIEW R ON R.MEMBER_NO = M.MEMBERNO AND R.BOOKING_NO = B.BOOKING_NO "; // 실행할 기본 쿼리
	}

}

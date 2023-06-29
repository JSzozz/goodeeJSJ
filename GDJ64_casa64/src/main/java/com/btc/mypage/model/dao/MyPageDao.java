package com.btc.mypage.model.dao;

import static com.btc.common.JDBCTemplate.close;
import static com.btc.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.btc.mypage.model.vo.Booking;
import com.btc.mypage.model.vo.QnA;

public class MyPageDao {
	
	// 마이페이지 예약내역 조회
	public List<Booking> selectBookingMyPage(Connection conn, int memberNo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Booking> list = new ArrayList();

		try {
			String sql = getBasicQuery();

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
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
	
	// 마이페이지 문의사항 조회
	public List<QnA> selectQnAMyPage(Connection conn, int memberNo){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<QnA> list = new ArrayList();

		try {
			String sql = getQnAQuery(); 
			sql += " ORDER BY Q.QUESTION_DATE ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberNo);
			rs = pstmt.executeQuery(); 

			while (rs.next()) {
				QnA qna = getQnA(rs);
				list.add(qna); //
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	public int reservationCancellation (Connection conn, int bookingNo){
		PreparedStatement pstmt = null;
		int result = 0; 

		try {
			conn = getConnection(); 
			
			String sql = " UPDATE BOOKING B SET B.BOOKING_STATE = '취소요청' "
						+ "	WHERE B.BOOKING_NO = ?";
			pstmt = conn.prepareStatement(sql);
//	         
			pstmt.setInt(1, bookingNo);
	
			result = pstmt.executeUpdate(); 
			if (result > 0) {
				conn.commit();
			} else {
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	
	public QnA getQnA(ResultSet rs) throws SQLException {
		return QnA.builder()
				.questionNo(rs.getInt("QUESTION_NO"))
				.memberNo(rs.getInt("MEMBER_NO"))
				.categoryName(rs.getString("CATEGORY_NAME"))
				.questionDate(rs.getDate("QUESTION_DATE"))
				.answer(rs.getString("ANSWER"))
				.questionContent(rs.getString("QUESTION_CONTENT"))
				.visibleCk(rs.getString("VISIBLE_CK"))
				.questionTitle(rs.getString("QUESTION_TITLE"))
				.nickname(rs.getString("NICKNAME"))
				.build();
				
	}
	
	public Booking getBooking(ResultSet rs) throws SQLException {
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
				.roomName(rs.getString("ROOM_NAME"))
				.reviewNo(rs.getInt("REVIEW_NO"))
				
				.build();
	}


	public String getBasicQuery() {
		return "SELECT B.*, R.ROOM_NAME, RV.NO REVIEW_NO FROM BOOKING B "
				+ " JOIN ROOM R ON R.ROOM_NO = B.ROOM_NO "
				+ " LEFT JOIN REVIEW RV ON RV.BOOKING_NO = B.BOOKING_NO AND RV.IS_DELETED = 0 "
				+ " WHERE B.MEMBER_NO = ?";
	}
	
	public String getQnAQuery() {
		return "SELECT Q.*, M.NICKNAME FROM QNA Q "
				+ " JOIN MEMBER M ON Q.MEMBER_NO = M.MEMBER_NO "
				+ " WHERE Q.MEMBER_NO = ?";
	}

}

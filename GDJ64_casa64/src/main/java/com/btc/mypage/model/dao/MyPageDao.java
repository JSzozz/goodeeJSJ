package com.btc.mypage.model.dao;

import static com.btc.common.JDBCTemplate.close;

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
	
	public List<Booking> selectBookingMyPage(Connection conn, int memberNo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Booking> list = new ArrayList();

		try {
			String sql = getBasicQuery(); // 실행할 기본 쿼리

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
	
	public List<QnA> selectQnAMyPage(Connection conn, int memberNo){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<QnA> list = new ArrayList();

		try {
			String sql = getQnAQuery(); // 실행할 기본 쿼리

			pstmt = conn.prepareStatement(sql); // 실제 쿼리 들어가고
			pstmt.setInt(1, memberNo);
			rs = pstmt.executeQuery(); // 쿼리 실행

			while (rs.next()) { // rs 다음 값이 있을 경우
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
				// 이용상태(결제대기, 결제완료, 예약완료, 사용완료, 취소요청, 취소완료) 컬럼 추가해야함
				.build();
	}
//				   private String bookingComment;
//				   private String bookingState;
//				   private Date paymentDate;

	public String getBasicQuery() {
		return "SELECT B.*, R.ROOM_NAME, RV.NO REVIEW_NO FROM BOOKING B "
				+ " JOIN ROOM R ON R.ROOM_NO = B.ROOM_NO "
				+ " LEFT JOIN REVIEW RV ON RV.BOOKING_NO = B.BOOKING_NO "
				+ " WHERE B.MEMBER_NO = ?";
	}
	
	public String getQnAQuery() {
		return "SELECT Q.*, M.NICKNAME FROM QNA Q "
				+ " JOIN MEMBER M ON Q.MEMBER_NO = M.MEMBER_NO "
				+ " WHERE Q.MEMBER_NO = ?";
	}

}

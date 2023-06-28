package com.btc.review.model.dao;

import static com.btc.common.JDBCTemplate.close;
import static com.btc.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.btc.mypage.model.dao.MyPageDao;
import com.btc.mypage.model.vo.Booking;
import com.btc.review.model.vo.Review;
import com.btc.review.model.vo.ReviewImages;
import com.btc.rooms.model.vo.Room;

public class ReviewDao {

	/**
	 * 이용후기 전체 리스트
	 * 
	 * @param conn
	 * @param type
	 * @param keyword
	 * @param roomNo
	 * @return
	 */
	public List<Review> selectReviews(Connection conn, String type, String keyword, String roomNo, int page,
			int postsPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Review> list = new ArrayList();

		try {
			String sql = getBasicQuery(); // 실행할 기본 쿼리
			// 방 선택된 게 있을 경우
			if (roomNo != null && roomNo != "" && type != null && type.equals("rooms")) {
				sql += " AND ROOM.ROOM_NO = " + roomNo;
			}
			// 검색 타입과 검색어가 있을 경우
			if (type != null && type != "" && keyword != null && keyword != "" && !type.equals("rooms")) {
				if (type.equals("title")) { // 검색 타입이 제목일 경우
					sql += " AND LOWER(REVIEW.TITLE) ";
				} else if (type.equals("contents")) { // 검색 타입이 내용일 경우
					sql += " AND LOWER(REVIEW.CONTENTS) ";
				} else if (type.equals("writer")) { // 검색 타입이 작성자 일 경우
					sql += " AND LOWER(MEMBER.NICKNAME) ";
				}
				sql += " LIKE '%" + keyword.toLowerCase() + "%' ";
			}
			sql += " ORDER BY REVIEW.NO DESC";
			String query = "SELECT * FROM (SELECT ROWNUM AS RNUM, R.* FROM (" + sql
					+ ") R ) WHERE RNUM BETWEEN ? AND ?";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, (page - 1) * postsPerPage + 1);
			pstmt.setInt(2, page * postsPerPage);
			rs = pstmt.executeQuery(); 

			while (rs.next()) {
				Review reviews = getReviews(rs);
				list.add(reviews); //
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}

		return list;

	}

	/**
	 * 이용후기 뷰 페이지 (리뷰번호로 조회)
	 * 
	 * @param conn
	 * @param reviewNo
	 * @return
	 */
	public Review getReviewView(Connection conn, int reviewNo) {

		String sql = getBasicQuery();
		sql += " AND REVIEW.NO = ?";

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		Review reviews = new Review();
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reviewNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				reviews = getReviews(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}

		return reviews;
	}

	/**
	 * 이용후기 작성
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	public int insertReviews(Connection conn, Review reviews) {
		PreparedStatement pstmt = null;
		int result = 0; 

		try {
			conn = getConnection(); 
			String sql = "INSERT INTO REVIEW (NO,TITLE,CONTENTS,VIEWS,IS_DELETED,ROOM_NO,MEMBER_NO,BOOKING_NO, DATE_CREATED, DATE_MODIFIED) "
					+ "VALUES (REVIEW_SEQ.NEXTVAL,?,?,?,?,?,?,?, SYSTIMESTAMP, SYSTIMESTAMP)";

			pstmt = conn.prepareStatement(sql); 
//         4. 쿼리에 파라미터 셋팅
			pstmt.setString(1, reviews.getTitle());
			pstmt.setString(2, reviews.getContents());
			pstmt.setInt(3, reviews.getViews());
			pstmt.setInt(4, reviews.getIsDeleted());
			pstmt.setInt(5, reviews.getRoomNo());
			pstmt.setInt(6, reviews.getMemberNo());
			pstmt.setInt(7, reviews.getBookingNo());

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

	/**
	 * 리뷰 수정 (제목, 내용)
	 * @param conn
	 * @param reviews
	 * @return
	 */
	public int updateReviews(Connection conn, Review reviews) {
		PreparedStatement pstmt = null;
		int result = 0; 

		try {
			conn = getConnection(); 
			String sql = "UPDATE REVIEW SET TITLE = ?, CONTENTS = ?, DATE_MODIFIED = SYSTIMESTAMP "
					+ "WHERE REVIEW.NO = ?";
			pstmt = conn.prepareStatement(sql); 
			pstmt.setString(1, reviews.getTitle());
			pstmt.setString(2, reviews.getContents());
			pstmt.setInt(3, reviews.getNo());
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

	/**
	 * 마이페이지용 이용 후기 리스트
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	public List<Review> selectReviewsMypage(Connection conn, int memberNo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Review> list = new ArrayList();

		try {
			String sql = getBasicQuery(); // 실행할 기본 쿼리
			sql += " AND REVIEW.MEMBER_NO = ? ";
            sql += " ORDER BY REVIEW.DATE_CREATED ";

			pstmt = conn.prepareStatement(sql); 
			pstmt.setInt(1, memberNo);
			rs = pstmt.executeQuery(); 

			while (rs.next()) { 
				Review reviews = getReviews(rs);
				list.add(reviews); //
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	/**
	 * 이용후기 리스트에서 객실 검색 위한 객실 리스트
	 * @param conn
	 * @return
	 */
	public List<Room> selectAllRoom(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Room> list = new ArrayList();

		try {
			String sql = "SELECT R.ROOM_NO, R.ROOM_NAME FROM ROOM R " + "WHERE R.BOOKABLE='Y'"; // 실행할 기본 쿼리

			pstmt = conn.prepareStatement(sql); 
			rs = pstmt.executeQuery();

			while (rs.next()) { 
				Room rooms = new Room();
				rooms.setRoomNo(rs.getInt("ROOM_NO"));
				rooms.setRoomName(rs.getString("ROOM_NAME"));
				list.add(rooms); //
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}

	/**
	 * 이용후기 번호로 저장된 이미지 있으면 불러오기
	 * @param conn
	 * @param reviewNo
	 * @return
	 */
	public List<ReviewImages> getReviewImages(Connection conn, int reviewNo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ReviewImages> list = new ArrayList();

		try {
			String sql = "SELECT * FROM REVIEW_IMAGES RI WHERE RI.REVIEW_NO = ?";

			pstmt = conn.prepareStatement(sql); 
			pstmt.setInt(1, reviewNo);
			rs = pstmt.executeQuery(); 

			while (rs.next()) { 
				ReviewImages ri = new ReviewImages();
				ri.setNo(rs.getInt("NO"));
				ri.setFileName(rs.getString("FILENAME"));
				ri.setSaveFileName(rs.getString("SAVE_FILENAME"));
				ri.setDateCreated(rs.getDate("DATE_CREATED"));
				list.add(ri); //
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;

	}
	
	/**
	 * 이용후기 작성하기 > 모달 > 작성할 수 있는 예약 내역 리스트
	 * @param conn
	 * @param memberNo
	 * @return
	 */
	public List<Booking> getBookingList(Connection conn, int memberNo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Booking> bkList = new ArrayList();

		try {
			String sql = "SELECT B.* , RM.ROOM_NAME, R.NO REVIEW_NO "
					+ "FROM BOOKING B "
					+ "JOIN MEMBER M ON M.MEMBER_NO = B.MEMBER_NO "
					+ "JOIN ROOM RM ON RM.ROOM_NO = B.ROOM_NO "
					+ "LEFT JOIN REVIEW R ON R.MEMBER_NO = M.MEMBER_NO AND R.BOOKING_NO = B.BOOKING_NO AND R.IS_DELETED = 0 "
					+ "WHERE B.MEMBER_NO = ? AND B.BOOKING_STATE = '이용완료' AND R.NO IS NULL"; // 실행할 기본 쿼리

			pstmt = conn.prepareStatement(sql); 
			pstmt.setInt(1, memberNo);
			rs = pstmt.executeQuery(); 

			while (rs.next()) { 
				Booking bookings = new MyPageDao().getBooking(rs);
				bkList.add(bookings);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return bkList;
	}

	/**
	 * 이용후기 이미지 업로드
	 * @param conn
	 * @param imgList
	 * @param reviews
	 * @return
	 */
	public int uploadImages(Connection conn, List<ReviewImages> imgList, Review reviews) {
		PreparedStatement pstmt = null;
		int result = 0;
		int reviewNo = 0;
		int sum = 0;
		// 1. reivew_images 에 insert
		// 2. list 의 개수만큼 insert 가 되어야 함 : 반복문을 통해 인서트
		// 3. insert 전에 review_no 를 먼저 구해야함. (완료)
		try {
			conn = getConnection(); // DB 접속
			// review no 를 가져오는 쿼리 작성 및 실행.
			String sql = "SELECT R.NO FROM REVIEW R WHERE R.BOOKING_NO = ? AND R.MEMBER_NO = ? ";

			// 반복문으로 insert 쿼리 작성 및 실행
			// 3. 쿼리 작성

			pstmt = conn.prepareStatement(sql); 
//	         4. 쿼리에 파라미터 셋팅

			pstmt.setInt(1, reviews.getBookingNo());
			pstmt.setInt(2, reviews.getMemberNo());
			ResultSet rs = pstmt.executeQuery(); 
			while (rs.next()) {
				reviewNo = rs.getInt("NO");
			}
			if (reviewNo > 0) {
				sql = "INSERT INTO REVIEW_IMAGES VALUES(REVIEW_IMAGES_SEQ.NEXTVAL, ?, ?, ?, SYSTIMESTAMP)";
				for (ReviewImages ri : imgList) {
					pstmt = conn.prepareStatement(sql); 
					pstmt.setInt(1, reviewNo);
					pstmt.setString(2, ri.getFileName());
					pstmt.setString(3, ri.getSaveFileName());
					result = pstmt.executeUpdate();
					sum += result;
				}
			}
			if (sum > 0) {
				conn.commit();
			} else {
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return sum;
	}

	/**
	 * 이용후기 페이징 처리 위한 전체 카운트 조회
	 * @param conn
	 * @param type
	 * @param keyword
	 * @param roomNo
	 * @return
	 */
	public int selectReviewsTotalCount(Connection conn, String type, String keyword, String roomNo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		String sql = getBasicQuery(); // 실행할 기본 쿼리
		// 방 선택된 게 있을 경우
		if (roomNo != null && roomNo != "" && type != null && type.equals("rooms")) {
			sql += " AND ROOM.ROOM_NO = " + roomNo;
		}
		// 검색 타입과 검색어가 있을 경우
		if (type != null && type != "" && keyword != null && keyword != "" && !type.equals("rooms")) {
			if (type.equals("title")) { // 검색 타입이 제목일 경우
				sql += " AND LOWER(REVIEW.TITLE) ";
			} else if (type.equals("contents")) { // 검색 타입이 내용일 경우
				sql += " AND LOWER(REVIEW.CONTENTS) ";
			} else if (type.equals("writer")) { // 검색 타입이 작성자 일 경우
				sql += " AND LOWER(MEMBER.NICKNAME) ";
			}
			sql += " LIKE '%" + keyword.toLowerCase() + "%' ";
		}
		sql += " ORDER BY REVIEW.NO DESC";
		String query = "SELECT COUNT(1) RN FROM ( " + sql + " ) ";
		try {
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			if (rs.next())
				result = rs.getInt("RN");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}

	/**
	 * 이용후기 뷰 > 관리자 댓글 작성 / 수정 / 삭제
	 * @param conn
	 * @param adminReply
	 * @param reviewNo
	 * @param isReply
	 * @return
	 */
	public int updateAdminReply(Connection conn, String adminReply, int reviewNo, int isReply) {
		PreparedStatement pstmt = null;
		int result = 0; 

		try {
			conn = getConnection(); 
			String sql = "UPDATE REVIEW SET ADMIN_REPLY = ?, IS_REPLY = ?, LAST_REPLY_DATE = SYSTIMESTAMP WHERE NO = ?";
			pstmt = conn.prepareStatement(sql); 
			pstmt.setString(1, adminReply);
			pstmt.setInt(2, isReply);
			pstmt.setInt(3, reviewNo);
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

	/**
	 * 이용후기 삭제 (블라인드)
	 * @param conn
	 * @param reviewNo
	 * @return
	 */
	public int deleteReview(Connection conn, int reviewNo) {
		PreparedStatement pstmt = null;
		int result = 0; 

		try {
			conn = getConnection(); 
			String sql = "UPDATE REVIEW SET IS_DELETED = 1, DATE_DELETED = SYSTIMESTAMP"
					+ "		WHERE REVIEW.NO = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reviewNo);
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

	/**
	 * 이용후기 조회수 증가
	 * @param conn
	 * @param reviewNo
	 * @return
	 */
	public int reviewCountUpdate(Connection conn, int reviewNo) {
		PreparedStatement pstmt = null;
		int result = 0;

		try {
			conn = getConnection();
			String sql = "UPDATE REVIEW SET VIEWS = VIEWS+1 WHERE NO = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reviewNo);
			result = pstmt.executeUpdate();
			if (result > 0) {
				conn.commit();
			} else {
				conn.rollback();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	private Review getReviews(ResultSet rs) throws SQLException {
//      Reviews reviews = new Reviews();

		return Review.builder().no(rs.getInt("NO")).title(rs.getString("TITLE")).contents(rs.getString("CONTENTS"))
				.views(rs.getInt("VIEWS")).dateCreated(rs.getDate("DATE_CREATED"))
				.dateModified(rs.getDate("DATE_MODIFIED")).dateDeleted(rs.getDate("DATE_DELETED"))
				.isDeleted(rs.getInt("IS_DELETED")).roomName(rs.getString("ROOM_NAME"))
				.nickName(rs.getString("NICKNAME")).roomNo(rs.getInt("ROOM_NO")).memberNo(rs.getInt("MEMBER_NO"))
				.bookingNo(rs.getInt("BOOKING_NO")).adminReply(rs.getString("ADMIN_REPLY"))
				.lastReplyDate(rs.getDate("LAST_REPLY_DATE")).isReply(rs.getInt("IS_REPLY")).build();
	}

	/**
	 * 이용후기 조회 시 기본 쿼리
	 * @return
	 */
	public String getBasicQuery() {
		return " SELECT REVIEW.* , MEMBER.NICKNAME, ROOM.ROOM_NAME "
			+  " FROM REVIEW "
			+  " JOIN MEMBER ON MEMBER.MEMBER_NO = REVIEW.MEMBER_NO "
			+  " JOIN ROOM ON ROOM.ROOM_NO = REVIEW.ROOM_NO WHERE 1=1 AND REVIEW.IS_DELETED = 0 "; // 실행할 기본 쿼리
	}
}
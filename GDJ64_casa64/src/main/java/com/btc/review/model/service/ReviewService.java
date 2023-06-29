package com.btc.review.model.service;

import static com.btc.common.JDBCTemplate.close;
import static com.btc.common.JDBCTemplate.getConnection;
import static com.btc.common.JDBCTemplate.commit;
import static com.btc.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.btc.mypage.model.vo.Booking;
import com.btc.review.model.dao.ReviewDao;
import com.btc.review.model.vo.Review;
import com.btc.review.model.vo.ReviewImages;
import com.btc.rooms.model.vo.Room;

public class ReviewService {
	private  ReviewDao dao = new ReviewDao();
	
	/**
	 * 이용후기 전체 리스트
	 * 
	 * @param type
	 * @param keyword
	 * @param roomNo
	 * @return
	 */
	public List<Review> selectReviews(String type, String keyword, String roomNo, int page, int postsPerPage){
		Connection conn = getConnection(); 
		List<Review> list = dao.selectReviews(conn, type, keyword, roomNo, page, postsPerPage);
		close(conn); 
		return list;
	}
	/**
	 * 이용후기 페이징 처리 위한 전체 카운트 조회
	 * @param type
	 * @param keyword
	 * @param roomNo
	 * @return
	 */
	public int selectReviewsTotalCount(String type, String keyword, String roomNo){
		Connection conn = getConnection(); 
		int totalCount = dao.selectReviewsTotalCount(conn, type, keyword, roomNo); // conn 생성해서 dao로 전달하는 역할
		close(conn); 
		return totalCount;
	}
	/**
	 * 이용후기 뷰 페이지 (리뷰번호로 조회)
	 * @param reviewNo
	 * @return
	 */
	public Review getReviewView(int reviewNo){
		Connection conn = getConnection();
		Review reviews = dao.getReviewView(conn, reviewNo);
		close(conn);
		return reviews;
		
	}
	/**
	 * 이용후기 작성
	 * @param reviews
	 * @return
	 */
	public int insertReviews(Review reviews) {
		Connection conn = getConnection();
		int result = dao.insertReviews(conn, reviews);
		close(conn);
		return result;
	}
	/**
	 * 이용후기 수정 (제목, 내용)
	 * @param reviews
	 * @return
	 */
	public int updateReviews(Review reviews) {
		Connection conn = getConnection();
		int result = dao.updateReviews(conn, reviews);
		close(conn);
		return result;
	}
	/**
	 * 이용후기 이미지 파일 업로드
	 * @param imgList
	 * @param reviews
	 * @return
	 */
	public int uploadImages(List<ReviewImages> imgList, Review reviews) {
		Connection conn = getConnection();
		int result = dao.uploadImages(conn, imgList, reviews);
		close(conn);
		return result;
	}
	/**
	 * 이용후기 번호로 저장된 이미지 있으면 불러오기
	 * @param reviewNo
	 * @return
	 */
	public List<ReviewImages> getReviewImages(int reviewNo) {
		Connection conn = getConnection(); 
		List<ReviewImages> list = dao.getReviewImages(conn, reviewNo); 
		close(conn); 
		return list;
	}
	/**
	 * 이용후기 삭제 (블라인드)
	 * @param reviewNo
	 * @return
	 */
	public int deleteReview(int reviewNo) {
		Connection conn = getConnection();
		int result = dao.deleteReview(conn, reviewNo);
		close(conn);
		return result;
	}
	/**
	 * 이용후기 조회수 증가
	 * @param isRead
	 * @param reviewNo
	 * @return
	 */
	public int reviewCountUpdate(boolean isRead, int reviewNo) {
		Connection conn = getConnection();
		int result = 0;
		if(!isRead) {
			result = dao.reviewCountUpdate(conn, reviewNo);
		}
		close(conn);
		return result;
	}
	/**
	 * 이용후기 작성하기 > 모달 > 작성할 수 있는 예약 내역 리스트
	 * @param memberNo
	 * @return
	 */
	public List<Booking> getBookingList(int memberNo){
		Connection conn = getConnection(); 
		List<Booking> list = dao.getBookingList(conn, memberNo); 
		close(conn); 
		return list;
		
	}
	/**
	 * 이용후기 뷰 > 관리자 댓글 작성 / 수정 / 삭제
	 * @param adminReply
	 * @param reviewNo
	 * @param isReply
	 * @return
	 */
	public int updateAdminReply(String adminReply, int reviewNo, int isReply) {
		Connection conn = getConnection();
		int result = dao.updateAdminReply(conn, adminReply, reviewNo, isReply);
		close(conn);
		return result;
	}
	
	/**
	 * 마이페이지용 이용 후기 리스트
	 * @param memberNo
	 * @return
	 */
	public List<Review> selectReviewsMypage(int memberNo){
		Connection conn = getConnection();
		List<Review> list = dao.selectReviewsMypage(conn, memberNo); 
		close(conn);
		return list;
	}
	/**
	 * 이용후기 리스트에서 객실 검색 위한 객실 리스트
	 * @return
	 */
	public List<Room> selectAllRoom() {
		Connection conn = getConnection(); 
		List<Room> list = dao.selectAllRoom(conn); 
		close(conn); 
		return list;
	}
	
	

}

package com.btc.review.model.service;

import static com.btc.common.JDBCTemplate.close;
import static com.btc.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.btc.mypage.model.vo.Booking;
import com.btc.review.model.dao.ReviewDao;
import com.btc.review.model.vo.Review;
import com.btc.review.model.vo.ReviewImages;
import com.btc.rooms.model.vo.Room;

public class ReviewService {
	private  ReviewDao dao = new ReviewDao();
	
	public List<Review> selectReviews(String type, String keyword, String roomNo){
		Connection conn = getConnection(); // db 접속 시작
		List<Review> list = dao.selectReviews(conn, type, keyword, roomNo); // conn 생성해서 dao로 전달하는 역할
		close(conn); // db 접속 종료
		return list;
	}
	
	public Review getReviewView(int reviewNo){
		Connection conn = getConnection();
		Review reviews = dao.getReviewView(conn, reviewNo);
		close(conn);
		return reviews;
		
	}
	
	public int insertReviews(Review reviews) {
		Connection conn = getConnection();
		int result = dao.insertReviews(conn, reviews);
		close(conn);
		return result;
	}
	
	public int updateReviews(Review reviews) {
		Connection conn = getConnection();
		int result = dao.updateReviews(conn, reviews);
		close(conn);
		return result;
	}
	
	public int uploadImages(List<ReviewImages> imgList, Review reviews) {
		Connection conn = getConnection();
		int result = dao.uploadImages(conn, imgList, reviews);
		close(conn);
		return 0;
	}
	
	public List<ReviewImages> getReviewImages(int reviewNo) {
		Connection conn = getConnection(); // db 접속 시작
		List<ReviewImages> list = dao.getReviewImages(conn, reviewNo); // conn 생성해서 dao로 전달하는 역할
		close(conn); // db 접속 종료
		return list;
	}
	
	public List<Booking> getBookingList(int memberNo){
		Connection conn = getConnection(); // db 접속 시작
		List<Booking> list = dao.getBookingList(conn, memberNo); // conn 생성해서 dao로 전달하는 역할
		close(conn); // db 접속 종료
		return list;
		
	}
	
	
	// 마이페이지용 리뷰 리스트 ( 파라미터는 memberNo)
	public List<Review> selectReviewsMypage(int memberNo){
		Connection conn = getConnection(); // db 접속 시작
		List<Review> list = dao.selectReviewsMypage(conn, memberNo); // conn 생성해서 dao로 전달하는 역할
		close(conn); // db 접속 종료
		return list;
	}
	
	public List<Room> selectAllRoom() {
		Connection conn = getConnection(); // db 접속 시작
		List<Room> list = dao.selectAllRoom(conn); // conn 생성해서 dao로 전달하는 역할
		close(conn); // db 접속 종료
		return list;
	}

}

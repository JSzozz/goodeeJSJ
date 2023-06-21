package com.btc.review.model.service;

import static com.btc.common.JDBCTemplate.close;
import static com.btc.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.btc.review.model.dao.ReviewDao;
import com.btc.review.model.vo.Reviews;

public class ReviewService {
	private  ReviewDao dao = new ReviewDao();
	
	public List<Reviews> selectReviews(String type, String keyword, String roomNo){
		Connection conn = getConnection(); // db 접속 시작
		List<Reviews> list = dao.selectReviews(conn, type, keyword, roomNo); // conn 생성해서 dao로 전달하는 역할
		close(conn); // db 접속 종료
		return list;
	}
	
	public Reviews getReviewView(int reviewNo){
		Connection conn = getConnection();
		Reviews reviews = dao.getReviewView(conn, reviewNo);
		close(conn);
		return reviews;
		
	}
	
	public int insertReviews(Reviews reviews) {
		Connection conn = getConnection();
		int result = dao.insertReviews(conn, reviews);
		close(conn);
		return result;
	}
	// 마이페이지용 리뷰 리스트 ( 파라미터는 memberNo)
	public List<Reviews> selectReviewsMypage(int memberNo){
		Connection conn = getConnection(); // db 접속 시작
		List<Reviews> list = dao.selectReviewsMypage(conn, memberNo); // conn 생성해서 dao로 전달하는 역할
		close(conn); // db 접속 종료
		return list;
	}

}

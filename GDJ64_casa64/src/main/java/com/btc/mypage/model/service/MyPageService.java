package com.btc.mypage.model.service;

import static com.btc.common.JDBCTemplate.close;
import static com.btc.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.btc.mypage.model.dao.MyPageDao;
import com.btc.mypage.model.vo.Booking;
import com.btc.mypage.model.vo.QnA;

public class MyPageService {
	private  MyPageDao dao = new MyPageDao();
	
	// 마이페이지 예약내역 조회
	public List<Booking> selectBookingMyPage(int memberNo){
		Connection conn = getConnection(); 
		List<Booking> list = dao.selectBookingMyPage(conn, memberNo);
		close(conn); 
		return list;
	}
	
	// 마이페이지 문의사항 조회
	public List<QnA> selectQnAMypage(int memberNo){
		Connection conn = getConnection(); 
		List<QnA> list = dao.selectQnAMyPage(conn, memberNo); 
		close(conn); 
		return list;
	}
	
	// 예약취소
	public int reservationCancellation(int bookingNo){
		Connection conn = getConnection(); 
		int result = dao.reservationCancellation(conn, bookingNo); 
		close(conn);
		return result;
	}

}

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
	
	public List<Booking> selectBookingMyPage(int memberNo){
		Connection conn = getConnection(); // db 접속 시작
		List<Booking> list = dao.selectBookingMyPage(conn, memberNo);
		close(conn); // db 접속 종료
		return list;
	}
	
	public List<QnA> selectQnAMypage(int memberNo){
		Connection conn = getConnection(); // db 접속 시작
		List<QnA> list = dao.selectQnAMyPage(conn, memberNo); // conn 생성해서 dao로 전달하는 역할
		close(conn); // db 접속 종료
		return list;
	}

}

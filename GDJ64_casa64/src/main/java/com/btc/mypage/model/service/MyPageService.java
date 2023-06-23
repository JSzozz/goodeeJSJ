package com.btc.mypage.model.service;

import static com.btc.common.JDBCTemplate.close;
import static com.btc.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.btc.mypage.model.dao.MyPageDao;
import com.btc.reserve.model.vo.Booking;

public class MyPageService {
	private  MyPageDao dao = new MyPageDao();
	
//	public List<Booking> selectBookingMyPage(){
//		Connection conn = getConnection(); // db 접속 시작
//		List<Booking> list = dao.selectBookingMyPage(conn);
//		close(conn); // db 접속 종료
//		return list;
//	}

}

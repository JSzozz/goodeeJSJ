package com.web.reserve.model.dao;

import static com.web.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.web.notice.model.vo.Notice;
import com.web.room.model.vo.Room;

public class ReserveDao {
	
	private Properties sql=new Properties();
	
	public ReserveDao() {
		String path=ReserveDao.class
				.getResource("/sql/reserve/reservesql.properties")
				.getPath();
		try {
			sql.load(new FileReader(path));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	private Room getRoom(ResultSet rs) throws SQLException{
		return Room.builder()
	         .roomNo(rs.getInt("room_no"))
	         .roomName(rs.getString("room_name"))
	         .roomPrice(rs.getInt("room_price"))
	         .roomSize(rs.getString("room_size"))
	         .roomCap(rs.getInt("room_cap"))
	         .roomMaxCap(rs.getInt("room_max_cap"))
	         .bookable(rs.getString("bookable"))
	         .roomImage(rs.getString("room_image"))
	         .dateCreated(rs.getDate("date_created"))
	         .dateModified(rs.getDate("date_modified"))
	         .roomDescription(rs.getString("room_description"))
	         .build();
	}
	
	public List<Room> selectAllRoom(Connection conn){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Room> list=new ArrayList();
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectAllRoom"));
			//SELECT * FROM ROOM
			rs=pstmt.executeQuery();
			while(rs.next()) {
				list.add(getRoom(rs));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return list;	
	}
	
//	private Notice getNotice(ResultSet rs) throws SQLException{
//		return Notice.builder()
//				.noticeNo(rs.getInt("notice_no"))
//				.noticeTitle(rs.getString("notice_title"))
//				.noticeWriter(rs.getString("notice_writer"))
//				.noticeContent(rs.getString("notice_content"))
//				.noticeDate(rs.getDate("notice_date"))
//				.filePath(rs.getString("filepath"))
//				.build();
//	}
//
//	public List<Notice> selectNotice(Connection conn, int cPage, int numPerpage){
//		PreparedStatement pstmt=null;
//		ResultSet rs=null;
//		List<Notice> list=new ArrayList();
//		try {
//			pstmt=conn.prepareStatement(sql.getProperty("selectNotice"));
//			//SELECT * FROM (SELECT ROWNUM AS RNUM, N.* FROM(SELECT * FROM NOTICE ORDER BY NOTICE_DATE DESC)N) WHERE RNUM BETWEEN ? AND ?
//			pstmt.setInt(1, (cPage-1)*numPerpage+1);
//			pstmt.setInt(2, cPage*numPerpage);
//			rs=pstmt.executeQuery();
//			while(rs.next()) {
//				list.add(getNotice(rs));
//			}
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}finally {
//			close(rs);
//			close(pstmt);
//		}return list;	
//	}
//	
//	public int selectNoticeCount(Connection conn) {
//		PreparedStatement pstmt=null;
//		ResultSet rs=null;
//		int result=0;
//		try {
//			pstmt=conn.prepareStatement(sql.getProperty("selectNoticeCount"));
//			rs=pstmt.executeQuery();
//			if(rs.next()) result=rs.getInt("RN");
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}finally {
//			close(rs);
//			close(pstmt);
//		}return result;
//	}
//	
//	public int insertNotice(Connection conn, Notice n) {
//		PreparedStatement pstmt=null;
//		int result=0;
//		try{
//			pstmt=conn.prepareStatement(sql.getProperty("insertNotice"));//INSERT INTO NOTICE VALUES(SEQ_NOTICE_NO.NEXTVAL,?,?,?,DEFAULT,?,DEFAULT)
//			pstmt.setString(1, n.getNoticeTitle());
//			pstmt.setString(2, n.getNoticeWriter());
//			pstmt.setString(3, n.getNoticeContent());
//			pstmt.setString(4, n.getFilePath());
//			result=pstmt.executeUpdate();
//			
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}finally {
//			close(pstmt);
//		}return result;
//	}
//	
//	public Notice selectNoticeByNo(Connection conn, int no) {
//		PreparedStatement pstmt=null;
//		ResultSet rs=null;
//		Notice n= null;
//		try {
//			pstmt=conn.prepareStatement(sql.getProperty("selectNoticeByNo"));//SELECT * FROM NOTICE WHERE NOTICE_NO=?
//			/* pstmt.setInt(1, n.getNoticeNo()); */
//			pstmt.setInt(1, no);
//			rs=pstmt.executeQuery();
//			if(rs.next()) {
//				n=getNotice(rs);
//			}
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}finally {
//			close(rs);
//			close(pstmt);
//		}return n;
//	}
}

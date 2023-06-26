package com.btc.notice.model.dao;

import static com.btc.common.JDBCTemplate.*;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.btc.notice.model.dao.NoticeDao;
import com.btc.notice.model.dto.Notice;
import com.btc.notice.model.dto.Notice_images;

public class NoticeDao {
private Properties sql=new Properties();
	
	public NoticeDao() {
		String path=NoticeDao.class.getResource("/sql/notice/sql_notice.properties").getPath();
		try {
			sql.load(new FileReader(path));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private Notice getNotice(ResultSet rs) throws SQLException{
		return Notice.builder()
				.noticeNo(rs.getInt("notice_no"))
				.noticeTitle(rs.getString("notice_title"))
				.noticeContent(rs.getString("notice_content"))
				.dateCreated(rs.getDate("date_created"))
				.dateModified(rs.getDate("date_modified"))
				.noticeReadCount(rs.getInt("notice_readcount"))
				.build();
	}
	
	private Notice_images getNoticeImage(ResultSet rs) throws SQLException{
		return Notice_images.builder()
				.fileNo(rs.getInt("file_no"))
				.saveFilename(rs.getString("save_filename"))
				.fileName(rs.getString("filename"))
				.dateCreated(rs.getDate("date_created"))
				.noticeNo(rs.getInt("notice_no"))
				.build();
	}
	
	public List<Notice> selectNotice(Connection conn, int cPage, int numPerpage){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Notice> list=new ArrayList();
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectNotice"));
			pstmt.setInt(1,(cPage-1)*numPerpage+1);
			pstmt.setInt(2, cPage*numPerpage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				list.add(getNotice(rs));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	public int selectNoticeCount(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectNoticeCount"));
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result=rs.getInt("RN"); //테이블에서 별칭을 저장한 
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return result;
	}
	
	public List<Notice> searchNotice(Connection conn,Map pagemap, Map map){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Notice> result=new ArrayList();
		int cPage=(int)pagemap.get("cPage");
		int numPerpage=(int)pagemap.get("numPerpage");
		try {
			String sqlc=sql.getProperty("searchType");
			sqlc=sqlc.replace("#type", (String)map.get("type"));
			pstmt=conn.prepareStatement(sqlc);
			pstmt.setString(1, "%"+(String)map.get("keyword")+"%");
			pstmt.setInt(2, (cPage-1)*numPerpage+1);
			pstmt.setInt(3, cPage*numPerpage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				result.add(getNotice(rs));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	public int selectNoticeSearchCount(Connection conn, Map map) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		try {
			String sqlc=sql.getProperty("selectNoticeSearchCount");
			sqlc=sqlc.replace("#type", (String)map.get("type"));
			pstmt=conn.prepareStatement(sqlc);
			pstmt.setString(1, map.get("type").equals("gender")? (String)map.get("keyword"):"%"+(String)map.get("keyword")+"%");
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result=rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	public Notice selectNoticeByNo(Connection conn, int no){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Notice b=null;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectNoticeByNo"));
			pstmt.setInt(1,no);
			rs=pstmt.executeQuery();
			if(rs.next()) b=getNotice(rs);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return b;
	}
	
	public int updateNoticeReadCount(Connection conn, int no) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("updateNoticeReadCount"));
			pstmt.setInt(1, no);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}
	
	public int insertNotice(Connection conn,Notice n) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("insertNotice"));
			pstmt.setString(1, n.getNoticeTitle());
			pstmt.setString(2, n.getNoticeContent());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}
	
	public int searchNoticeNo(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("searchNoticeNo"));
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result=rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	public int insertNoticeImage(Connection conn,Notice_images image, int noticeNo) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("insertNoticeImage"));
			pstmt.setString(1, image.getSaveFilename());
			pstmt.setString(2, image.getFileName());
			pstmt.setInt(3, noticeNo);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}
	
	public Notice_images selectNoticeImage(Connection conn,int no) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Notice_images image=null;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectNoticeImage"));
			pstmt.setInt(1,no);
			rs=pstmt.executeQuery();
			if(rs.next()) image=getNoticeImage(rs);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return image;
	}
	
	public int updateNotice(Connection conn , Notice n) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("updateNotice"));
			pstmt.setString(1, n.getNoticeTitle());
			pstmt.setString(2, n.getNoticeContent());
			pstmt.setInt(3, n.getNoticeNo());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}
	public int updateNoticeImage(Connection conn , Notice_images image) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("updateNoticeImage"));
			pstmt.setString(1, image.getSaveFilename());
			pstmt.setString(2, image.getFileName());
			pstmt.setInt(3, image.getNoticeNo());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}
	
	public int NoticeDelete(Connection conn, int no) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("NoticeDelete"));
			pstmt.setInt(1, no);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}
	
	public int NoticeImageDelete(Connection conn, int no) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("NoticeImageDelete"));
			pstmt.setInt(1, no);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}
	
}

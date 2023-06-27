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

import com.btc.notice.model.dao.QnaDao;
import com.btc.notice.model.dto.Qna;
import com.btc.notice.model.dto.QnaComment;
public class QnaDao {
private Properties sql=new Properties();
	
	public QnaDao() {
		String path=QnaDao.class.getResource("/sql/notice/sql_Qna.properties").getPath();
		try {
			sql.load(new FileReader(path));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private Qna getQna(ResultSet rs) throws SQLException{
		return Qna.builder()
				.questionNo(rs.getInt("question_no"))
				.memberNo(rs.getInt("member_no"))
				.categoryName(rs.getString("category_name"))
				.questionDate(rs.getDate("question_date"))
				.answer(rs.getString("answer"))
				.questionContent(rs.getString("question_content"))
				.visibleCk(rs.getString("visible_ck").charAt(0))
				.questionTitle(rs.getString("question_title"))
				.build();
	}
	private QnaComment getQnaComment (ResultSet rs)throws SQLException{
		return QnaComment.builder()
				.QnaCommentNo(rs.getInt("qna_comment_no"))
				.QnaCommentLevel(rs.getInt("qna_comment_level"))
				.QnaCommentWriter(rs.getString("qna_comment_writer"))
				.QnaCommentContent(rs.getString("qna_comment_content"))
				.QnaCommentDate(rs.getDate("qna_comment_date"))
				.QnaCommentRef(rs.getInt("qna_comment_ref"))
				.QnaRef(rs.getInt("qna_ref"))
				.build();
	}
	
	
	
	public List<Qna> selectQna(Connection conn, int cPage, int numPerpage){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Qna> list=new ArrayList();
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectQna"));
			pstmt.setInt(1,(cPage-1)*numPerpage+1);
			pstmt.setInt(2, cPage*numPerpage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				list.add(getQna(rs));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	public int selectQnaCount(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectQnaCount"));
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
	
	public String checkName(Connection conn, int memberNo) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String result = "";
		try {
			pstmt=conn.prepareStatement(sql.getProperty("checkName"));
			pstmt.setInt(1,memberNo);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				result=rs.getString("MEMBER_NAME");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	public Qna selectQnaByNo(Connection conn, int no){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Qna q=null;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectQnaByNo"));
			pstmt.setInt(1,no);
			rs=pstmt.executeQuery();
			if(rs.next()) q=getQna(rs);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return q;
	}
	
	public List<QnaComment> selectQnaComment(Connection conn, int no){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<QnaComment> list=new ArrayList();
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectQnaComment"));
			pstmt.setInt(1,no);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				list.add(getQnaComment(rs));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return list;
	}
//	public List<Qna> searchQna(Connection conn,Map pagemap, Map map){
//		PreparedStatement pstmt=null;
//		ResultSet rs=null;
//		List<Qna> result=new ArrayList();
//		int cPage=(int)pagemap.get("cPage");
//		int numPerpage=(int)pagemap.get("numPerpage");
//		try {
//			String sqlc=sql.getProperty("searchType");
//			sqlc=sqlc.replace("#type", (String)map.get("type"));
//			pstmt=conn.prepareStatement(sqlc);
//			pstmt.setString(1, "%"+(String)map.get("keyword")+"%");
//			pstmt.setInt(2, (cPage-1)*numPerpage+1);
//			pstmt.setInt(3, cPage*numPerpage);
//			rs=pstmt.executeQuery();
//			while(rs.next()) {
//				result.add(getQna(rs));
//			}
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}finally {
//			close(rs);
//			close(pstmt);
//		}
//		return result;
//	}
//	
//	public int selectQnaSearchCount(Connection conn, Map map) {
//		PreparedStatement pstmt=null;
//		ResultSet rs=null;
//		int result=0;
//		try {
//			String sqlc=sql.getProperty("selectQnaSearchCount");
//			sqlc=sqlc.replace("#type", (String)map.get("type"));
//			pstmt=conn.prepareStatement(sqlc);
//			pstmt.setString(1, map.get("type").equals("gender")? (String)map.get("keyword"):"%"+(String)map.get("keyword")+"%");
//			rs=pstmt.executeQuery();
//			if(rs.next()) {
//				result=rs.getInt(1);
//			}
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}finally {
//			close(rs);
//			close(pstmt);
//		}
//		return result;
//	}
//	
//	
//	public int updateQnaReadCount(Connection conn, int no) {
//		PreparedStatement pstmt=null;
//		int result=0;
//		try {
//			pstmt=conn.prepareStatement(sql.getProperty("updateQnaReadCount"));
//			pstmt.setInt(1, no);
//			result=pstmt.executeUpdate();
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}finally {
//			close(pstmt);
//		}return result;
//	}
//	
//	public int insertQna(Connection conn,Qna n) {
//		PreparedStatement pstmt=null;
//		int result=0;
//		try {
//			pstmt=conn.prepareStatement(sql.getProperty("insertQna"));
//			pstmt.setString(1, n.getQnaTitle());
//			pstmt.setString(2, n.getQnaContent());
//			result=pstmt.executeUpdate();
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}finally {
//			close(pstmt);
//		}return result;
//	}
//	
//	public int searchQnaNo(Connection conn) {
//		PreparedStatement pstmt=null;
//		ResultSet rs=null;
//		int result=0;
//		try {
//			pstmt=conn.prepareStatement(sql.getProperty("searchQnaNo"));
//			rs=pstmt.executeQuery();
//			if(rs.next()) {
//				result=rs.getInt(1);
//			}
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}finally {
//			close(rs);
//			close(pstmt);
//		}
//		return result;
//	}
//	
//	public int insertQnaImage(Connection conn,Qna_images image, int QnaNo) {
//		PreparedStatement pstmt=null;
//		int result=0;
//		try {
//			pstmt=conn.prepareStatement(sql.getProperty("insertQnaImage"));
//			pstmt.setString(1, image.getSaveFilename());
//			pstmt.setString(2, image.getFileName());
//			pstmt.setInt(3, QnaNo);
//			result=pstmt.executeUpdate();
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}finally {
//			close(pstmt);
//		}return result;
//	}
//	
//	public Qna_images selectQnaImage(Connection conn,int no) {
//		PreparedStatement pstmt=null;
//		ResultSet rs=null;
//		Qna_images image=null;
//		try {
//			pstmt=conn.prepareStatement(sql.getProperty("selectQnaImage"));
//			pstmt.setInt(1,no);
//			rs=pstmt.executeQuery();
//			if(rs.next()) image=getQnaImage(rs);
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}finally {
//			close(rs);
//			close(pstmt);
//		}return image;
//	}
//	
//	public int updateQna(Connection conn , Qna n) {
//		PreparedStatement pstmt = null;
//		int result = 0;
//		try {
//			pstmt=conn.prepareStatement(sql.getProperty("updateQna"));
//			pstmt.setString(1, n.getQnaTitle());
//			pstmt.setString(2, n.getQnaContent());
//			pstmt.setInt(3, n.getQnaNo());
//			result=pstmt.executeUpdate();
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}finally {
//			close(pstmt);
//		}return result;
//	}
//	public int updateQnaImage(Connection conn , Qna_images image) {
//		PreparedStatement pstmt = null;
//		int result = 0;
//		try {
//			pstmt=conn.prepareStatement(sql.getProperty("updateQnaImage"));
//			pstmt.setString(1, image.getSaveFilename());
//			pstmt.setString(2, image.getFileName());
//			pstmt.setInt(3, image.getQnaNo());
//			result=pstmt.executeUpdate();
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}finally {
//			close(pstmt);
//		}return result;
//	}
//	
//	public int QnaDelete(Connection conn, int no) {
//		PreparedStatement pstmt = null;
//		int result = 0;
//		try {
//			pstmt=conn.prepareStatement(sql.getProperty("QnaDelete"));
//			pstmt.setInt(1, no);
//			result=pstmt.executeUpdate();
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}finally {
//			close(pstmt);
//		}return result;
//	}
//	
//	public int QnaImageDelete(Connection conn, int no) {
//		PreparedStatement pstmt = null;
//		int result = 0;
//		try {
//			pstmt=conn.prepareStatement(sql.getProperty("QnaImageDelete"));
//			pstmt.setInt(1, no);
//			result=pstmt.executeUpdate();
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}finally {
//			close(pstmt);
//		}return result;
//	}
	
}

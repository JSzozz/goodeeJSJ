
package com.btc.notice.model.dao;

import static com.btc.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.btc.notice.model.dto.Notice;
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
				.questionTitle(rs.getString("question_title"))
				.memberName(rs.getString("member_name"))
				.questionDate(rs.getDate("question_date"))
				.answer(rs.getString("answer"))
				.questionContent(rs.getString("question_content"))
				.visibleCk(rs.getString("visible_ck").charAt(0))
				.build();
	}
	private QnaComment getQnaComment (ResultSet rs)throws SQLException{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
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
	
	public int insertQnaComment(Connection conn, QnaComment qc) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("insertQnaComment"));
			pstmt.setInt(1, qc.getQnaCommentLevel());
			pstmt.setString(2, qc.getQnaCommentWriter());
			pstmt.setString(3, qc.getQnaCommentContent());
			pstmt.setInt(4, qc.getQnaRef());
			pstmt.setString(5, qc.getQnaCommentRef()==0?null:String.valueOf(qc.getQnaCommentRef()));
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}
	
	public int insertQna(Connection conn, Qna q) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("insertQna"));
			pstmt.setInt(1, q.getMemberNo());
			pstmt.setString(2, q.getCategoryName());
			pstmt.setString(3, q.getQuestionContent());
			pstmt.setString(4, q.getQuestionTitle());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}
	
	public int updateQna(Connection conn , Qna q) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("updateQna"));
			pstmt.setInt(1, q.getMemberNo());
			pstmt.setString(2, q.getCategoryName());
			pstmt.setString(3, q.getQuestionTitle());
			pstmt.setString(4, q.getQuestionContent());
			pstmt.setInt(5, q.getMemberNo());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}
	
	public int QnaDelete(Connection conn, int no) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("QnaDelete"));
			pstmt.setInt(1, no);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}
	
	public int updateQnaComment(Connection conn, QnaComment qc) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("updateQnaComment"));
			pstmt.setInt(1, qc.getQnaCommentLevel());
			pstmt.setString(2, qc.getQnaCommentWriter());
			pstmt.setString(3, qc.getQnaCommentContent());
			pstmt.setInt(4, qc.getQnaRef());
			pstmt.setInt(5, qc.getQnaCommentNo());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}
	
	public int QnaCommentDelete(Connection conn, int no) {
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("QnaCommentDelete"));
			pstmt.setInt(1, no);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}

	public List<Qna> searchQna(Connection conn,Map pagemap, Map map){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Qna> result=new ArrayList();
		int cPage=(int)pagemap.get("cPage");
		int numPerpage=(int)pagemap.get("numPerpage");
		try {
			String sqlc=sql.getProperty("searchQna");
			sqlc=sqlc.replace("#type", (String)map.get("type")); //search type
			pstmt=conn.prepareStatement(sqlc);
			pstmt.setString(1, "%"+(String)map.get("keyword")+"%"); //keyword
			pstmt.setInt(2, (cPage-1)*numPerpage+1); //cPage
			pstmt.setInt(3, cPage*numPerpage); //numPerPage
			rs=pstmt.executeQuery();
			while(rs.next()) {
				result.add(getQna(rs));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}

	public int selectQnaSearchCount(Connection conn, Map map) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		try {
			String sqlc=sql.getProperty("selectQnaSearchCount");
			sqlc=sqlc.replace("#type", (String)map.get("type"));
			pstmt=conn.prepareStatement(sqlc);
			pstmt.setString(1, "%"+(String)map.get("keyword")+"%");
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
	
	public List<Qna> searchQnaFilter(String filter,Connection conn,Map pagemap){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Qna> result=new ArrayList();
		int cPage=(int)pagemap.get("cPage");
		int numPerpage=(int)pagemap.get("numPerpage");
		try {
			String sqlc=sql.getProperty("searchQnaFilter");
			sqlc=sqlc.replace("#WHERE", filter.isEmpty()?"":"WHERE CATEGORY_NAME IN (" + filter + ")");
			System.out.println(filter);
			pstmt=conn.prepareStatement(sqlc);
			pstmt.setInt(1, (cPage-1)*numPerpage+1); //cPage
			pstmt.setInt(2, cPage*numPerpage); //numPerPage
			rs=pstmt.executeQuery();
			while(rs.next()) {
				result.add(getQna(rs));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		System.out.println("dao: "+result);
		return result;
	}
	
	public int selectQnaFilterCount(Connection conn, String filter) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		try {
			String sqlc=sql.getProperty("selectQnaFilterCount");
			sqlc=sqlc.replace("#WHERE", filter.isEmpty()?"":"WHERE CATEGORY_NAME IN (" + filter + ")");
			pstmt=conn.prepareStatement(sqlc);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result=rs.getInt(1);
			}
			System.out.println("테스트" + result);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
}

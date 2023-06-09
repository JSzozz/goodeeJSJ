package com.web.admin.model.dao;

import static com.web.common.JDBCTemplate.close;
import static com.web.member.model.dao.MemberDao.*;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.web.member.model.dto.Member;

import oracle.jdbc.proxy.annotation.Pre;

public class AdminDao {

	private final Properties sql = new Properties();

//		String path=AdminDao.class.getResource("sql/admin/adminsql.properties").getPath();
	public AdminDao() {
		String path = AdminDao.class.getResource("/sql/admin/adminsql.properties").getPath();
		try {
			sql.load(new FileReader(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}// 메소드는 초기화블록으로 대체 가능!

	public List<Member> selectMemberAll(Connection conn, int cPage, int numPerpage) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
//		List<MemberDTO> m= null;
		List<Member> result = new ArrayList();
		try {
//			pstmt=conn.prepareStatement(sql.getProperty("selectMemberAll"));//SELECT * FROM MEMBER - 메소드 기능 수정시킴
			pstmt = conn.prepareStatement(sql.getProperty("selectMemberAll"));
			// SELECT * FROM (SELECT ROWNUM AS RNUM, M.* FROM(SELECT * FROM MEMBER) M) WHERE
			// RNUM BETWEEN ? AND ?
			pstmt.setInt(1, (cPage - 1) * numPerpage + 1);
			pstmt.setInt(2, cPage * numPerpage);
			rs = pstmt.executeQuery();
			while (rs.next()) {
//x				result=getMember(rs);
				result.add(getMember(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}

	public int selectMemberCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql.getProperty("selectMemberCount"));// SELECT COUNT(*) FROM MEMBER
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);// ()에는 컬럼명||컬럼 인덱스 번호를 넣을 수 있음 - 가독성상 컬럼명 이용, db에 접근 어려운 상황에서는 인덱스 번호 활용 가능!
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;

	}

	public List<Member> selectMemberByKeyword(Connection conn, String type, String keyword, int cPage, int numPerpage){//String type은 컬럼명
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String query= sql.getProperty("selectMemberByKeyword");//SELECT * FROM (SELECT rownum AS rnum, m.* FROM (SELECT * FROM MEMBER WHERE #COL LIKE ?)m) WHERE rnum BETWEEN ? AND ?
		List<Member> members=new ArrayList();

		try {
			query=query.replace("#COL", type);//v : 컬럼명을 문자열로 인식하기 때문에 필요한 작업
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, type.equals("gender")?keyword:"%"+keyword+"%");
			pstmt.setInt(2, (cPage-1)*numPerpage+1);
			pstmt.setInt(3, cPage*numPerpage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				members.add(getMember(rs));
			}
				
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return members;
	}
	
	public int selectMemberByKeywordCount(Connection conn, String type, String keyword) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		String query=sql.getProperty("selectMemberByKeywordCount").replace("#COL", type);//SELECT COUNT(*) FROM MEMBER WHERE #COL LIKE ?
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, type.equals("gender")?keyword:"%"+keyword+"%");
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result=rs.getInt(1);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return result;
		
		
	}
	public static Member getMember(ResultSet rs) throws SQLException { // static 메소드
		return Member.builder().userId(rs.getString("userId")).userName(rs.getString("username"))
				.gender(rs.getString("gender").charAt(0)).age(rs.getInt("age")).email(rs.getString("email"))
				.phone(rs.getString("phone")).address(rs.getString("address"))
				.hobby(rs.getString("hobby") != null ? rs.getString("hobby").split(",") : null)
				.enrollDate(rs.getDate("enrolldate")).build();
	}
}

package com.web.admin.model.dao;

import static com.web.common.JDBCTemplate.close;
import static com.web.model.dao.MemberDao.*;//MemberDao는 template에 넣어도 된다!

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.web.model.dto.MemberDTO;

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

	public List<MemberDTO> selectMemberAll(Connection conn, int cPage, int numPerpage) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
//		List<MemberDTO> m= null;
		List<MemberDTO> result = new ArrayList();
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

	public static MemberDTO getMember(ResultSet rs) throws SQLException { // static 메소드
		return MemberDTO.builder().userId(rs.getString("userId")).userName(rs.getString("username"))
				.gender(rs.getString("gender").charAt(0)).age(rs.getInt("age")).email(rs.getString("email"))
				.phone(rs.getString("phone")).address(rs.getString("address"))
				.hobby(rs.getString("hobby") != null ? rs.getString("hobby").split(",") : null)
				.enrollDate(rs.getDate("enrolldate")).build();
	}
}

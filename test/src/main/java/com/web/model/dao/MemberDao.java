package com.web.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.web.common.JDBCTemplate;
//import com.web.member.model.vo.Member;
import com.web.model.dto.MemberDTO;

public class MemberDao {

	public int insertMember(Connection conn, MemberDTO m) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement("INSERT INTO MEMBER VALUES(?,?,?,?,?,?,?,?,?,SYSDATE)");
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getPassword());
			pstmt.setString(3, m.getUserName());
			pstmt.setString(4, String.valueOf(m.getGender()));//v 
			pstmt.setInt(5, m.getAge());//v
			pstmt.setString(6, m.getEmail());
			pstmt.setString(7,m.getPhone());
			pstmt.setString(8, m.getAddress());
			pstmt.setString(9, String.join(",", m.getHobby()));//v
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
}

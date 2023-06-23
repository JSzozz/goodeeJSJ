package com.btc.member.model.dao;


import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import static com.btc.common.JDBCTemplate.*;

import com.btc.member.model.dto.Member;

public class MemberDao {
	Properties sql=new Properties();
	{
		String path=MemberDao.class.getResource("/sql/member/sql_member.properties").getPath();
		try {
		sql.load(new FileReader(path));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public Member login(Connection conn,String email, String pw) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Member m=null;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("login"));
			pstmt.setString(1, email);
			pstmt.setString(2, pw);
			rs=pstmt.executeQuery();
			if(rs.next()) m=getMember(rs);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return m;
	}
	
	public int insertMember(Connection conn, String name, String email, String nickName, String phone,String pw) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("insertMember"));
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			pstmt.setString(3, nickName);
			pstmt.setString(4, pw);
			pstmt.setString(5, phone);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}
	
	public Member selectEmail(Connection conn, String email) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Member m=null;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectEmail"));
			pstmt.setString(1, email);
			rs=pstmt.executeQuery();
			if(rs.next()) m=getMember(rs);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return m;
	}
	
	public int updatePassword(Connection conn, String email, String pw) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("updatePassword"));
			pstmt.setString(1, pw);
			pstmt.setString(2, email);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}
	
	private Member getMember(ResultSet rs) throws SQLException{
		return Member.builder().memberNo(rs.getInt("member_no")).memberType(rs.getInt("member_type")).memberName(rs.getString("member_name"))
				.email(rs.getString("email")).nickName(rs.getString("nickname")).phone(rs.getString("phone")).memberBlack(rs.getString("member_black")).build();
	}
}
















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
import com.btc.member.model.dto.SNSMember;

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
	
	public int PasswordCk(Connection conn, int memberNo, String password) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("passwordCk"));
			pstmt.setInt(1, memberNo);
			pstmt.setString(2, password);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}
	
	public int upadateMember(Connection conn, String nickname, String phone, String name, String email) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("updateMember"));
			pstmt.setString(1, nickname);
			pstmt.setString(2, phone);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}
	
	public int deleteMember(Connection conn,int memberNo) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("deleteMember"));
			pstmt.setInt(1, memberNo);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}
	
	public int updateBlack(Connection conn,int memberNo,String black) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("updateBlack"));
			pstmt.setString(1, black);
			pstmt.setInt(2, memberNo);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}
	
	public int insertSNSMember(Connection conn, String code,String type,String name,String email,String nickname) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("inserSNSMember"));
			pstmt.setString(1, code);
			pstmt.setString(2, type);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			pstmt.setString(5, nickname);
			result=pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			
		}return result;
	}
	
	public SNSMember selectSNSMember(Connection conn,String code) {
		PreparedStatement pstmt=null;
		SNSMember m=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectSNSMember"));
			pstmt.setString(1, code);
			rs=pstmt.executeQuery();
			if(rs.next()) m=getSNSMember(rs);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return m;
	}
	
	private Member getMember(ResultSet rs) throws SQLException{
		return Member.builder().memberNo(rs.getInt("member_no")).memberType(rs.getInt("member_type")).memberName(rs.getString("member_name"))
				.email(rs.getString("email")).nickName(rs.getString("nickname")).phone(rs.getString("phone")).memberBlack(rs.getString("member_black")).build();
	}
	
	private SNSMember getSNSMember(ResultSet rs) throws SQLException{
		return SNSMember.builder().memberNo(rs.getInt("sns_member_no")).snsCode(rs.getString("sns_code")).snsType(rs.getString("sns_type")).Name(rs.getString("sns_name"))
				.email(rs.getString("sns_email")).nickName(rs.getString("sns_nickname")).snsConnectDate(rs.getDate("sns_connect_date")).build();
	}
}
















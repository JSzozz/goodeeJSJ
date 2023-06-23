package com.btc.admin.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.lang.module.ModuleDescriptor.Builder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.btc.member.model.dao.MemberDao;
import com.btc.member.model.dto.Member;

import static com.btc.common.JDBCTemplate.*;

public class AdminMemberDao {

	Properties sql=new Properties();
	{
		String path=MemberDao.class.getResource("/sql/admin/sql_adminMember.properties").getPath();
		try {
		sql.load(new FileReader(path));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public int memberCount(Connection conn) {
		PreparedStatement pstmt=null;
		int result=0;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("memberCount"));
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
	
	public List<Member> memberList(Connection conn,int cPage, int numPerpage){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Member> members=new ArrayList<>();
		try {
			pstmt=conn.prepareStatement(sql.getProperty("memberList"));
			pstmt.setInt(1, (cPage-1)*numPerpage+1);
			pstmt.setInt(2, cPage*numPerpage);
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
	
	private Member getMember(ResultSet rs) throws SQLException{
		return Member.builder().memberNo(rs.getInt("member_No")).memberName(rs.getString("member_Name")).email(rs.getString("email")).nickName(rs.getString("nickName"))
				.phone(rs.getString("phone")).memberBlack(rs.getString("member_black")).build();
	}
	
}





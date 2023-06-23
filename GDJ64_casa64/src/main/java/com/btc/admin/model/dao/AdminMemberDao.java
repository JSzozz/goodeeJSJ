package com.btc.admin.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.btc.member.model.dao.MemberDao;

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
}

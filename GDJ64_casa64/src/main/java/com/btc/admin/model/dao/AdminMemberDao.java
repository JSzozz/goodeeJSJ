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
import com.btc.member.model.dto.CancelMember;
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
	
	public int memberCancel(Connection conn, int memberNo) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("memberCancel"));
			pstmt.setInt(1, memberNo);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			
			close(pstmt);
		}return result;
		
	}
	
	public Member selectMember(Connection conn, int memberNo) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Member m=null;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectMember"));
			pstmt.setInt(1, memberNo);
			rs=pstmt.executeQuery();
			if(rs.next()) m=getMember(rs);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return m;
	}
	
	public int insertCancelMember(Connection conn, String name, String nickname, String email, String phone) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("insertCancelMember"));
			pstmt.setString(1, name);
			pstmt.setString(2, nickname);
			pstmt.setString(3, email);
			pstmt.setString(4, phone);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			
			close(pstmt);
		}return result;
	}
	
	public int canceledMemberCount(Connection conn) {
		PreparedStatement pstmt=null;
		int result=0;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("canceledMemberCount"));
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
	
	public List<CancelMember> canceledMemberList(Connection conn,int cPage, int numPerpage){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<CancelMember> members=new ArrayList<>();
		try {
			pstmt=conn.prepareStatement(sql.getProperty("canceledMemberList"));
			pstmt.setInt(1, (cPage-1)*numPerpage+1);
			pstmt.setInt(2, cPage*numPerpage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				members.add(getCancelMember(rs));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return members;
	}
	
	public List<Member> searchMember(Connection conn,int cPage,int numPerpage,String type ,String searchMember){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Member> members=new ArrayList<>();
		String query="";
		if(type.equals("member_name")) query=sql.getProperty("searchMemberByName");
		else if(type.equals("nickname")) query=sql.getProperty("searchMemberByNickname");
		else if(type.equals("email")) query=sql.getProperty("searchMemberByEmail");
		else query=sql.getProperty("searchMemberByPhone");
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, searchMember);
			pstmt.setInt(2, (cPage-1)*numPerpage+1);
			pstmt.setInt(3, cPage*numPerpage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				members.add(getMember(rs));
			}
			System.out.println(members);
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				close(rs);
				close(pstmt);
			}return members;
		}
		
		public int searchMemberCount(Connection conn, String type,String searchMember) {
			PreparedStatement pstmt=null;
			int result=0;
			ResultSet rs=null;
			String query="";
			if(type.equals("member_name")) query=sql.getProperty("searchMemberByNameCount");
			else if(type.equals("nickname")) query=sql.getProperty("searchMemberByNicknameCount");
			else if(type.equals("email")) query=sql.getProperty("searchMemberByEmailCount");
			else query=sql.getProperty("searchMemberByPhoneCount");
			try {
				pstmt=conn.prepareStatement(query);
				pstmt.setString(1, searchMember);
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
	
		public List<CancelMember> searchCMember(Connection conn,int cPage,int numPerpage,String type ,String searchMember){
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			List<CancelMember> members=new ArrayList<>();
			String query="";
			if(type.equals("member_name")) query=sql.getProperty("searchCMemberByName");
			else if(type.equals("nickname")) query=sql.getProperty("searchCMemberByNickname");
			else if(type.equals("email")) query=sql.getProperty("searchCMemberByEmail");
			else query=sql.getProperty("searchCMemberByPhone");
			try {
				pstmt=conn.prepareStatement(query);
				pstmt.setString(1, searchMember);
				pstmt.setInt(2, (cPage-1)*numPerpage+1);
				pstmt.setInt(3, cPage*numPerpage);
				rs=pstmt.executeQuery();
				while(rs.next()) {
					members.add(getCancelMember(rs));
				}
				System.out.println(members);
				}catch(SQLException e) {
					e.printStackTrace();
				}finally {
					close(rs);
					close(pstmt);
				}return members;
			}
			
			public int searchCMemberCount(Connection conn, String type,String searchMember) {
				PreparedStatement pstmt=null;
				int result=0;
				ResultSet rs=null;
				String query="";
				if(type.equals("member_name")) query=sql.getProperty("searchCMemberByNameCount");
				else if(type.equals("nickname")) query=sql.getProperty("searchCMemberByNicknameCount");
				else if(type.equals("email")) query=sql.getProperty("searchCMemberByEmailCount");
				else query=sql.getProperty("searchCMemberByPhoneCount");
				try {
					pstmt=conn.prepareStatement(query);
					pstmt.setString(1, searchMember);
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
	
	
	
	
	private Member getMember(ResultSet rs) throws SQLException{
		return Member.builder().memberNo(rs.getInt("member_No")).memberName(rs.getString("member_Name")).email(rs.getString("email")).nickName(rs.getString("nickName"))
				.phone(rs.getString("phone")).memberBlack(rs.getString("member_black")).build();
	}
	
	private CancelMember getCancelMember(ResultSet rs) throws SQLException{
		return CancelMember.builder().memberNo(rs.getInt("member_No")).memberName(rs.getString("member_Name")).nickName(rs.getString("nickName")).email(rs.getString("email"))
				.phone(rs.getString("phone")).cancelDate(rs.getDate("cancel_date")).build();
	}
	
}






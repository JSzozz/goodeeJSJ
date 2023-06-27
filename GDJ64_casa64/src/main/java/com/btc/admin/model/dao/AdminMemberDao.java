package com.btc.admin.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;



import com.btc.member.model.dao.MemberDao;
import com.btc.member.model.dto.BlackFile;
import com.btc.member.model.dto.BlackMember;
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
	
	public int insertBlackMember(Connection conn,int memberNo,String memberName,String nickName,String email,String phone,String reason) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("insertBlackMember"));
			pstmt.setInt(1, memberNo);
			pstmt.setString(2, memberName);
			pstmt.setString(3, nickName);
			pstmt.setString(4, email);
			pstmt.setString(5, phone);
			pstmt.setString(6, reason);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}
	
	public int insertBlackFile(Connection conn,int memberno,String fileName,String fileRealName) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("insertBlackFile"));
			pstmt.setInt(1, memberno);
			pstmt.setString(2, fileName);
			pstmt.setString(3, fileRealName);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}
	
	public int blackMemberCount(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("blackMemberCount"));
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result=rs.getInt(1);
			}
		}catch(SQLException e) {
				e.printStackTrace();
		}return result;
	}
	
	public List<BlackMember> blackMemberList(Connection conn, int cPage,int numPerpage){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<BlackMember> members=new ArrayList();
		try {
			pstmt=conn.prepareStatement(sql.getProperty("blackMemberList"));
			pstmt.setInt(1, (cPage-1)*numPerpage+1);
			pstmt.setInt(2, cPage*numPerpage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				members.add(getBlackMember(rs));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return members;
	}
	
	public BlackMember selectBlackMember(Connection conn, int memberNo) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		BlackMember m=null;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectBlackMember"));
			pstmt.setInt(1, memberNo);
			rs=pstmt.executeQuery();
			if(rs.next()) m=getBlackMember(rs);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return m;
	}
	
	public BlackFile selectBlackFile(Connection conn, int memberNo) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		BlackFile m=null;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectBlackFile"));
			pstmt.setInt(1, memberNo);
			rs=pstmt.executeQuery();
			if(rs.next()) m=getBlackFile(rs);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return m;
	}
	
	public int deleteBlackMember(Connection conn, int memberNo) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("deleteBlackMember"));
			pstmt.setInt(1, memberNo);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			
		}return result;
	}
	
	public int deleteBlackFile(Connection conn, int memberNo) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("deleteBlackFile"));
			pstmt.setInt(1, memberNo);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			
		}return result;
	}
	
	public List<BlackMember> searchBMember(Connection conn,int cPage,int numPerpage,String type ,String searchMember){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<BlackMember> members=new ArrayList<>();
		String query="";
		if(type.equals("member_name")) query=sql.getProperty("searchBMemberByName");
		else if(type.equals("nickname")) query=sql.getProperty("searchBMemberByNickname");
		else if(type.equals("email")) query=sql.getProperty("searchBMemberByEmail");
		else query=sql.getProperty("searchBMemberByPhone");
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, searchMember);
			pstmt.setInt(2, (cPage-1)*numPerpage+1);
			pstmt.setInt(3, cPage*numPerpage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				members.add(getBlackMember(rs));
			}
			System.out.println(members);
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				close(rs);
				close(pstmt);
			}return members;
		}
		
		public int searchBMemberCount(Connection conn, String type,String searchMember) {
			PreparedStatement pstmt=null;
			int result=0;
			ResultSet rs=null;
			String query="";
			if(type.equals("member_name")) query=sql.getProperty("searchBMemberByNameCount");
			else if(type.equals("nickname")) query=sql.getProperty("searchBMemberByNicknameCount");
			else if(type.equals("email")) query=sql.getProperty("searchBMemberByEmailCount");
			else query=sql.getProperty("searchBMemberByPhoneCount");
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
		
	public CancelMember selectCMember(Connection conn,String email) {
		PreparedStatement pstmt=null;
		CancelMember m=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectCMember"));
			pstmt.setString(1, email);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				m=getCancelMember(rs);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return m;
	}
	
	public int deleteCMember(Connection conn, String email) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("deleteCMember"));
			pstmt.setString(1, email);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
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
	
	private BlackMember getBlackMember(ResultSet rs) throws SQLException{
		return BlackMember.builder().memberNo(rs.getInt("member_no")).memberName(rs.getString("member_name")).email(rs.getString("email")).phone(rs.getString("phone"))
				.reason(rs.getString("reason")).blackDate(rs.getDate("black_date")).build();
	}
	
	private BlackFile getBlackFile(ResultSet rs) throws SQLException{
		return BlackFile.builder().memberNo(rs.getInt("member_no")).fileName(rs.getString("file_name")).fileRealName(rs.getString("file_real_name")).build();
	}
	
}






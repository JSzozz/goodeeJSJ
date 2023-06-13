package com.web.model.service;

import static com.web.common.JDBCTemplate.getConnection;

//import static com.jsp.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.web.common.JDBCTemplate;
//import com.web.member.model.vo.Member;
import com.web.model.dao.MemberDao;
import com.web.model.dto.Member;

public class MemberService {

	private MemberDao dao = new MemberDao();

//	public List<MemberDTO> selectMemberAll(){
//		Connection conn=JDBCTemplate.getConnection();
//		List<MemberDTO> list=dao.selectMemberAll(conn);
//		JDBCTemplate.close(conn);
//		return list;
//	}
//	
//	public List<MemberDTO> searchByName(String name){
//		Connection conn=JDBCTemplate.getConnection();
//		List<MemberDTO> list=dao.searchByNmae(conn, name);
//		JDBCTemplate.close(conn);
//		return list;
//	}

	public Member login(String userId, String password) {
		Connection conn = JDBCTemplate.getConnection();
		Member list = dao.selectByUserIdAndPw(conn, userId, password);
		JDBCTemplate.close(conn);
		return list;
	}

	public int insertMember(Member m) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.insertMember(conn, m);
		if (result > 0)
			JDBCTemplate.commit(conn);
		else
			JDBCTemplate.rollback(conn);
		JDBCTemplate.close(conn);
		return result;

	}
	
	public Member selectByUserId(String userId) {
		Connection conn = JDBCTemplate.getConnection();
		Member m=dao.selectByUserId(conn,userId);
		JDBCTemplate.close(conn);
		return m;
	}
	public int updateMember(Member m) {
		Connection conn = JDBCTemplate.getConnection();
		int result=dao.updateMember(conn,m);
		if(result>0)
			JDBCTemplate.commit(conn);
		else
			JDBCTemplate.rollback(conn);
			JDBCTemplate.close(conn);
		return result;
	}
	
	public int updatePassword(String userId, String password) {
		Connection conn=JDBCTemplate.getConnection();
		int result=dao.updatePassword(conn,userId,password);
		if(result>0)
			JDBCTemplate.commit(conn);
		else
			JDBCTemplate.rollback(conn);
			JDBCTemplate.close(conn);
		return result;
	}
	
	public Member selectByUserIdAndPw(String userId,String password) {
		Connection conn=getConnection();
		Member m=dao.selectByUserIdAndPw(conn,userId,password);
		JDBCTemplate.close(conn);
		return m;
	}
}

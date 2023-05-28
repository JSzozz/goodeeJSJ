package com.web.model.service;

//import static com.jsp.common.JDBCTemplate.getConnection;

import java.sql.Connection;

import com.web.common.JDBCTemplate;
import com.web.model.dao.MemberDao;
import com.web.model.dto.MemberDTO;

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

	public MemberDTO login(String userId, String password) {
		Connection conn = JDBCTemplate.getConnection();
		MemberDTO list = dao.login(conn, userId, password);
		JDBCTemplate.close(conn);
		return list;
	}

	public int insertMember(MemberDTO m) {
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.insertMember(conn, m);
		if (result > 0)
			JDBCTemplate.commit(conn);
		else
			JDBCTemplate.rollback(conn);
		JDBCTemplate.close(conn);
		return result;

	}
	
	public MemberDTO selectByUserId(String userId) {
		Connection conn = JDBCTemplate.getConnection();
		MemberDTO m=dao.selectByUserId(conn,userId);
		JDBCTemplate.close(conn);
		return m;
		
	}
}

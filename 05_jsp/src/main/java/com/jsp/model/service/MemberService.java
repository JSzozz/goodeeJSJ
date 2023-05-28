package com.jsp.model.service;

//import static com.jsp.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.jsp.common.JDBCTemplate;
import com.jsp.model.dao.MemberDao;
import com.jsp.model.dto.MemberDTO;

public class MemberService {

	private MemberDao dao=new MemberDao();
	
	public List<MemberDTO> selectMemberAll(){
		Connection conn=JDBCTemplate.getConnection();
		List<MemberDTO> list=dao.selectMemberAll(conn);
		JDBCTemplate.close(conn);
		return list;
	}
	
	public List<MemberDTO> searchByName(String name){
		Connection conn=JDBCTemplate.getConnection();
		List<MemberDTO> list=dao.searchByNmae(conn, name);
		JDBCTemplate.close(conn);
		 
		
		
		
		return list;
		
		
		
		
	}
	
}

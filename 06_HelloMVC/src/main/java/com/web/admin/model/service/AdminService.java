package com.web.admin.model.service;

import java.sql.Connection;
import java.util.List;

import com.web.admin.model.dao.AdminDao;
import com.web.member.model.dto.Member;

import static com.web.common.JDBCTemplate.*;

public class AdminService {

	private AdminDao dao=new AdminDao();
	
	public List<Member> selectMemberAll(int cPage, int numPerpage) {
	Connection conn = getConnection();
	List<Member> result=dao.selectMemberAll(conn, cPage, numPerpage);
	close(conn);
	return result;
	}
	
	public int selectMemberCount() {
		Connection conn=getConnection();
		int result=dao.selectMemberCount(conn);
		close(conn);
		return result;
	}
	
	public List<Member> selectMemberByKeyword(String type, String keyword, int cPage, int numPerpage){//(팁)map자료형이였으면 매개변수 추가 불필요했을 것임
		Connection conn=getConnection();
		List<Member> members=dao.selectMemberByKeyword(conn, type, keyword, cPage, numPerpage);
		close(conn);
		return members;
	}
	
	
	public int selectMemberByKeywordCount(String type, String keyword){
		Connection conn=getConnection();
		int count = dao.selectMemberByKeywordCount(conn, type, keyword);
		close(conn);
		return count;
		
	}
		
		
}

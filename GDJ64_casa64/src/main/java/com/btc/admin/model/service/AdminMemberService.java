package com.btc.admin.model.service;

import java.sql.Connection;
import java.util.List;

import static com.btc.common.JDBCTemplate.*;
import com.btc.admin.model.dao.AdminMemberDao;
import com.btc.member.model.dto.Member;

public class AdminMemberService {
	private AdminMemberDao dao=new AdminMemberDao();
	public int memberCount() {
		Connection conn=getConnection();
		int result=dao.memberCount(conn);
		close(conn);
		return result;
	}
	
	public List<Member> memberList(int cPage, int numPerpage){
		Connection conn=getConnection();
		List<Member> members=dao.memberList(conn,cPage,numPerpage);
		close(conn);
		return members;
	}
}

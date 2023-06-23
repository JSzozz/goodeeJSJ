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
	
	public int memberCancel(int memberNo) {
		Connection conn=getConnection();
		int result=dao.memberCancel(conn,memberNo);
		if(result>0)commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public Member selectMember(int memberNo) {
		Connection conn=getConnection();
		Member member=dao.selectMember(conn,memberNo);
		close(conn);
		return member;
	}
	
	public int insertCancelMember(String name, String nickname, String email, String phone) {
		Connection conn=getConnection();
		int result=dao.insertCancelMember(conn,name,nickname,email,phone);
		close(conn);
		return result;
	}
}










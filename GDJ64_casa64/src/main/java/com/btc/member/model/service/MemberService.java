package com.btc.member.model.service;
import static com.btc.common.JDBCTemplate.*;

import java.sql.Connection;

import com.btc.member.model.dao.MemberDao;
import com.btc.member.model.dto.Member;

public class MemberService {

	private MemberDao dao=new MemberDao();
	public Member login(String email, String pw) {
		Connection conn=getConnection();
		Member m=dao.login(conn, email, pw);
		close(conn);
		return m;
	}
	
	public int insertMember(String name, String email, String nickName, String phone, String pw) {
		Connection conn=getConnection();
		int result=dao.insertMember(conn,name,email,nickName,phone,pw);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public Member selectEmail(String email) {
		Connection conn=getConnection();
		Member m=dao.selectEmail(conn, email);
		close(conn);
		return m;
	}
	
	public int updatePassword(String email,String pw) {
		Connection conn=getConnection();
		int result=dao.updatePassword(conn,email,pw);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public int passwordCk(int memberNo,String pw) {
		Connection conn=getConnection();
		int result=dao.PasswordCk(conn, memberNo, pw);
		close(conn);
		return result;
	}
}

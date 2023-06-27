package com.btc.member.model.service;
import static com.btc.common.JDBCTemplate.*;

import java.sql.Connection;

import com.btc.member.model.dao.MemberDao;
import com.btc.member.model.dto.Member;
import com.btc.member.model.dto.SNSMember;

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
	
	public Member updateMember(String nick,String phone,String name,String email) {
		Connection conn=getConnection();
		int result=dao.upadateMember(conn, nick, phone, name, email);
		Member updateMember=null;
		if(result>0) {
			commit(conn);
			updateMember=dao.selectEmail(conn, email);
		}
		else rollback(conn);
		close(conn);
		return updateMember;
	}
	
	public int deleteMember(int memberNo) {
		Connection conn=getConnection();
		int result=dao.deleteMember(conn, memberNo);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public int updateBlack(int memberNo,String black) {
		Connection conn=getConnection();
		int result=dao.updateBlack(conn, memberNo,black);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public int insertSNSMember(String code,String type,String name,String email,String nickname) {
		Connection conn=getConnection();
		int result=dao.insertSNSMember(conn, code, type, name, email, nickname);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public SNSMember selectSNSMember(String code) {
		Connection conn=getConnection();
		SNSMember m=dao.selectSNSMember(conn, code);
		close(conn);
		return m;
	}

}













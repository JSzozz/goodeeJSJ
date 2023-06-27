package com.btc.admin.model.service;

import java.sql.Connection;
import java.util.List;

import static com.btc.common.JDBCTemplate.*;
import com.btc.admin.model.dao.AdminMemberDao;
import com.btc.member.model.dto.BlackFile;
import com.btc.member.model.dto.BlackMember;
import com.btc.member.model.dto.CancelMember;
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
	
	public int canceledMemberCount(){
		Connection conn=getConnection();
		int result=dao.canceledMemberCount(conn);
		close(conn);
		return result;
	}
	
	public List<CancelMember> canceledMemberList(int cPage,int numPerpage){
		Connection conn=getConnection();
		List<CancelMember> list=dao.canceledMemberList(conn,cPage,numPerpage);
		close(conn);
		return list;
	}
	
	public int searchMemberCount(String type, String searchMember) {
		Connection conn=getConnection();
		int result=dao.searchMemberCount(conn,type,searchMember);
		close(conn);
		return result;
	}
	
	public List<Member> searchMemberList(int cPage, int numPerpage,String type, String searchMember){
		Connection conn=getConnection();
		List<Member> members=dao.searchMember(conn,cPage,numPerpage,type,searchMember);
		close(conn);
		return members;
	}
	
	public int searchCMemberCount(String type, String searchMember) {
		Connection conn=getConnection();
		int result=dao.searchCMemberCount(conn,type,searchMember);
		close(conn);
		return result;
	}
	
	public List<CancelMember> searchCMemberList(int cPage, int numPerpage,String type, String searchMember){
		Connection conn=getConnection();
		List<CancelMember> members=dao.searchCMember(conn,cPage,numPerpage,type,searchMember);
		close(conn);
		return members;
	}
	
	public int insertBlackMember(int memberno,String memberName,String nickName,String email,String phone,String reason) {
		Connection conn=getConnection();
		int result=dao.insertBlackMember(conn, memberno, memberName, nickName, email, phone,reason);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public int insertBlackFile(int memberno,String fileName,String fileRealName) {
		Connection conn=getConnection();
		int result=dao.insertBlackFile(conn, memberno, fileName, fileRealName);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public int blackMemberCount() {
		Connection conn=getConnection();
		int result=dao.blackMemberCount(conn);
		close(conn);
		return result;
	}
	
	public List<BlackMember> blackMemberList(int cPage,int numPerpage){
		Connection conn=getConnection();
		List<BlackMember> members=dao.blackMemberList(conn, cPage, numPerpage);
		close(conn);
		return members;
	}
	
	public BlackMember selectBlackMember(int memberNo) {
		Connection conn=getConnection();
		BlackMember m=dao.selectBlackMember(conn, memberNo);
		close(conn);
		return m;
	}
	
	public BlackFile selectBlackFile(int memberNo) {
		Connection conn=getConnection();
		BlackFile m=dao.selectBlackFile(conn, memberNo);
		close(conn);
		return m;
	}
	
	public int deleteBlackMember(int memberNo) {
		Connection conn=getConnection();
		int result=dao.deleteBlackMember(conn, memberNo);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public int deleteBlackFile(int memberNo) {
		Connection conn=getConnection();
		int result=dao.deleteBlackFile(conn, memberNo);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public int searchBMemberCount(String type, String searchMember) {
		Connection conn=getConnection();
		int result=dao.searchBMemberCount(conn,type,searchMember);
		close(conn);
		return result;
	}
	
	public List<BlackMember> searchBMemberList(int cPage, int numPerpage,String type, String searchMember){
		Connection conn=getConnection();
		List<BlackMember> members=dao.searchBMember(conn,cPage,numPerpage,type,searchMember);
		close(conn);
		return members;
	}
	
	public CancelMember selectCMember(String email) {
		Connection conn=getConnection();
		CancelMember m=dao.selectCMember(conn, email);
		close(conn);
		return m;
	}
	
	public int deleteCMember(String email) {
		Connection conn=getConnection();
		int result=dao.deleteCMember(conn, email);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
}










package com.web.admin.model.service;

import java.sql.Connection;
import java.util.List;

import com.web.admin.model.dao.AdminDao;
import static com.web.common.JDBCTemplate.*;
import com.web.model.dto.MemberDTO;

public class AdminService {

	private AdminDao dao=new AdminDao();
	
	public List<MemberDTO> selectMemberAll(int cPage, int numPerpage) {
	Connection conn = getConnection();
	List<MemberDTO> result=dao.selectMemberAll(conn, cPage, numPerpage);
	close(conn);
	return result;
	}
	
	public int selectMemberCount() {
		Connection conn=getConnection();
		int result=dao.selectMemberCount(conn);
		close(conn);
		return result;
	}
}

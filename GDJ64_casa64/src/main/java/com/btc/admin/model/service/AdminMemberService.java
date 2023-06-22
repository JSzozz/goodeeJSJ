package com.btc.admin.model.service;

import java.sql.Connection;
import static com.btc.common.JDBCTemplate.*;
import com.btc.admin.model.dao.AdminMemberDao;

public class AdminMemberService {
	private AdminMemberDao dao=new AdminMemberDao();
	public int memberCount() {
		Connection conn=getConnection();
		int result=dao.memberCount(conn);
		close(conn);
		return result;
	}
}

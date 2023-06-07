package com.web.model.service;



import java.sql.Connection;
import com.web.common.JDBCTemplate;
import com.web.model.dao.MemberDao;
import com.web.model.dto.MemberDTO;

public class MemberService {

	private MemberDao dao = new MemberDao();
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

}

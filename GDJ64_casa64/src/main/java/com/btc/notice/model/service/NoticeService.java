package com.btc.notice.model.service;

import static com.btc.common.JDBCTemplate.close;
import static com.btc.common.JDBCTemplate.getConnection;
import static com.btc.common.JDBCTemplate.commit;
import static com.btc.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.btc.notice.model.dao.NoticeDao;
import com.btc.notice.model.dto.Notice;


public class NoticeService {
	private NoticeDao dao = new NoticeDao();
	
	public List<Notice> selectNotice(int cPage,int numPerpage){
		Connection conn=getConnection();
		List<Notice> list=dao.selectNotice(conn,cPage,numPerpage);
		close(conn);
		return list;
	}
	
	public int selectNoticeCount() {
		Connection conn=getConnection();
		int result=dao.selectNoticeCount(conn);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public List<Notice> searchNotice(Map pagemap,Map map) {
		Connection conn=getConnection();
		List<Notice> result=dao.searchNotice(conn,pagemap,map);
		close(conn);
		return result;
	}
	
	public int selectNoticeSearchCount(Map map) {
		Connection conn=getConnection();
		int result=dao.selectNoticeSearchCount(conn,map);
		close(conn);
		return result;
	}
	
	public Notice selectNoticeByNo(int no,boolean isRead){
		Connection conn=getConnection();
		//번호로 게시물 상세내용 불러오기
		Notice n=dao.selectNoticeByNo(conn, no);
		if(n!=null&&!isRead) { //isRead가 false면 값이 없는 것이니 들어간 적이 없다는 의미이고, 그때는 증가 메소드 실행||isRead가 true면 방문한 적 있기 때문에 실행을 막음
			int result=dao.updateNoticeReadCount(conn,no);
			if(result>0) {
				commit(conn);
				n.setNoticeReadCount(n.getNoticeReadCount()+1);
			}
			else rollback(conn);			
		}
		close(conn);
		return n;
	}
	
}

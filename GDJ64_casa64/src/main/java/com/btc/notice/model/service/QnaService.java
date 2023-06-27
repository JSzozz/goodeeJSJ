package com.btc.notice.model.service;

import static com.btc.common.JDBCTemplate.close;
import static com.btc.common.JDBCTemplate.commit;
import static com.btc.common.JDBCTemplate.getConnection;
import static com.btc.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.btc.notice.model.dao.QnaDao;
import com.btc.notice.model.dto.Qna;
import com.btc.notice.model.dto.QnaComment;
import com.btc.notice.model.dto.Notice_images;


public class QnaService {
	private QnaDao dao = new QnaDao();
	
	public List<Qna> selectQna(int cPage,int numPerpage){
		Connection conn=getConnection();
		List<Qna> list=dao.selectQna(conn,cPage,numPerpage);
		close(conn);
		return list;
	}
	
	public int selectQnaCount() {
		Connection conn=getConnection();
		int result=dao.selectQnaCount(conn);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public String checkName(int memberNo) {
		Connection conn=getConnection();
		String name=dao.checkName(conn,memberNo);
		close(conn);
		return name;
	}
	
	public Qna selectQnaByNo(int no){
		Connection conn=getConnection();
		Qna n=dao.selectQnaByNo(conn, no);
		close(conn);
		return n;
	}
	
	public List<QnaComment> selectQnaComment(int no) {
		Connection conn=getConnection();
		List<QnaComment> list=dao.selectQnaComment(conn,no);			
		close(conn);
		return list;
	}
//	public List<Notice> searchNotice(Map pagemap,Map map) {
//		Connection conn=getConnection();
//		List<Notice> result=dao.searchNotice(conn,pagemap,map);
//		close(conn);
//		return result;
//	}
//	
//	public int selectNoticeSearchCount(Map map) {
//		Connection conn=getConnection();
//		int result=dao.selectNoticeSearchCount(conn,map);
//		close(conn);
//		return result;
//	}
//	
//	
//	public int insertNotice(Notice n) {
//		Connection conn=getConnection();
//		int result=dao.insertNotice(conn,n);
//		if(result>0) commit(conn);
//		else rollback(conn);
//		close(conn);
//		return result;
//	}
//	
//	public int searchNoticeNo() {
//		Connection conn=getConnection();
//		int result=dao.searchNoticeNo(conn);
//		if(result>0) commit(conn);
//		else rollback(conn);
//		close(conn);
//		return result;
//	}
//	
//	public int insertNoticeImage(Notice_images image, int noticeNo) {
//		Connection conn=getConnection();
//		int result=dao.insertNoticeImage(conn,image,noticeNo);
//		if(result>0) commit(conn);
//		else rollback(conn);
//		close(conn);
//		return result;
//	}
//	
//	public Notice_images selectNoticeImage(int no) {
//		Connection conn=getConnection();
//		Notice_images image=dao.selectNoticeImage(conn, no);			
//		close(conn);
//		return image;
//	}
//	
//	public int updateNotice(Notice n , Notice_images image) {
//		Connection conn=getConnection();
//		int result1=dao.updateNotice(conn,n);
//		int result2=dao.updateNoticeImage(conn,image);
//		if(result1>0&&result2>0) commit(conn);
//		else rollback(conn);
//		close(conn);
//		return result1;
//	}
//	
//	public int NoticeDelete(int no) {
//		Connection conn=getConnection();
//		int result1=dao.NoticeDelete(conn,no);
//		int result2=dao.NoticeImageDelete(conn,no);
//		if(result1>0&&result2>0) commit(conn);
//		else rollback(conn);
//		close(conn);
//		return result1;
//	}
}


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
import com.btc.notice.model.dto.Notice;
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
	
	public int insertQnaComment(QnaComment qc) {
		Connection conn=getConnection();
		int result=dao.insertQnaComment(conn,qc);
		if(result>0) commit(conn);
		else rollback(conn);			
		close(conn);
		return result;
	}
	
	public int insertQna(Qna q) {
		Connection conn=getConnection();
		int result=dao.insertQna(conn,q);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public int updateQna(Qna q) {
		Connection conn=getConnection();
		int result=dao.updateQna(conn,q);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public int QnaDelete(int no) {
		Connection conn=getConnection();
		int result=dao.QnaDelete(conn,no);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public int updateQnaComment(QnaComment qc) {
		Connection conn=getConnection();
		int result=dao.updateQnaComment(conn,qc);
		if(result>0) commit(conn);
		else rollback(conn);			
		close(conn);
		return result;
	}
	
	public int QnaCommentDelete(int no) {
		Connection conn=getConnection();
		int result=dao.QnaCommentDelete(conn,no);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public List<Qna> searchQna(Map pagemap,Map map) {
		Connection conn=getConnection();
		List<Qna> result=dao.searchQna(conn,pagemap,map);
		close(conn);
		return result;
	}
	
	public int selectQnaSearchCount(Map map) {
		Connection conn=getConnection();
		int result=dao.selectQnaSearchCount(conn,map);
		close(conn);
		return result;
	}
}


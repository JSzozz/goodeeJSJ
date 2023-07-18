package com.bs.spring.board.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bs.spring.board.model.dao.BoardDao;
import com.bs.spring.board.model.dto.Attachment;
import com.bs.spring.board.model.dto.Board;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BoardServiceImpl implements BoardService {
	
	private BoardDao dao;
	private SqlSession session;
	
	
	public BoardServiceImpl(BoardDao dao, SqlSession session) {
		this.dao = dao;
		this.session = session;
	}

	@Override
//	@Transactional
	public int insertBoard(Board b) {
		//2개의 insert문을 실행!
		log.info("{}", b.getBoardNo());
		int result=dao.insertBoard(session, b);
		log.info("{}", b.getBoardNo());
		if(result>0) {
			if(b.getFile().size()>0) {
				for(Attachment a: b.getFile()) {
					//b.getFile().setBoardNo(b.getBoardNo());
					//result=dao.insertAttachment(session, b.getFile());
					a.setBoardNo(b.getBoardNo());
					result+=dao.insertAttachment(session, a);
					
					//result=dao.insertAttachment(session, a);
					//if(result!=0) throw new RuntimeException("내가 그냥 싫어!!!");//rollback방법1
				}
			}
		}
		//rollback처리를 원하다면..... RuntimeException을 발생시키면됨.
//		if(result!=b.getFile().size()+1) throw new RuntimeException("내가 그냥 싫어!!!");//rollback방법2
		if(result!=0) throw new RuntimeException("내가 그냥 싫어!!!");//rollback방법2
		return result;
	}

	@Override
	public List<Board> selectBoardAll(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return dao.selectBoardAll(session, param);
	}

	@Override
	public int selectBoardCount() {
		// TODO Auto-generated method stub
		return dao.selectBoardCount(session);
	}

	@Override
	public Board selectBoardById(int no) {
		// TODO Auto-generated method stub
		return dao.selectBoardByNo(session, no);
	}

}

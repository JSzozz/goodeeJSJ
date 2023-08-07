package com.bs.helloboot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bs.helloboot.dao.JpaBoardDao;
import com.bs.helloboot.dao.JpaMemberDao;
import com.bs.helloboot.dto.BoardEntity;
import com.bs.helloboot.dto.MemberDto;

@Service
public class JpaServiceImpl implements JpaService {

	private JpaMemberDao memberDao;
	private JpaBoardDao boardDao;
	
	public JpaServiceImpl(JpaMemberDao memberDao, JpaBoardDao boardDao) {
		super();
		this.memberDao = memberDao;
		this.boardDao=boardDao;
	}

	@Override
	public MemberDto selectById(String id) {
		// TODO Auto-generated method stub
		return memberDao.findById(id).orElseThrow();
	}

	@Override
	public void insertMember(MemberDto m) {
		memberDao.save(m);
	}

	@Override
	public List<BoardEntity> selectBoardAll() {
		// TODO Auto-generated method stub
		return boardDao.findAll();
	}

	@Override
	public List<BoardEntity> selectByTitle(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BoardEntity> selectByTitleAndContent(String title, String content) {
		// TODO Auto-generated method stub
		return boardDao.findByBoardTitleLike("%"+title+"%");
	}

	@Override
	public void boardRemove(long boardNo) {
		// TODO Auto-generated method stub
		
	}
}

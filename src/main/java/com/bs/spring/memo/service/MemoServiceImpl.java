package com.bs.spring.memo.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bs.spring.memo.dao.MemoDao;
import com.bs.spring.memo.model.dto.Memo;

@Service
public class MemoServiceImpl implements MemoService {

	@Autowired
	private MemoDao dao;
	
	@Autowired
	private SqlSessionTemplate session;
	
	@Override
	public List<Memo> selectMemoAll() {
		// TODO Auto-generated method stub
		return dao.selectMemoAll(session);
	}

	@Override
	public int insertMemo(Memo memo) {
		return dao.insertMemo(session, memo);
	}

	@Override
	public int memoDelete(int memoNo) {
		// TODO Auto-generated method stub
		return dao.memoDelete(session, memoNo);
	}


}

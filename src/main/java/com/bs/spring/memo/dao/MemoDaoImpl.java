package com.bs.spring.memo.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.bs.spring.memo.model.dto.Memo;

@Repository
public class MemoDaoImpl implements MemoDao {

	
	
	@Override
	public List<Memo> selectMemoAll(SqlSessionTemplate session) {
		// TODO Auto-generated method stub
		return session.selectList("memo.selectMemoAll");
	}

	@Override
	public int insertMemo(SqlSessionTemplate session, Memo memo) {
		// TODO Auto-generated method stub
		return session.insert("memo.insertMemo", memo);
	}

	@Override
	public int memoDelete(SqlSessionTemplate session, int memoNo) {
		// TODO Auto-generated method stub
		return session.delete("memo.memoDelete", memoNo);
	}


}

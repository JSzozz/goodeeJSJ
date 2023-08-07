package com.bs.helloboot.service;

import org.springframework.stereotype.Service;

import com.bs.helloboot.dao.JpaMemberDao;
import com.bs.helloboot.dto.MemberDto;

@Service
public class JpaServiceImpl implements JpaService {

	private JpaMemberDao memberDao;
	
	public JpaServiceImpl(JpaMemberDao memberDao) {
		super();
		this.memberDao = memberDao;
	}

	@Override
	public MemberDto selectById(String id) {
		// TODO Auto-generated method stub
		return memberDao.findById(id).orElseThrow();
	}
}

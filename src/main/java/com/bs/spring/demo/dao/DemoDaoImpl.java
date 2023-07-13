package com.bs.spring.demo.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.bs.spring.demo.model.dto.Demo;

@Repository
public class DemoDaoImpl implements DemoDao {

	@Override
	public int insertDemo(SqlSessionTemplate session, Demo m) {
		// TODO Auto-generated method stub
		return session.insert("dev.insertDemo",m);
	}

	@Override
	public List<Demo> selectDemoAll(SqlSessionTemplate session) {
		// TODO Auto-generated method stub
		return session.selectList("dev.selectDemoAll");
	}
	
	@Override
	public Demo devByDevNo(SqlSessionTemplate session, int devNo) {
		return session.selectOne("dev.devByDevNo", devNo);
	}

	@Override
	public int update(SqlSessionTemplate session, Demo demo) {
		// TODO Auto-generated method stub
		return session.update("dev.update", demo);
	}
}

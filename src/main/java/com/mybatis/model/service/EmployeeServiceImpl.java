package com.mybatis.model.service;

import static com.mybatis.common.SessionTemplate.getSession;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.mybatis.model.dao.EmployeeDao;
import com.mybatis.model.dao.EmployeeDaoImpl;
import com.mybatis.model.dto.Department;
import com.mybatis.model.dto.Employee;

public class EmployeeServiceImpl implements EmployeeService{
	
	private EmployeeDao dao=new EmployeeDaoImpl();

	@Override
	public List<Employee> searchEmployeeAll(){
		SqlSession session=getSession();
		List<Employee> result=dao.searchEmployeeAll(session);
		session.close();
		return result;
	}
	
	@Override
	public int selectEmployeeCount() {
		SqlSession session =getSession();
		int result=dao.selectEmployeeCount(session);
		session.close();
		return result;
	}
	
	@Override
	public List<Employee> selectEmployeePage(int cPage, int numPerpage){
		SqlSession session=getSession();
		List<Employee> result =dao.selectEmployeePage(session, cPage, numPerpage);
		return result;
	}

	@Override
	public List<Employee> searchEmp(Map<String, Object> param) {
		SqlSession session=getSession();
		List<Employee> result =dao.searchEmp(session, param);
		return result;
	}
	
	@Override
	public List<Department> selectAllDept(){
		SqlSession session=getSession();
		List<Department> result = dao.selectAllDept(session);
		session.close();
		return result;
	}
	
}

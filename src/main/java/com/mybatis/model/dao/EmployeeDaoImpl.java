package com.mybatis.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.mybatis.model.dto.Department;
import com.mybatis.model.dto.Employee;

public class EmployeeDaoImpl implements EmployeeDao {

	@Override
	public List<Employee> searchEmployeeAll(SqlSession session){
		return session.selectList("employee.selectEmployeeAll");
	}
	
	@Override
	public List<Employee> selectEmployeePage(SqlSession session, int cPage, int numPerpage){
		RowBounds rb=new RowBounds((cPage-1)*numPerpage, numPerpage);
		return session.selectList("employee.selectEmployeePage", null, rb);
	}
	
	@Override
	public int selectEmployeeCount(SqlSession session) {
		return session.selectOne("employee.selectStudentCount");
	}

	@Override
	public List<Employee> searchEmp(SqlSession session, Map<String, Object> param) {
		return session.selectList("employee.searchEmp", param);
	}
	
	@Override
	public List<Department> selectAllDept(SqlSession session){
		return session.selectList("employee.selectAllDept");
	}
}




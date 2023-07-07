package com.mybatis.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.mybatis.model.dto.Department;
import com.mybatis.model.dto.Employee;

public interface EmployeeDao {

	
	List<Employee> searchEmployeeAll(SqlSession session);
	
	int selectEmployeeCount(SqlSession session);
	
	List<Employee> selectEmployeePage(SqlSession session, int cPage, int numPerpage);
	
//	--------------------------------------------------
	
	List<Employee> searchEmp(SqlSession session, Map<String,Object> param);

	List<Department> selectAllDept(SqlSession session);
	
}

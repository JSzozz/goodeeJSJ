package com.mybatis.model.service;

import java.util.List;
import java.util.Map;

import com.mybatis.model.dto.Department;
import com.mybatis.model.dto.Employee;

public interface EmployeeService {

	List<Employee> searchEmployeeAll();
	
	int selectEmployeeCount();
	
	List<Employee> selectEmployeePage(int cPage, int numPerpage);
	
//	--------------------------------------------------
	
	List<Employee> searchEmp(Map<String,Object> param);
	
	List<Department> selectAllDept();
}

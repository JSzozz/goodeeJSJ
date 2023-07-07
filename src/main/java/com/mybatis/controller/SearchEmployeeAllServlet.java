package com.mybatis.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mybatis.model.dto.Employee;
import com.mybatis.model.service.EmployeeServiceImpl;
import com.mybatis.model.service.EmployeeService;

/**
 * Servlet implementation class SearchEmployeeAllServlet
 */
@WebServlet("/employee/searchEmployeeAll")
public class SearchEmployeeAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private EmployeeServiceImpl service;//v
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchEmployeeAllServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		service=new EmployeeServiceImpl();
		
		List<Employee> result=service.searchEmployeeAll();
		
//		result.stream().forEach(System.out::println);
		
		request.setAttribute("employee", result);
		request.getRequestDispatcher("/views/SearchEmployee.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package com.mybatis.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mybatis.model.dto.Employee;
import com.mybatis.model.service.EmployeeService;
import com.mybatis.model.service.EmployeeServiceImpl;


/**
 * Servlet implementation class SearchEmpServlet
 */
@WebServlet("/searchEmp.do")
public class SearchEmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmployeeService service;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchEmpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service=new EmployeeServiceImpl();
		
		request.setCharacterEncoding("utf-8");
			
		String type = request.getParameter("type");
		String keyword= request.getParameter("keyword");
		
		String gender=request.getParameter("gender");//null, F, M 중 하나
		
		String salary=request.getParameter("salary");
		String salFlag=request.getParameter("salFlag");//ge(이상), le(이하)
//		System.out.println(salary);
//		System.out.println(salFlag);
		
		
		Map<String, Object> param = new HashMap<String,Object>();
		param.put("type",type);
		param.put("keyword",keyword);
		
		param.put("gender",gender);
		
		param.put("salary",salary);
		param.put("salFlag",salFlag);
		
		param.put("salFlag", request.getParameter("salFlag"));
		param.put("deptCodes",request.getParameterValues("deptCode"));
		
		param.put("jobCodes",request.getParameterValues("jobCode"));
		
		param.put("searchStartDate",request.getParameter("searchStartDate"));
		param.put("searchEndDate",request.getParameter("searchEndDate"));
		
		System.out.println(param.get("searchStartDate"));
		System.out.println(param.get("searchEndDate"));
		
		
		List<Employee> list = service.searchEmp(param);
//		list.stream().forEach(System.out::println);
		
		response.setContentType("text/html;charset=utf-8");	
		request.setAttribute("employee", list);
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

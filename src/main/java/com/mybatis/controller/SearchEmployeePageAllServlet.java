package com.mybatis.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mybatis.model.dto.Employee;
import com.mybatis.model.service.EmployeeService;
import com.mybatis.model.service.EmployeeServiceImpl;

/**
 * Servlet implementation class SearchEmployeePageAllServlet
 */
@WebServlet("/employee/searchEmployeePageAll")
public class SearchEmployeePageAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private EmployeeService service;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchEmployeePageAllServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service= new EmployeeServiceImpl();
		
		int cPage,numPerpage;
		
		try {
			cPage=Integer.parseInt(request.getParameter("cPage"));
		}catch(Exception e) {
			cPage=1;
		}
		try {
			numPerpage=Integer.parseInt(request.getParameter("numPerPage"));
		}catch(Exception e) {
			numPerpage=5;
		}
		
		List<Employee> list=service.selectEmployeePage(cPage,numPerpage);
//		System.out.println(list.size());
//		list.stream().forEach(System.out::println);
		request.setAttribute("employee", list);
		
		int totalData=service.selectEmployeeCount();
//		System.out.println(totalData);
		int totalPage=(int)Math.ceil((double)totalData/numPerpage);
		int pageBarSize=5;
		int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1;
		int pageEnd=pageNo+pageBarSize-1;
		
		String pageBar="<ul class='pagination justify-content pagination-lg'>";
		
		if(pageNo==1) {
			//15버전
			pageBar+="""
					<li class='page-item disabled'>
						<a class='page-link' href='#'>이전</a>
					</li>
					""";
		}else {
			pageBar+="<li class='page-item'>";
			pageBar+="<a class='page-link' href='"+request.getRequestURI()
					+"?cPage="+(pageNo-1)+"'>이전</a>";
			pageBar+="</li>";
		}
		
		while(!(pageNo>pageEnd||pageNo>totalPage)) {
			if(cPage==pageNo) {
				pageBar+="<li class='page-item disabled'>";
				pageBar+="<a class='page-link' href='#'>"+pageNo+"</a>";
				pageBar+="</li>";
			}else {
				pageBar+="<li class='page-item'>";
				pageBar+="<a class='page-link' href='"+request.getRequestURI()
						+"?cPage="+(pageNo)+"'>"+pageNo+"</a>";
				pageBar+="</li>";
			}
			pageNo++;
		}
		
		if(pageNo>totalPage) {
			pageBar+="""
					<li class='page-item disabled'>
						<a class='page-link' href='#'>다음</a>
					</li>
					""";
		}else {
			pageBar+="<li class='page-item'>";
			pageBar+="<a class='page-link' href='"+request.getRequestURI()
					+"?cPage="+(pageNo)+"'>다음</a>";
			pageBar+="</li>";
		}
		pageBar+="</ul>";
		request.setAttribute("pageBar", pageBar);
		
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

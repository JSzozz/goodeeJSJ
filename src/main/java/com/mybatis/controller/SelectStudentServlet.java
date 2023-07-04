package com.mybatis.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mybatis.model.dto.Student;
import com.mybatis.model.service.StudentService;


/**
 * Servlet implementation class SelectStudentServlet
 */
@WebServlet("/student/selectStudent.do")
public class SelectStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectStudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int no=Integer.parseInt( request.getParameter("no"));
		Student s=new StudentService().selectStudent(no);
//		System.out.println(s);
		//Student(studentNo=0, studentName=null, studentTel=null, studentEmail=null, studentAddress=null, reg_date=2023-07-04)
		//Student(studentNo=1, studentName=홍길동, studentTel=01012345678, studentEmail=honggd@google.com, studentAddress=서울시 강남구, reg_date=2023-07-04)
		
		request.setAttribute("s", s);
		request.setAttribute("count", new StudentService().selectStudentCount());

		request.getRequestDispatcher("/views/student.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

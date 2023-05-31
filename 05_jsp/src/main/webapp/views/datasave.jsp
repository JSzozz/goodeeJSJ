<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List,com.data.model.vo.Animal"
    %>

<%
	List<Animal> animals=List.of(
			new Animal("뽀송이",2,"여자",4.7),
			new Animal("뽀삐",2,"남자",7.2),
			new Animal("유미",5,"여자",10.2),
			new Animal("톰",2,"남자",8.5)
			);
	request.setAttribute("animals",animals);
	session.setAttribute("loginId","admin");
	application.setAttribute("contextData","data");
		
	/* request.getRequestDispatcher("datacheck.jsp").forward(request,response); */
	
	response.sendRedirect(request.getContextPath()+"/views/datacheck.jsp");
	



%>    
    
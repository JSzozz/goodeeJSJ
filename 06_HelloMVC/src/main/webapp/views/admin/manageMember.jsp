<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>    
<%
	List<MemberDTO> members=(List)request.getAttribute("members");	
%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%@ include file="/views/common/header.jsp"%>
<body>
	<style type="text/css">
    section#memberList-container {text-align:center;}
    
    section#memberList-container table#tbl-member {width:100%; border:1px solid gray; border-collapse:collapse;}
    section#memberList-container table#tbl-member th, table#tbl-member td {border:1px solid gray; padding:10px; }
    </style>
<!--     http://localhost:9090/06_HelloMVC2/admin/memberList.do
    http://localhost:9090/06_HelloMVC2/views/admin/memberlist.jsp -->
    <section id="memberList-container">
        <h2>회원관리</h2>
        <table id="tbl-member">
            <thead>
                <tr>
		            <th>아이디</th>
				    <th>이름</th>
				    <th>성별</th>
				    <th>나이</th>
				    <th>이메일</th>
				    <th>전화번호</th>
				    <th>주소</th>
				    <th>취미</th>
				    <th>가입날짜</th>
                </tr>
            </thead>
            <tbody>
            		<%if(members.isEmpty()){ %>
            			<tr>
            				<td colspan="9">조회된 결과가 없으면 한줄(row)로 결과가 없습니다 출력</td>
						</tr>
		       	    <%} else{
		       	    	for(MemberDTO m:members){%>
			    		<tr>
				            <td><%=m.getUserId() %></td>
				            <td><%=m.getUserName() %></td>
				            <td><%=m.getGender() %></td>
				            <td><%=m.getAge() %></td>
				            <td><%=m.getEmail()!=null?m.getEmail():"-" %></td>
				            <td><%=m.getPhone() %></td>
				            <td><%=m.getAddress()!=null?m.getAddress():"-" %></td>
				            <td><%=m.getHobby()!=null?String.join(",",m.getHobby()):"-" %></td>
				            <td><%=m.getEnrollDate() %></td>
			    		</tr>
			    	<%	} 
		       	     }%>
            	
			    </tr>
            </tbody>
        </table>
        <div id="pageBar">
        	<%=request.getAttribute("pageBar")%>
        </div>
        
        
    </section>
</body>
<%@ include file="/views/common/footer.jsp"%>
</html>
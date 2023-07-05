<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생조회결과</title>
</head>
<body>
	
	<h2>학생정보</h2>
	<c:if test="${count!=null }">
		<h3>전체학생수: <c:out value="${count }"/></h3>
	</c:if>
	<c:if test="${s!=null }">
		<!-- vo 필드 멤버변수명 || db컬럼명으로 들어오기 때문에 삼항연산자 사용함 -->
		<ul>
			<li>학생이름 : <c:out value="${s.studentName!=null?s.studentName:s.STUDENT_NAME }"/></li>
			<li>학생전화번호 : <c:out value="${s.studentTel!=null?s.studentTel:s.STUDENT_TEL }"/></li>
			<li>학생이메일 : <c:out value="${s.studentEmail!=null?s.studentEmail:s.STUDENT_EMAIL }"/></li>
			<li>학생주소 : <c:out value="${s.studentAddress!=null?s.studentAddress:s.STUDENT_ADDR }"/></li>
			<li>등록일 : <c:out value="${s.reg_date!=null?s.reg_date:s.REG_DATE }"/></li>
		</ul>
	</c:if>
	<c:if test="${students.size()>0 }">
		<h3>전체학생수: <c:out value="${students.size() }"/></h3>
		<table>
			<tr>
				<th>번호</th>
				<th>이름</th>
				<th>전화번호</th>
				<th>이메일</th>
				<th>주소</th>
				<th>등록일</th>
			</tr>
			<c:forEach var="s" items="${students }">
			<!-- vo 필드 멤버변수명 || db컬럼명으로 들어오기 때문에 삼항연산자 사용함 -->
				<tr>
					<td><c:out value="${s.studentNo!=null?s.studentNo:s.STUDENT_NO}"/></td>
					<td><c:out value="${s.studentName!=null?s.studentName:s.STUDENT_NAME }"/></td>
					<td><c:out value="${s.studentTel!=null?s.studentTel:s.STUDENT_TEL }"/></td>
					<td><c:out value="${s.studentEmail!=null?s.studentEmail:s.STUDENT_EMAIL }"/></td>
					<td><c:out value="${s.studentAddress!=null?s.studentAddress:s.STUDENT_ADDR }"/></td>
					<td><fmt:formatDate value="${s.reg_date!=null?s.reg_date:s.REG_DATE }"/> </td>
				</tr>
			</c:forEach>
		</table>
	</c:if>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원조회하기</title>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>
	<center><h2>사원조회하기</h2></center>
	<form action="${pageContext.request.contextPath }/searchEmp.do" method="post">
		<table>
			<tr>
				<td>
					<select name="type">
						<option value="emp_id">사원번호</option>
						<option value="emp_name">사원이름</option>
						<option value="email">이메일</option>
						<option value="phone">전화번호</option>
					</select>
				</td>
				<td>
					<input type="text" name="keyword"/>
				</td>
			</tr>
			<tr>
				<td><center><b>성 별</b></center></td>
				<td>
					<input type="radio" name="gender" value="M" >남자
					<input type="radio" name="gender" value="F">여자
				</td>
			</tr>
			<tr>
				<td>급여</td>
				<td>
					<input type="number" name="salary" step="500000" min="1200000">
					<label><input type="radio" name="salFlag" value="ge">이상</label>
					<label><input type="radio" name="salFlag" value="le">이하</label>
				</td>
			</tr>
			<tr>
				<td>
					부서별 조회
				</td>
				<td>
					<label><input type="checkbox" name="deptCode" value="D9">총무부</label>
					<label><input type="checkbox" name="deptCode" value="D1">인사관리부</label>
					<label><input type="checkbox" name="deptCode" value="D2">회계관리부</label>
					<label><input type="checkbox" name="deptCode" value="D3">마케팅부</label>
					<label><input type="checkbox" name="deptCode" value="D4">국내영업부</label>
					<label><input type="checkbox" name="deptCode" value="D5">해외영업1부</label>
					<label><input type="checkbox" name="deptCode" value="D6">해외영업2부</label>
					<label><input type="checkbox" name="deptCode" value="D7">해외영업3부</label>
					<label><input type="checkbox" name="deptCode" value="D8">기술지원부</label>
				</td>
			</tr>
			<tr>
				<td>
					직무별 조회
				</td>
				<td>
					<label><input type="checkbox" name="jobCode" value="J1">대표</label>
					<label><input type="checkbox" name="jobCode" value="J2">부사장</label>
					<label><input type="checkbox" name="jobCode" value="J3">부장</label>
					<label><input type="checkbox" name="jobCode" value="J4">차장</label>
					<label><input type="checkbox" name="jobCode" value="J5">과장</label>
					<label><input type="checkbox" name="jobCode" value="J6">대리</label>
					<label><input type="checkbox" name="jobCode" value="J7">사원</label>
				</td>
			</tr>
			<tr>
				<td>
					입사일
				</td>
				<td>
					조회 시작 날짜 :<label><input type="date" name="searchStartDate" ></label><br>
					조회 마침 날짜 :<label><input type="date" name="searchEndDate" ></label>
				</td>
			</tr>			
			<tr>
				<td>
					<input type="submit" value="검색"/>
				</td>
			</tr>
		</table>
	</form>
	<c:if test="${empty employee }">
		<h2>조회된 사원이 없습니다</h2>
	</c:if>
	<br>
	<c:if test="${not empty employee }">
		<table class="table">
			<tr>
				<th>empId</th>
				<th>empName</th>
				<th>empNo</th>
				<th>email</th>
				<th>phone</th>
				<th>deptCode</th>
				<th>jobCode</th>
				<th>salLevel</th>
				<th>salary</th>
				<th>bonus</th>
				<th>managerId</th>
				<th>hireDate</th>
				<th>entDate</th>
				<th>entYn</th>
				<th>gender</th>
			</tr>
			<c:forEach var="e" items="${employee }">
				<tr>
					<td><c:out value="${e.empId }"/> </td>
					<td><c:out value="${e.empName }"/> </td>
					<td><c:out value="${e.empNo }"/> </td>
					<td><c:out value="${e.email }"/> </td>
					<td><c:out value="${e.phone }"/> </td>
					<td><c:out value="${e.deptCode }"/> </td>
					<td><c:out value="${e.jobCode }"/> </td>
					<td><c:out value="${e.salLevel }"/> </td>
					<td>
						<fmt:formatNumber value="${e.salary }"
						type="currency"/>원
					</td>
					<td>
						<fmt:formatNumber value="${e.bonus }" type="percent"/>
					</td>
					<td><c:out value="${e.managerId }"/> </td>
					<td><c:out value="${e.hireDate }"/> </td>
					<td><c:out value="${e.entDate }"/> </td>
					<td><c:out value="${e.entYn }"/> </td>
					<td><c:out value="${e.gender=='F'?'여':'남' }"/> </td>
				</tr>
			</c:forEach>
		</table>
		<c:if test="${not empty pageBar }">
			<center><c:out value="${pageBar }" escapeXml="false"></c:out></center>
		</c:if>
	</c:if>
	


</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>
<!-- 카테고리별 이미지 -->
<%@ include file="/views/common/categoryImage.jsp"%>

<!-- 컨텐츠/내용 시작 -->
<div class="container">
	<%@ include file="/views/board/board_tab.jsp"%>

	<!--검색창-->
	<div id="select-line">
		<form>
			<div class="select-rooms text-center mt-5 mb-5">
				<div>
					<input type="checkbox" class="btn-check" id="btn-check-outlined-1"
						autocomplete="off"> <label
						class="btn btn-outline-primary me-2" for="btn-check-outlined-1">전체보기</label>
					<input type="checkbox" class="btn-check" id="btn-check-outlined-2"
						autocomplete="off"> <label
						class="btn btn-outline-primary me-2" for="btn-check-outlined-2">회원가입/정보</label>
					<input type="checkbox" class="btn-check" id="btn-check-outlined-3"
						autocomplete="off"> <label
						class="btn btn-outline-primary me-2" for="btn-check-outlined-3">예약/결제</label>
					<input type="checkbox" class="btn-check" id="btn-check-outlined-4"
						autocomplete="off"> <label
						class="btn btn-outline-primary me-2" for="btn-check-outlined-4">취소/환불</label>
					<input type="checkbox" class="btn-check" id="btn-check-outlined-5"
						autocomplete="off"> <label
						class="btn btn-outline-primary me-2" for="btn-check-outlined-5">기타문의</label>
				</div>


				<div class="float-end mt-5">
					<div class="search-area d-flex">
						<select name="search-type" aria-label="">
							<option value="title">제목</option>
							<option value="contents">내용</option>
							<option value="writer">작성자</option>
						</select> <input type="text" name="keyword" placeholder="검색어를 입력해 주세요"
							class="ms-1">
						<button type="button" class="btn btn-primary btn-sm ms-1">검색</button>
					</div>
				</div>
		</form>
	</div>


	<!--테이블-->
	<div class="list-area">
		<table class="table table-hover text-center">
			<colgroup>
				<col width="40px" />
				<col width="90px" />
				<col width="200px" />
				<col width="70px" />
				<col width="50px" />
				<col width="70px" />
			</colgroup>
			<thead>
				<tr>
					<th>번호</th>
					<th>구분</th>
					<th>제목</th>
					<th>작성자</th>
					<th>조회수</th>
					<th>작성일</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>1</td>
					<td>회원가입/정보</td>
					<td><a href="<%=request.getContextPath()%>/board/qnaView.do">질문
							제목입니다.</a></td>
					<td>윤나라</td>
					<td>999</td>
					<td>2023-05-31</td>
				</tr>
				<tr>
					<td>2</td>
					<td>회원가입/정보</td>
					<td><a href="<%=request.getContextPath()%>/board/qnaView.do">질문
							제목입니다.</a></td>
					<td>윤나라</td>
					<td>999</td>
					<td>2023-05-31</td>
				</tr>
				<tr>
					<td>3</td>
					<td>회원가입/정보</td>
					<td><a href="<%=request.getContextPath()%>/board/qnaView.do">질문
							제목입니다.</a></td>
					<td>윤나라</td>
					<td>999</td>
					<td>2023-05-31</td>
				</tr>
				<tr>
					<td>4</td>
					<td>회원가입/정보</td>
					<td><a href="<%=request.getContextPath()%>/board/qnaView.do">질문
							제목입니다.</a></td>
					<td>윤나라</td>
					<td>999</td>
					<td>2023-05-31</td>
				</tr>
				<tr>
					<td>5</td>
					<td>회원가입/정보</td>
					<td><a href="<%=request.getContextPath()%>/board/qnaView.do">질문
							제목입니다.</a></td>
					<td>윤나라</td>
					<td>999</td>
					<td>2023-05-31</td>
				</tr>
				<tr>
					<td>6</td>
					<td>회원가입/정보</td>
					<td><a href="<%=request.getContextPath()%>/board/qnaView.do">질문
							제목입니다.</a></td>
					<td>윤나라</td>
					<td>999</td>
					<td>2023-05-31</td>
				</tr>
				<tr>
					<td>7</td>
					<td>회원가입/정보</td>
					<td><a href="<%=request.getContextPath()%>/board/qnaView.do">질문
							제목입니다.</a></td>
					<td>윤나라</td>
					<td>999</td>
					<td>2023-05-31</td>
				</tr>
				<tr>
					<td>8</td>
					<td>회원가입/정보</td>
					<td><a href="<%=request.getContextPath()%>/board/qnaView.do">질문
							제목입니다.</a></td>
					<td>윤나라</td>
					<td>999</td>
					<td>2023-05-31</td>
				</tr>
				<tr>
					<td>9</td>
					<td>회원가입/정보</td>
					<td><a href="<%=request.getContextPath()%>/board/qnaView.do">질문
							제목입니다.</a></td>
					<td>윤나라</td>
					<td>999</td>
					<td>2023-05-31</td>
				</tr>
				<tr>
					<td>10</td>
					<td>회원가입/정보</td>
					<td><a href="<%=request.getContextPath()%>/board/qnaView.do">질문
							제목입니다.</a></td>
					<td>윤나라</td>
					<td>999</td>
					<td>2023-05-31</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="write-area text-end">
		<a href="<%=request.getContextPath()%>/board/qnaWrite.do"
			class="btn btn-primary btn-sm ms-1">작성하기</a>
	</div>

	<div class="">
		<nav aria-label="Page navigation example">
			<ul class="pagination justify-content-center">
				<li class="page-item"><a class="page-link" href="#"
					aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
				</a></li>
				<li class="page-item active"><a class="page-link" href="#">1</a></li>
				<li class="page-item"><a class="page-link" href="#">2</a></li>
				<li class="page-item"><a class="page-link" href="#">3</a></li>
				<li class="page-item"><a class="page-link" href="#">4</a></li>
				<li class="page-item"><a class="page-link" href="#">5</a></li>
				<li class="page-item"><a class="page-link " href="#"
					aria-label="Next"> <span aria-hidden="true">&raquo;</span>
				</a></li>
			</ul>
		</nav>
	</div>
</div>

<!-- 내용 종료 -->

<%@ include file="/views/common/footer.jsp"%>
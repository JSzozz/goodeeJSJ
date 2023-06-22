<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>
<!-- 컨텐츠/내용 시작 -->
<div class="container">
	<div class="row mt-3 mx-1 border-primary border-bottom border-2"
		style="height: 5rem">
		<div class="col  text-center ">
			<h2>나의 문의사항</h2>
		</div>
	</div>
	<div class="row text-dark min-vh-100">
		<div class="mt-5 col-2 border-end">
			<%@ include file="/views/common/asideNav.jsp"%>
		</div>
		<div class="mt-5 col-9 mx-auto">

			<div class="tb">
				<table class="table table-hover text-center align-middle">
					<colgroup>
						<col width="10%">
						<col width="10%">
						<col width="25%">
						<col width="15%">
						<col width="15%">
					</colgroup>
					<thead>
						<tr>
							<th>번호</th>
							<th>카테고리</th>
							<th>제목</th>
							<th>작성일</th>
							<th>답변여부</th>

						</tr>
					</thead>
					<tbody>
						<tr>
							<td>1</td>
							<td>카테고리1</td>
							<td>제목1</td>
							<td>2023-01-12</td>
							<td>Y</td>
						</tr>

						<tr>
							<td>2</td>
							<td>카테고리2</td>
							<td>제목3</td>
							<td>2023-01-13</td>
							<td>Y</td>
						</tr>

						<tr>
							<td>3</td>
							<td>카테고리3</td>
							<td>제목3</td>
							<td>2023-01-15</td>
							<td>N</td>
						</tr>

						<tr>
							<td>4</td>
							<td>카테고리4</td>
							<td>제목4</td>
							<td>2023-01-22</td>
							<td>N</td>
						</tr>


						<!-- 문의내역 없을 때  -->
						<!-- <tr>
                              <td colspan="5" >
                                작성한 문의가 없습니다
                              </td>
                            </tr>
                            <tr>
                              <td colspan="5">
                                <button type="button" class="btn btn-primary">문의하기</button>
                              </td>
                            </tr> -->

					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>

<%@ include file="/views/common/footer.jsp"%>

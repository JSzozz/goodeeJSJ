<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>
<!-- 컨텐츠/내용 시작 -->
<div class="container">
	<div class="row mt-3 mx-1 border-primary border-bottom border-2"
		style="height: 5rem">
		<div class="col  text-center ">
			<h2>예약내역</h2>
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
						<col width="15%">
						<col width="20%">
						<col width="15%">
						<col width="15%">
						<col width="15%">
					</colgroup>
					<thead>
						<tr>
							<th>번호</th>
							<th>객실사진</th>
							<th>객실명</th>
							<th>예약상태</th>
							<th>예약날짜</th>
							<th>비고</th>
						</tr>
					</thead>
					<tbody>
						<!-- 
                            결제내역이 없을떄
                            <tr>
                              <td colspan="5" class="text-center">예약내역이 없습니다</td>
                            </tr>
                           -->
						<tr>
							<td>1</td>
							<td><img alt=""
								src="<%=request.getContextPath()%>/images/22.jpg"
								class="img-fluid"></td>
							<td>room1</td>
							<td>결제완료</td>
							<td>2023-05-13</td>
							<td><button type="button" class="btn btn-primary btn-sm">결제취소</button></td>
						</tr>
						<tr>
							<td>2</td>
							<td><img alt=""
								src="<%=request.getContextPath()%>/images/33.jpg"
								class="img-fluid"></td>
							<td>room2</td>
							<td>결제대기</td>
							<td>2023-05-13</td>
							<td><button type="button" class="btn btn-primary btn-sm">결제하기</button></td>
						</tr>
						<tr>
							<td>3</td>
							<td><img alt=""
								src="<%=request.getContextPath()%>/images/44.jpg"
								class="img-fluid"></td>
							<td>room2</td>
							<td>결제완료</td>
							<td>2023-05-13</td>
							<td><button type="button" class="btn btn-primary btn-sm">결제취소</button></td>
						</tr>
						<tr>
							<td>4</td>
							<td><img alt=""
								src="<%=request.getContextPath()%>/images/2.jpeg"
								class="img-fluid"></td>
							<td>room2</td>
							<td>결제대기</td>
							<td>2023-05-13</td>
							<td><button type="button" class="btn btn-primary btn-sm">결제하기</button></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>

<%@ include file="/views/common/footer.jsp"%>
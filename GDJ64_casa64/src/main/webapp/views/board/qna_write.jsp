<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>

<!-- 카테고리별 이미지 -->
<%@ include file="/views/common/categoryImage.jsp"%>

<!-- 컨텐츠/내용 시작 -->
<div class="container">
	<%@ include file="/views/board/board_tab.jsp"%>

	<!--게시글 작성하기-->

	<div class="board">
		<div id="content" class="no-bg">
			<form action="<%=request.getContextPath()%>/qna/qnaWrite.do" method="post">
			<input type="hidden" name="MEMBER_NO" value="<%=loginMember.getMemberNo()%>"> <!-- 회원번호 -->
				<div class="inner">
					<div class="inq-write-wrap">
						<div class="board">
							<form>
								<table class="table">
									<colgroup>
										<col style="width: 180px">
										<col style="width: 1020px">
									</colgroup>
									<tbody>
										<tr>
											<th class="text-center align-middle"><span>작성자</span></th>
											<td>
												<div class="td-con">
													<p class="writer"><%=loginMember.getMemberName()%></p>
												</div>
											</td>
										</tr>
										<tr>
											<th class="text-center align-middle">
												구분
											</th>
											<td>
												<div class="td-con">
													<select class="form-select w-15" name="CATEGORY_NAME">
													  <option selected>선택</option>
													  <option value="회원가입/정보">회원가입/정보</option>
													  <option value="예약/결제">예약/결제</option>
													  <option value="취소/환불">취소/환불</option>
													  <option value="기타">기타</option>
													</select>
												</div>
											</td>
										</tr>
										<tr>
											<th class="text-center align-middle"><span>제목</span></th>
											<td>
												<div class="td-con">
													<div class="input-area">
														<input type="text" class="form-control w-50" name="QUESTION_TITLE">
													</div>
												</div>
											</td>
										</tr>
										<tr>
											<th class="text-center align-middle"><span>내용</span></th>
											<td>
												<div class="td-con">
													<div class="editor-wrap">
														<div class="editor-area">
															<!--에디터가 들어가는 영역입니다.-->
															<textarea class="form-control w-100 h-100 review-contents" name="QUESTION_CONTENT"></textarea>
														</div>
													</div>
												</div>
											</td>
										</tr>
									</tbody>
								</table>
							</form>
						</div>
						<div class="d-flex justify-content-center mt-4">
							<button type="button" class="btn btn-dark btn-sm ms-1"
								onclick="location.assign('<%=request.getContextPath()%>/qna/insertQna.do')">뒤로</button>
							<button type="submit" class="btn btn-dark btn-sm ms-1">등록</button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
</div>

<!-- Modal -->
<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static"
	data-bs-keyboard="false" tabindex="-1"
	aria-labelledby="staticBackdropLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered modal-dialog-scrollabl">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="staticBackdropLabel">객실 선택</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div>이용한 객실 목록</div>
				<div class="room-list-area">
					<div class="list-group">
						<a href="javascript:void(0)"
							class="list-group-item list-group-item-action"
							aria-current="true">
							<div class="d-flex w-100 justify-content-between">
								<div class="flex-fill" style="">
									<input type="radio" style="margin-top: 30px;"
										name="checked_room" value="1">
								</div>
								<div class="flex-fill bd-highlight">
									<img src="./images/room.png" width="70" id="thumbnail_img_1">
								</div>
								<div class="flex-fill bd-highlight">
									<h6 class="mb-1">객실명</h6>
									<small id="room_name_1">CASA 1호</small>
								</div>
								<div class="flex-fill bd-highlight">
									<h6 class="mb-1">이용기간</h6>
									<small>2022-12-31</small>
								</div>
							</div>
						</a> <a href="javascript:void(0)"
							class="list-group-item list-group-item-action"
							aria-current="true">
							<div class="d-flex w-100 justify-content-between">
								<div class="flex-fill" style="">
									<input type="radio" style="margin-top: 30px;"
										name="checked_room" value="2">
								</div>
								<div class="flex-fill bd-highlight">
									<img src="./images/room.png" width="70" id="thumbnail_img_2">
								</div>
								<div class="flex-fill bd-highlight">
									<h6 class="mb-1">객실명</h6>
									<small id="room_name_2">CASA 2호</small>
								</div>
								<div class="flex-fill bd-highlight">
									<h6 class="mb-1">이용기간</h6>
									<small>2022-12-31</small>
								</div>
							</div>
						</a> <a href="javascript:void(0)"
							class="list-group-item list-group-item-action"
							aria-current="true">
							<div class="d-flex w-100 justify-content-between">
								<div class="flex-fill" style="">
									<input type="radio" style="margin-top: 30px;"
										name="checked_room" value="3">
								</div>
								<div class="flex-fill bd-highlight">
									<img src="./images/room.png" width="70" id="thumbnail_img_3">
								</div>
								<div class="flex-fill bd-highlight">
									<h6 class="mb-1">객실명</h6>
									<small id="room_name_3">CASA 3호</small>
								</div>
								<div class="flex-fill bd-highlight">
									<h6 class="mb-1">이용기간</h6>
									<small>2022-12-31</small>
								</div>
							</div>
						</a> <a href="javascript:void(0)"
							class="list-group-item list-group-item-action"
							aria-current="true">
							<div class="d-flex w-100 justify-content-between ">
								<div class="flex-fill" style="">
									<input type="radio" style="margin-top: 30px;"
										name="checked_room" value="4">
								</div>
								<div class="flex-fill bd-highlight">
									<img src="./images/room.png" width="70" id="thumbnail_img_4">
								</div>
								<div class="flex-fill bd-highlight">
									<h6 class="mb-1">객실명</h6>
									<small id="room_name_4">CASA 4호</small>
								</div>
								<div class="flex-fill bd-highlight">
									<h6 class="mb-1">이용기간</h6>
									<small>2022-12-31</small>
								</div>
							</div>
						</a>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary btn-room-checked">선택</button>
				<button type="button" class="btn btn-secondary"
					data-bs-dismiss="modal" id="modal_close">닫기</button>
			</div>
		</div>
	</div>
</div>


<!-- 내용 종료 -->


<%@ include file="/views/common/footer.jsp"%>
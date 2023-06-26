<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/nara_publish/css/common.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/nara_publish/smarteditor2/js/HuskyEZCreator.js" charset="utf-8"></script>
<%@ page import="java.util.List,com.btc.review.model.vo.Review" %> <!-- Review 클래스를 사용하기 위해 임포트 -->
<%
	
%> 
<!-- 카테고리별 이미지 -->
<%@ include file="/views/common/categoryImage.jsp"%>

<!-- 컨텐츠/내용 시작 -->
<div class="container">
	<%@ include file="/views/board/board_tab.jsp"%>

<!-- 1.제목 2. 내용 3. 사진  -->

	<!--게시글 작성하기-->
	<div class="board">
		<div id="content" class="no-bg">
			<form action="<%=request.getContextPath()%>/notice/noticeWrite.do" method="post" id="board-form" enctype="multipart/form-data"
			>
			<% 
				int no = (request.getParameter("no") != null) ? Integer.parseInt(request.getParameter("no")) : 0;
				String type = (no > 0) ? "update" : "write";
			%>
			<input type="hidden" name="type" value="<%= type %>">
			<input type="hidden" name="noticeNo" value="<%= no %>">
			<div class="inner">
				<div class="inq-write-wrap">
					<div class="board">
						<table class="table">
							<colgroup>
								<col style="width: 180px">
								<col style="width: 1020px">
							</colgroup>
							<tbody>
								<%-- <tr>
									<th class="text-center align-middle"><span>작성자</span></th>
									<td>
										<div class="td-con">
											<p class="writer"><%=(reviews != null) ? reviews.getNickName() : loginMember.getNickName() %></p>
										</div>
									</td>
								</tr> --%>
								<%-- <tr>
									<th class="text-center align-middle"><span>객실선택</span></th>
									<td>
										<input type="hidden" id="roomNo" name="roomNo" value="<%=(reviews != null) ? reviews.getRoomNo() : "" %>">
										<%
											if(type == "write"){
										%>
										<div class="td-con d-flex flex-row align-items-center">
											<div class="add-room-area" style="display: none;">
												<span class="thumb-area"> <img
													id="selected_room_img" width="70">
												</span> <span class="txt-area"> 이용객실명 </span>
											</div>
											<div class="select-room-area">
												<button class="btn btn-secondary" type="button"
													data-bs-toggle="modal" data-bs-target="#staticBackdrop">객실선택</button>
											</div>
										</div>
										<%
											} else {
										%>
										<div class="td-con d-flex flex-row align-items-center">
											<div class="add-room-area" >
												<span class="thumb-area"> <img
													id="selected_room_img" width="70">
												</span> <span class="txt-area"><%=reviews.getRoomName() %></span>
											</div>
											
										</div>
										<%
											}
										%>
									</td>
								</tr> --%>
								<tr>
									<th class="text-center align-middle"><span>제목</span></th>
									<td>
										<div class="td-con">
											<div class="input-area">
												<input type="text" class="form-control w-50" name="title" >
											</div>
										</div>
									</td>
								</tr>
								<tr>
									<th class="text-center align-middle"><span>내용</span></th>
									<td>
										<div class="td-con">
											<div class="editor-wrap">
												<div id="smarteditor" class="editor-area">
													<!--에디터가 들어가는 영역입니다.-->
													<textarea name="contents" id="contents"></textarea>
												</div>
											</div>
										</div>
									</td>
								</tr>
								<tr>
									<th class="text-center align-middle"><span>첨부파일</span></th>
									<td>
										<div class="td-con">
											<div class="fileBox addfile">
												<span class="inputOuter bTp"> <input type="file"
													id="uploadBtn1" class="uploadBtn" name="uploadFile">
												</span>
											</div>
										</div>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="d-flex justify-content-center mt-4">
						<!--  만약 생성글이라면 -->
						<button type="button" class="btn btn-dark btn-sm ms-1"
							onclick="location.assign('<%=request.getContextPath()%>/notice/insertNotice.do')">뒤로</button>
						<button type="submit" class="btn btn-dark btn-sm ms-1">저장</button>
						<!-- 수정글일 경우 아래 버튼을 보여주면 됨 -->
						<!-- <button class="btn btn-dark btn-sm ms-1">수정</button> -->
						<!-- <button class="btn btn-dark btn-sm ms-1">삭제</button> -->
					</div>
				</div>
			</div>
			</form>
		</div>
	</div>
</div>
<%-- 
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
									<img
										src="<%=request.getContextPath()%>/publish/images/room.png"
										width="70" id="thumbnail_img_1">
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
									<img
										src="<%=request.getContextPath()%>/publish/images/room.png"
										width="70" id="thumbnail_img_2">
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
									<img
										src="<%=request.getContextPath()%>/publish/images/room.png"
										width="70" id="thumbnail_img_3">
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
									<img
										src="<%=request.getContextPath()%>/publish/images/room.png"
										width="70" id="thumbnail_img_4">
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
</div> --%>

<script>

	/* $('.btn-room-checked').on('click', function(e) {
		const $checked = $('input[name="checked_room"]:checked');
		if ($checked.length < 1) {
			alert('객실을 선택해주세요');
			return false;
		} */
		/* const id = $checked.val();
		const img_src = $('#thumbnail_img_' + id).attr('src'); // 선택한 이미지 src
		const room_name = $('#room_name_' + id).text();

		$('#selected_room_img').attr('src', img_src); // 글작성 이미지 src 에 저장 
		$('.txt-area').text(room_name); // 객실명
		$('.txt-area').css('margin-right', '40px');
		$('.add-room-area').show();
		$('#modal_close').click();
		$("#roomNo").val(id);
		return true;
	}) */

	/* 에디터 설정 */
	let oEditors = [];

	smartEditor = function() {
		nhn.husky.EZCreator.createInIFrame({
			oAppRef : oEditors,
			elPlaceHolder : "contents",
			sSkinURI : "<%=request.getContextPath()%>/nara_publish/smarteditor2/SmartEditor2Skin.html",
			fCreator : "createSEditor2",
			htParams: { fOnBeforeUnload : function(){}}
		})
	}
	// form 전송 시 textarea 에 에디터에 쓴 내용을 업데이트해줌
	$("#board-form").on('submit',function(e){
		oEditors.getById["contents"].exec("UPDATE_CONTENTS_FIELD", []);
	});
		
	$(document).ready(function() {
		smartEditor();
	})
</script>

<!-- 내용 종료 -->
<%@ include file="/views/common/footer.jsp"%>
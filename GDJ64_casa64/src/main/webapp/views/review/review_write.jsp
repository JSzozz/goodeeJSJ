<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/nara_publish/css/common.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/nara_publish/smarteditor2/js/HuskyEZCreator.js" charset="utf-8"></script>
<%@ page import="java.util.List,com.btc.review.model.vo.Review, com.btc.mypage.model.vo.Booking" %> 
<%
	Review reviews = (Review)request.getAttribute("reviews");
	List<Booking> bkList = (List)request.getAttribute("bkList");
%> 
<!-- 카테고리별 이미지 -->
<%@ include file="/views/common/categoryImage.jsp"%>
<!-- 컨텐츠/내용 시작 -->
<div class="container">
	<%@ include file="/views/board/board_tab.jsp"%>

	<!--게시글 작성하기-->
	<div class="board">
		<div id="content" class="no-bg">
			<form action="<%=request.getContextPath()%>/review/reviewWrite" method="post" id="board-form" enctype="multipart/form-data">
			<% 
				int no = (request.getParameter("no") != null) ? Integer.parseInt(request.getParameter("no")) : 0;
				String type = (no > 0) ? "update" : "write";
			%>
			<input type="hidden" name="type" value="<%= type %>">
			<input type="hidden" name="reviewNo" value="<%= no %>">
			<div class="inner">
				<div class="inq-write-wrap">
					<div class="board">
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
											<p class="writer"><%=(reviews != null) ? reviews.getNickName() : loginMember.getNickName() %></p>
										</div>
									</td>
								</tr>
								<tr>
									<th class="text-center align-middle"><span>객실선택</span></th>
									<td>
										<input type="hidden" id="roomNo" name="roomNo" value="<%=(reviews != null) ? reviews.getRoomNo() : "" %>">
										<input type="hidden" id="bookingNo" name="bookingNo" value="<%=(reviews != null) ? reviews.getBookingNo() : "" %>">
										<%
											if(type == "write"){
										%>
										<div class="td-con d-flex flex-row align-items-center">
											<div class="add-room-area" style="display: none;">
												<!-- <span class="thumb-area"> <img id="selected_room_img" width="70"> </span>  --> 
												<span id="roomname-txt-area"> 이용객실명 </span>
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
												<span class="txt-area"><%=reviews.getRoomName() %></span>
											</div>
											
										</div>
										<%
											}
										%>
									</td>
								</tr>
								<tr>
									<th class="text-center align-middle"><span>제목</span></th>
									<td>
										<div class="td-con">
											<div class="input-area">
												<input id="title" type="text" class="form-control w-50" name="title" value='<%=(reviews != null) ? reviews.getTitle() : "" %>'>
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
													<textarea name="contents" id="contents"><%=(reviews != null) ? reviews.getContents() : "" %></textarea>
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
												<span class="inputOuter bTp"> 
												<div class="file-area" id="file-area">
													<input type="file" name="uploadFiles1" id="uploadFiles1" class="uploadBtn" accept="image/*"  >
													
												</div>
													<!-- 
													<button type="button" class="btn btn-sm" onclick="addFileArea()">추가</button>
													<button type="button" class="btn btn-sm" onclick="removeFileArea()">삭제</button>
													<input type="file" name="uploadFiles2" id="uploadFiles2" class="uploadBtn" accept="image/*"  >
													<input type="file" name="uploadFiles3" id="uploadFiles3" class="uploadBtn" accept="image/*"  >
													<input type="file" name="uploadFiles4" id="uploadFiles4" class="uploadBtn" accept="image/*"  >
													<input type="file" name="uploadFiles5" id="uploadFiles5" class="uploadBtn" accept="image/*"  > -->
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
						<a href="javascript:history.back()" class="btn btn-dark btn-sm ms-1">뒤로</a>
						<button type="submit" class="btn btn-dark btn-sm ms-1">저장</button>
					</div>
				</div>
			</div>
			</form>
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
					<% if(bkList != null && ! bkList.isEmpty()) {
						for(Booking b : bkList) {
					%>
						<label for="checked_<%=b.getBookingNo() %>">
							<a href="javascript:void(0)"
								class="list-group-item list-group-item-action"
								aria-current="true">
								<div class="d-flex w-100 justify-content-between">
									<div class="flex-fill">
										<input type="radio" class="mt-3" id="checked_<%=b.getBookingNo() %>" name="checked_room" value="<%=b.getRoomNo() %>" 
										data-bookingno="<%=b.getBookingNo() %>">
									</div>
									<div class="flex-fill bd-highlight">
										<h6 class="mb-1">객실명</h6>
										<small id="room_name_<%=b.getBookingNo() %>"><%=b.getRoomName() %></small>
									</div>
									<div class="flex-fill bd-highlight">
										<h6 class="mb-1">이용기간</h6>
										<small><%=b.getCheckIn() %> ~ <%=b.getCheckOut() %></small>
									</div>
								</div>
							</a>
						</label>
						<%} } else { %>
							리뷰 작성 가능한 예약 내역이 없습니다.
						<%}%>
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

<script>
	$('.btn-room-checked').on('click', function(e) {
		const $checked = $('input[name="checked_room"]:checked');
		if ($checked.length < 1) {
			alert('객실을 선택해 주세요!'); // 모달에서 선택했을 때
			return false;
		}
		const booking_id = $checked.attr("data-bookingno");
		const id = $checked.val();
		// const img_src = $('#thumbnail_img_' + id).attr('src'); // 선택한 이미지 src
		const room_name = $('#room_name_' + booking_id).text();
		// $('#selected_room_img').attr('src', img_src); // 글작성 이미지 src 에 저장 
		$('#roomname-txt-area').text(room_name); // 객실명
		$('#roomname-txt-area').css('margin-right', '40px');
		$('.add-room-area').show();
		$("#roomNo").val(id);
		$("#bookingNo").val(booking_id);
		
		$('#modal_close').click();
		return true;
	})

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
		if ($("#title").val().length < 1) {
			alert('제목을 입력해 주세요!');
			$('#title').focus();
			return false;
		} 
		var contents = $("#contents").val();

        if( contents.length < 1 || contents == ""  || contents == null || contents == '&nbsp;' || contents == '<p>&nbsp;</p>')  {
        	alert('내용을 입력해 주세요!');
            oEditors.getById["contents"].exec("FOCUS"); //포커싱
            return false;
        }
        if($('#roomNo').val() == null || $('#roomNo').val() == undefined || $('#roomNo').val() == "" || $('#roomNo').val().length < 1 ){
        	alert('객실을 선택해 주세요!'); // 작성하기를 클릭했을 때
			return false;
        }
	});
		
	$(document).ready(function() {
		smartEditor();
	});
	

</script>

<!-- 내용 종료 -->
<%@ include file="/views/common/footer.jsp"%>
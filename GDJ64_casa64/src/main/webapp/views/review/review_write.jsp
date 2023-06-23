<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/nara_publish/css/common.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/nara_publish/smarteditor2/js/HuskyEZCreator.js" charset="utf-8"></script>
<%@ page import="java.util.List,com.btc.review.model.vo.Review" %> <!-- Review 클래스를 사용하기 위해 임포트 -->
<%
	Review reviews = (Review)request.getAttribute("reviews");
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
													<input type="file" id="uploadBtn1" class="uploadBtn" multiple accept="image/*" onchange="imgUpload(this)">
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
						</a> 
						<a href="javascript:void(0)"
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

<script>

	$('.btn-room-checked').on('click', function(e) {
		const $checked = $('input[name="checked_room"]:checked');
		if ($checked.length < 1) {
			alert('객실을 선택해 주세요!'); // 모달에서 선택했을 때
			return false;
		}
		const id = $checked.val();
		const img_src = $('#thumbnail_img_' + id).attr('src'); // 선택한 이미지 src
		const room_name = $('#room_name_' + id).text();

		$('#selected_room_img').attr('src', img_src); // 글작성 이미지 src 에 저장 
		$('.txt-area').text(room_name); // 객실명
		$('.txt-area').css('margin-right', '40px');
		$('.add-room-area').show();
		$('#modal_close').click();
		$("#roomNo").val(id);
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
	
	let fileNo = 0;  // 첨부된 이미지 총 갯수 (삭제되도 줄어들지않음). 이미지마다 다른 id를 지정하기 위함
	let filesArr = [];  // 첨부된 모든 파일 리스트 (삭제되도 제거되지않음)

	function imgUpload(obj) {  // 파일을 인자값으로 받음

	    let maxFileCnt = 10 ;  // 첨부파일 최대 개수
	    let attFileCnt = document.querySelectorAll('.filebox').length ;   // 기존 추가된 첨부파일 개수
	    																 // 업로드를 하면 바디에 생성된 태그 갯수로 계산
	    let remainFileCnt = maxFileCnt - attFileCnt;    // 추가로 첨부가능한 개수
	    let curFileCnt = obj.files.length;  // 현재 선택된 첨부파일 개수
	    
	    // 첨부파일 개수 확인
	    // 최대 개수 초과 시
	    if (curFileCnt > remainFileCnt) {
	        alert("이미지는 최대 " + maxFileCnt + "개 까지 첨부 가능합니다.");
	    }

	    // 최대 개수 넘지 않았을 시
	    else {
	      	// 첨부된 복수의 파일 하나씩
	        for (const file of obj.files) {
				
	          	// 파일 배열에 담기
	            filesArr.push(file)  // 파일 리스트에 추가
	          
	            // 이미지 미리보기
	            let img = new Image()  // 이미지 태그 객체 생성
	            img.src = URL.createObjectURL(file)  // 이미지 src에 blob으로 이미지 url 생성
	            
	            let previewHtmlData = img
	            previewHtmlData.setAttribute('id', `preview-img-${fileNo}`)  // 이미지마다 다른 id 부여
	            $('.file-input-custom').before(previewHtmlData)
	           	
	            // 이미지 목록에 추가
	            // 업로드한 파일마다 파일이름과 삭제 가능한 버튼을 문서에 붙인다
	            let htmlData = ''
	            htmlData += '<div id="file' + fileNo + '" class="filebox">'
	            htmlData += '   <p class="name">' + file.name + '</p>'
	            htmlData += '   <a class="delete" onclick="deleteFile(' + fileNo + ')">❌</a>'  // 온클릭 이벤트 추가하고 해당 파일을 파라미터에 담음
	            htmlData += '</div>'
	            $('.file-list').append(htmlData)

	            fileNo++  // 파일 번호 +1
	        }
	    }
	}

	// 첨부파일 삭제
	// 동적으로 생성된 html 태그에서 파일 이름 옆 ❌버튼을 누르면 작동
	function deleteFile(num) {
	    document.querySelector("#file" + num).remove()
	    document.querySelector("#preview-img-" + num).remove()
	    filesArr[num].is_delete = true  // 파일 리스트의 해당 인덱스에 is_delete=true 라는 키와 값을 추가한다
	  	/*
	    배열을 삭제하지 않고 남겨두는 이유는
	  	fileNo를 통해서 순서대로 리스트에 추가했고
	  	fileNo로 index 조회를 하고있기에
	  	fileNo와 리스트에 저장된 index 번호가 달라지면 안되기 때문이다
	    */
	}
	
</script>

<!-- 내용 종료 -->
<%@ include file="/views/common/footer.jsp"%>
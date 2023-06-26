<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/nara_publish/css/common.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/nara_publish/smarteditor2/js/HuskyEZCreator.js" charset="utf-8"></script>
<%
	int no = (request.getParameter("no") != null) ? Integer.parseInt(request.getParameter("no")) : 0;
%> 
<!-- 카테고리별 이미지 -->
<%@ include file="/views/common/categoryImage.jsp"%>

<!-- 컨텐츠/내용 시작 -->
<div class="container">
	<%@ include file="/views/board/board_tab.jsp"%>
	
	<!--게시글 작성하기-->
	<div class="board">
		<div id="content" class="no-bg">
			<form action="<%=request.getContextPath()%>/notice/noticeWrite.do" method="post" id="board-form" enctype="multipart/form-data">
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
					</div>
				</div>
			</div>
			</form>
		</div>
	</div>
</div>

<script>
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
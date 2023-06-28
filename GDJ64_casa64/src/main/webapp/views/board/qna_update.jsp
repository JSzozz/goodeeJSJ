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
			<form action="<%=request.getContextPath()%>/qna/updateQnaEnd.do" method="post" id="board-form">
			<% 
				/* int no = (request.getParameter("no") != null) ? Integer.parseInt(request.getParameter("no")) : 0; */
                /* String type = (no > 0) ? "update" : "write"; */
			%>
			<%-- <input type="hidden" name="type" value="<%= type %>"> --%>
			<input type="hidden" name="noticeNo" value="<%= no %>">
			<input type="hidden" name="MEMBER_NO" value="<%=request.getAttribute("memberNo")%>">
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
											<select id="selectChange" class="form-select w-15" name="CATEGORY_NAME">
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
												<input type="text" class="form-control w-50" name="QUESTION_TITLE" value='<%=request.getAttribute("questionTitle")%>'>
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
													<textarea class="form-control w-100 h-100 review-contents" name="QUESTION_CONTENT"><%=request.getAttribute("questionContent")%></textarea>
												</div>
											</div>
										</div>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="d-flex justify-content-center mt-4">
						<button type="button" class="btn btn-dark btn-sm ms-1">뒤로</button>
						<button type="submit" class="btn btn-dark btn-sm ms-1">저장</button>
					</div>
				</div>
			</div>
			</form>
		</div>
	</div>
</div>


<script>
	$('#selectChange').val('<%=request.getAttribute("categoryName")%>').prop("selected",true);
	
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
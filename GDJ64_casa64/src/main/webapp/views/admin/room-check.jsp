<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/admin/common/header.jsp"%>
<%@ page import="com.btc.rooms.model.vo.Room" %>
<%
	Room r=(Room)request.getAttribute("room");
%>
<!-- 삭제 모달 -->
<div class="modal fade" id="removeRoom" tabindex="-1"
	aria-labelledby="removeRoomModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="removeRoomModalLabel">경고</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">객실을 정말 삭제합니까?</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-danger">삭제</button>
				<button type="button" class="btn btn-dark" data-bs-dismiss="modal">취소</button>
			</div>
		</div>
	</div>
</div>

<!-- 컨테츠 -->
<section id="roomDetail-container">
	<div class="container-fluid">
		<div class="row">
			<div class="col-xl-10 col-lg-9 col-md-8 ms-auto">
				<div class="row pt-md-5 mt-md-3 mb-4">
					<h2 class="text-center">상세조회</h2>
				</div>
				<div class="col-12">
					<form id="roomFrm" method="post" class="mt-5">
						<!-- 객실명 -->
						<div class="form-group row mt-3 align-items-center">
							<label for="roomName" class="col-sm-1 col-form-label text-center">객실명</label>
							<div class="col-sm-4">
								<input type="text" name="roomName" class="form-control"
									value="<%=r.getRoomName()%>">
							</div>
						</div>
						<!-- 객실정보 -->
						<div class="form-group row mt-3 align-items-center">
							<label for="roomInfo" class="col-sm-1 col-form-label text-center">객실소개</label>
							<div class="col-sm-11">
								<textarea id="roomInfo" class="form-control" rows="4"
									name="roomDescription"><%=r.getRoomDescription()%></textarea>
							</div>
						</div>
						<!-- 인원 -->
						<div class="form-group row mt-3 align-items-center">
							<label for="capacity" class="col-sm-1 col-form-label text-center">기준인원</label>
							<div class="col-sm-2">
								<input type="number" name="roomCap" class="form-control"
									value="<%=r.getRoomCap()%>">
							</div>
							<label for="maxCapcity"
								class="col-sm-1 col-form-label text-center">최대인원</label>
							<div class="col-sm-2">
								<input type="number" name="roomMaxCap" class="form-control"
									value="<%=r.getRoomMaxCap()%>">
							</div>
						</div>
						<!-- 크기 -->
						<div class="form-group row mt-3 align-items-center">
							<label for="size" class="col-sm-1 col-form-label text-center">사이즈(m^2)</label>
							<div class="col-sm-3">
								<input type="number" name="roomSize" class="form-control"
									value="<%=r.getRoomSize()%>">
							</div>
						</div>
						<!-- 가격 -->
						<div class="form-group row mt-3 align-items-center">
							<label for="price" class="col-sm-1 col-form-label text-center">가격(원)</label>
							<div class="col-sm-3">
								<input type="number" name="roomPrice" class="form-control"
									value="<%=r.getRoomPrice()%>">
							</div>
						</div>
						<!-- 옵션 -->
						<div class="form-group row mt-3 align-items-center">
							<label for="option" class="col-sm-1 col-form-label text-center">옵션</label>
							<div class="col-sm-2">
								<button type="button" id="option" class="btn btn-dark">옵션설정</button>
							</div>
						</div>
						<!-- 객실 사진 -->
						<div class="form-group row mt-3 align-items-center">
							<label for="attachment"
								class="col-sm-1 col-form-label text-center">객실사진</label>
							<div class="col-sm-4">
								<input type="file" name="roomImage" class="form-control"
									multiple>
							</div>
						</div>
						<!-- 객실 공개 -->
						<div class="form-group row mt-3 align-items-center">
							<label class="col-sm-1 col-form-label text-center">공개설정</label>
							<div class="col-sm-11 d-flex align-items-center">
								<!-- 공개/비공개 라디오 설정. y이면 공개 체크, 아니면 반대 -->
								<input type="radio" name="bookable" value="Y"
									<%=r.getBookable() == 'Y' ? "checked" : ""%>>공개 <input
									type="radio" name="bookable" value="N"
									<%=r.getBookable() == 'N' ? "checked" : ""%>>비공개
							</div>
						</div>
				</div>
				<!-- 관리자 버튼 -->
				<div class="d-flex justify-content-end mt-2">
					<input type="button" class="btn btn-dark me-2"
						onclick="fn_updateRoom();" value="수정">
					<button type="submit" class="btn btn-danger" data-bs-toggle="modal"
						data-bs-target="#removeRoom">삭제</button>
				</div>
				</form>
			</div>
		</div>
	</div>
</section>
<script>
	const fn_updateRoom=()=>{
		//form을 전송하기
		$("roomFrm").attr("action","<%=request.getContextPath()%>/admin/room/updateRoom.do").submit();
	}
</script>
<%@ include file="/views/admin/common/footer.jsp"%>
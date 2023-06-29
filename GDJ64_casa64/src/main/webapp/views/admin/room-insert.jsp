<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/admin/common/header.jsp"%>
<%@ page import="com.btc.rooms.model.vo.*,java.util.List" %>
<%
	
	List<OptionFree> frees=(List<OptionFree>)request.getAttribute("frees");
	
%>
<!-- 컨테츠 -->
<section>
	<div class="container-fluid">
		<div class="row">
			<div class="col-xl-10 col-lg-9 col-md-8 ms-auto">
				<div class="row pt-md-5 mt-md-3 mb-4">
					<h2 class="text-center">객실추가</h2>
				</div>
				<div class="col-12">
					<form
						action="<%=request.getContextPath()%>/admin/room/insertRoomEnd.do"
						class="mt-5" method="post" enctype="multipart/form-data">
						<!-- multipart폼으로 만들어서 -->
						<!-- 객실명 -->
						<div class="form-group row mt-3 align-items-center">
							<label for="roomName" class="col-sm-1 col-form-label text-center">객실명</label>
							<div class="col-sm-4">
								<input type="text" name="roomName" class="form-control"
									placeholder="객실명을 입력해주세요.">
							</div>
						</div>
						<!-- 객실정보 -->
						<div class="form-group row mt-3 align-items-center">
							<label for="roomInfo" class="col-sm-1 col-form-label text-center">객실소개</label>
							<div class="col-sm-11">
								<textarea id="roomInfo" class="form-control" rows="4" name="roomDescription" placeholder="객실정보를 입력해주세요."></textarea>
							</div>
						</div>
						<!-- 인원 -->
						<div class="form-group row mt-3 align-items-center">
							<label for="capacity" class="col-sm-1 col-form-label text-center">기준인원</label>
							<div class="col-sm-2">
								<input type="number" id="capacity" class="form-control" name="roomCap" placeholder="기준인원">
							</div>
							<label for="maxCapcity"
								class="col-sm-1 col-form-label text-center">최대인원</label>
							<div class="col-sm-2">
								<input type="number" id="maxCapcity" class="form-control" name="roomMaxCap" placeholder="최대인원">
							</div>
						</div>
						<!-- 크기 -->
						<div class="form-group row mt-3 align-items-center">
							<label for="size" class="col-sm-1 col-form-label text-center">사이즈(m^2)</label>
							<div class="col-sm-3">
								<input type="number" name="roomSize" class="form-control">
							</div>
						</div>
						<!-- 가격 -->
						<div class="form-group row mt-3 align-items-center">
							<label for="price" class="col-sm-1 col-form-label text-center">가격</label>
							<div class="col-sm-3">
								<input type="number" id="price" class="form-control" name="roomPrice" placeholder="가격을 입력해주세요.">
							</div>
						</div>
						<!-- 옵션 -->
						<div class="form-group row mt-3 align-items-center">
							<label for="option" class="col-sm-1 col-form-label text-center">옵션</label>
							<div class="col-sm-8">
								
								<%if(frees==null||frees.isEmpty()){ %>
									<p>설정된 기본 옵션이 없습니다. 옵션관리에서 옵션을 추가해 주세요</p>
								<%}else{
									for(OptionFree of:frees){%>
									<label>
										<input type="checkbox" name="optionFree" value="<%=of.getFreeNo() %>" ><%=of.getFreeName() %>
									</label>
									<%}
								}%>
								
							</div>
						</div>
						<!-- 객실 사진 -->
						<div class="form-group row mt-3 align-items-center">
							<label for="attachment"
								class="col-sm-1 col-form-label text-center">객실사진</label>
							<div class="col-sm-4">
								<input type="file" id="attachment" class="form-control" name="roomImage">
								<div id="imagePreview"></div>
							</div>
						</div>
						<!-- 객실 공개 -->
						<div class="form-group row mt-3 align-items-center">
							<label class="col-sm-1 col-form-label text-center">공개설정</label>
							<div class="col-sm-11 d-flex align-items-center">
								<div class="form-check form-check-inline">
								<label>
									<input class="form-check-input" type="radio" name="bookable"
										id="public" value='Y' />공개<!--  <label class="form-check-label"
										for="public">공개</label> -->
								</label>
								</div>
								<div class="form-check form-check-inline">
								<label>
									<input class="form-check-input" type="radio" name="bookable"
										id="private" value='N' />비공개
								</label>
								</div>
							</div>
						</div>
						<div class="d-flex justify-content-end mt-2">
							<button type="submit" class="btn btn-dark" id="addRoom">객실추가</button>		
							<!-- 넘어가서 빈페이지나오네-->			
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- <div class="modal fade" id="addOption" tabindex="-1" aria-labelledby="addOptionModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="addOptionModalLabel">객실 내 기본옵션 추가</h5>
					<table>
						
					</table>
					
					 -->
</section>
<script>
	$("#attachment").change(e=>{
		$("#imagePreview").html('');
		const files=e.target.files;
		$.each(files,(i,f))=>{
			const reader=new FileReader();
			reader.onload=e=>{
				const img=$("<img>").attr({
					"src":e.target.result,
					"width":"210",
					"height":"100"
				}}
			$("#imagePreview").append(img);
		}
		reader.readAsDataURL(f);
	})

</script>
<%@ include file="/views/admin/common/footer.jsp"%>
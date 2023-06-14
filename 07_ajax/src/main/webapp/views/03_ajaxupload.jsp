<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ajax파일 업로드시키기</title>
<script src="http://code.jquery.com/jquery-3.7.0.min.js"></script>
</head>
<body>
	<h2>ajax를 이용해서 파일 업로드하기</h2>
	<input type="file" id="upFile" multiple>
	<button	id="uploadBtn">업로드파일</button>
	<script>
		$("#uploadBtn").click(e=>{
			//js가 제공하는 FormData클래스를 이용함.
			const form=new FormData();
			//append로 서버에 전송할 데이터를 넣을 수 있음
			//key:vaule형식으로 저장
			const fileInput=$("#upFile");
			console.log(fileInput);
			$.each(fileInput[0].files,(i,f)=>{
				form.append("upfile"+i,f);
			});
			form.append("name", "유병승");
			$.ajax({
				url:"<%=request.getContextPath()%>/fileUpload", 
				/* com.ajax.controller - AjaxFileUploadServlet */
				data:form,
				type:"post",	//v
				processData:false, // v : multyfile로 보내기 위함
				contentType:false, // v
				success:data=>{
					console.log(data);
					alert("업로드 완료");
				},
				error:(r,m)=>{
					alert("업로드 실패"+ m);
				},
				complete:()=>{
					$("#upFile").val(''); // 업로드 요청된 파일 값 지우기!
				}
			});
			
		});
	</script>
</body>
</html>
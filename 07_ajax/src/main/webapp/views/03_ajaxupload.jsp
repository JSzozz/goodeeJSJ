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
	
	<h2>업로드 이미지 미리보기 기능</h2>
	
	<img src="https://img.freepik.com/free-icon/user_318-804790.jpg"
	width="100" height="100" id="profile">
	<input type="file" style="display:none" id="profileInput" accept="image/*">
	<div id="uploadpreview"></div>
	
	<input type="file" id="upfiles" multiple accept="image/*">
	<div id="1"></div>

	
	<script>
		$("#upfiles").change(e=>{
			/* const reader = new FileReader();
			reader.onload=(e)=>{
				const img =$("<img>").attr("src", e.target.result).attr("width", "100"); 
				$("#1").append(img);
			}
			 reader.readAsDataURL(e.target.files[0]); */

			 $("#uploadpreview").html('');
			 const files=e.target.files;
				$.each(files,(i,f)=>{
					const reader=new FileReader();
					reader.onload=e=>{
						const img=$("<img>").attr({
							src:e.target.result,
							"width":"100",
							"height":"100"
						});
						$("#uploadpreview").append(img);
					}
					reader.readAsDataURL(f);
				})
		});

		$("#profile").click(e=>{
			$("#profileInput").click(); // 숨어있는 input태그가 클릭되는 효과!
		});	
		
		$("#profileInput").change(e=>{
			const reader = new FileReader();
			reader.onload=(e)=>{
				//e.target.result속성에 변경된 file이 나옴 ~ readerAsDataURL(e.target.files[0])
				 $("#profile").attr("src", e.target.result); 
			}
				 reader.readAsDataURL(e.target.files[0]); 
		});
		
	</script>
	<style>
		#profile{
			border-radius:100px;
			border: 1px solid yellow;
		}
	</style>
	
	
	
	
	
</body>
</html>
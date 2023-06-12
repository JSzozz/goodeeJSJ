<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ajax통신 활용하기</title>
</head>
<body>

	<p>ajax : 비동기식으로 서버와 통신 하는 기술</p>
	<h2>javascript로 ajax통신 구현하기</h2>
	<p>
		javascript로 비동기통신을 구현하려면 js가 제공하는 객체를 이용<br>
		XMLHttpRequest객체를 이용
		1. XMLHttpRequest객체를 생성
		2. 필요한 속성에 대한 설정
			- 요청할 서버의 주소, 요청 방식 등
			- 요청이 끝나고 실행할 함수를 설정()
		3. 요청보내기 함수 실행 -> send()	
	</p>
	<h3>js로 요청보내기</h3>
	<button id="jsSendBtn">js로 ajax통신</button>
	<div id="result"></div>
	<script>
		const sendBtn=document.getElementById("jsSendBtn");
		sendBtn.addEventListener("click",e=>{
			const request=new XMLHttpRequest();
		});
	</script>
</body>
</html>
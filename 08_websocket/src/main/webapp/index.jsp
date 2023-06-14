<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>웹소켓 테스트</title>
<script src="http://code.jquery.com/jquery-3.7.0.min.js"></script>

</head>
<body>
	<div id="container">
		<input type="text" id="sendMsg">
	</div>
	<div id="msgcontainer"></div>

	<script>
	
		//웹소켓서버에 접속하기
		//ws:// -> http프로토콜을 이용할때 사용
		//wss:// -> https프로토콜을 이용할때 사용
		// error : index.jsp:21 WebSocket connection to 'ws://localhost:9090//08_websocket/chatting' failed: 
		const socket= new WebSocket("ws://localhost:9090/<%=request.getContextPath()%>/chatting");
		socket.onopen=(e)=>{
			console.log(e);
		}
		//
		socket.onmessage=(e)=>{
			console.log(e);
		}
	</script>


</body>
</html>
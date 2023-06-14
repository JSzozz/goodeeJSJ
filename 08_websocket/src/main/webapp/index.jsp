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
		<input type="text" id="sender" placeholder="보내는 사람"><br>
		<input type="text" id="sendMsg"> &nbsp;<button id="sendBtn">전송</button>
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
			/* socket.send("안녕"); */
		}
		//
		socket.onmessage=(e)=>{
			console.log(e);
			const msg=e.data.split(",");
			const sup=$("<sup>").text(msg[0]+" : ");
			const span=$("<span>").text(msg[1]).css("fontsize", "10px");
			const textContainer=$("<div>").append(sup).append(span);
			
			$("#msgcontainer").append(textContainer);
		}
		$("#sendBtn").click(e=>{
			const msg=$("#sendMsg").val();
			let sender=$("#sender").val();
			sendMsg(sender+","+msg)
		});
		function sendMsg(msg){
			if(msg.length>0)
				socket.send(msg);
			else throw new Error("메세지가 비어있습니다");
				
		}		
	</script>


</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>채팅페이지</title> 
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<!-- jQuery library -->
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
<!-- Popper JS -->
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>

<script src="${pageContext.request.contextPath }/resources/js/jquery-3.7.0.min.js"></script>
</head>
<body>
	<div class="container-fluid">
		<h2>스프링 채팅하자!</h2>
		<div id="chattingcontainer"></div>
		<div class="row">
			<div class="col-xm-10">
				<input class="form-control" type="text" id="msg">
			</div>
			<div class="col-xm-2">
				<button class="btn btn-outline-danger">전송</button>
			</div>
		</div>
	</div>
	<script>
		const server=new WebSocket("ws://localhost:8080/spring/chatting");
		server.onopen=data=>{
			server.send(JSON.stringify(new Message("open",'${loginMember.userId}')));
		}
		server.onmessage=data=>{
			
		}
		server.onclose=data=>{
			
		}
		class Message{
			constructor(type="",sender="",reciever="",msg="",room=""){
				this.type=type;
				this.sender=sender;
				this.reciever=reciever;
				this.msg=msg;
				this.room=room;
			}
		}
	</script>
</body>
</html>
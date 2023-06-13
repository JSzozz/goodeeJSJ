<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jqueryajax</title>
<script src="http://code.jquery.com/jquery-3.7.0.min.js"></script>
</head>
<body>
	<h2>jqeury가 제공하는 함수이용하기</h2>
	<ol>
		<li>$.ajax({}) : 요청에 대한 상세설정을 할때, header, 요청내용 설정</li>
		<li>$.get("",(data)=>{}) : 기본 get방식요청 처리할때 사용 - 간편함수</li>
		<li>$.post("",{},(data)=>{}) : 기본 post방식요청 처리할때 사용 - 간편함수</li>
	</ol>
	<h2>$.ajax()함수 활용하기</h2>
	<p>
		매개변수로 요청에 대한 설정을 한 객체를 전달한다.<br> 
		매개변수 객체의 key값은 $.ajax()함수에서 정해놓음<br>
		url : 요청주소를 설정 -> string<br> 
		[type : 요청방식(get,post) -> string default : get방식임 ]<br> 
		[data : 서버에 요청할때 전송할 데이터 -> Object({key:value,...})]<br> 
		[dataType : 응답데이터 타입에 대한 설정 -> string(json,html,text...)]<br> 
		success : 응답이 완료되고 실행되는 callback함수 status 200일때(성공) 실행하는 함수 -> (data)=>{}<br> 
		[error : 응답이 완료되고 실행되는 callback함수 status가 200이 아닐때 실행하는 함수 -> (e,r,m)=>{}]<br> 
		[complete : 응답이 성공,실패되도 무조건실행되는 함수 -> ()=>{}]<br>
	</p>

	<button id="btn">기본 $.ajax이용하기</button>
	<button id="btnGet">기본 $.get이용하기</button>
	<button id="btnPost">기본 $.post이용하기</button>
	<div id="container"></div>
	<script>
		$("#btnGet").click(e=>{
			$.get("<%=request.getContextPath()%>/jquery/ajax.do?name=최주영&age=27"
					,data=>{
						console.log(data);
						$("<h4>").text(data).css("color","lime")
						.appendTo($("#container"));
					});
		});
		
		$("#btnPost").click(e=>{
			$.post("<%=request.getContextPath()%>/jquery/ajax.do",
					{name:"김재훈",age:29},
					data=>{
						$("<h1>").text(data).css("color","cornflowerblue")
						.appendTo($("#container"));}
					);
		});
		
		$("#btn").click(e=>{
			/* const request={
					
			}
			$.ajax(request); */
			$.ajax({
				url:"<%=request.getContextPath()%>/jquery/ajax.do",
				//type:"get",
				type:"post",
				data:{name:"유병승",age:19},
				success:(data)=>{
					//responseText속성에 저장된 값을 data에 대입함.
					//console.log(data);
					$("<h3>").text(data).css("color","magenta")
					.appendTo($("#container"));
				},
				error:(r,m)=>{
					console.log(r);
					console.log(m);
					//console.log(e);
					if(e.status==404) alert("요청한 페이지가 없습니다");
				},
				/* complete:()=>{
					alert("서버와 통신 끝!");
				} */
			});
		});
	</script>

	<h2>서버에 저장되어있는 문자파일가져오기</h2>
	<button id="btnFile">test.txt가져오기</button>
	<div id="result2"></div>
	<script>
		$("#btnFile").click(e=>{
			$.get("<%=request.getContextPath()%>/test/test.txt",
					data=>{
						const arr=data.split("\n");
						arr.forEach(e=>{
							$("#result2").append($("<p>").text(e));
						});
						const person=arr[arr.length-1];
						const persons=person.split("\\n");
						const table=$("<table>");
						persons.forEach(e=>{
							const tr=$("<tr>");
							const p=e.split(",");
							p.forEach(d=>{
								tr.append($("<td>").text(d).css("border","1px solid black"));
							});
							table.append(tr);
						});
						$("#result2").append(table);
					});
		<%-- 	$.ajax({
				url:"<%=request.getContextPath()%>/test/test.txt",
				dataType:"text",
				success:data=>{
					console.log(data);
					const arr=data.split("\n");
					console.log(arr);
					arr.forEach(e=>{
						$("#result2").append($("<p>").text(e));
					});
					const person=arr[arr.length-1];
					console.log(person);
					const persons=person.split("\\n");
					console.log(persons);
					const table=$("<table>");
					persons.forEach(e=>{
						const tr=$("<tr>");
						const p=e.split(",");
						p.forEach(d=>{
							tr.append($("<td>").text(d).css("border","1px solid black"));
						});
						table.append(tr);
					});
					$("#result2").append(table);
				}
			}); --%>
		});
	</script>


	<h2>서버에서 전송하는 csv방식의 데이터처리하기</h2>
	<p>문자열을 데이터별로 구분할 수 있게 만들어놓은 것! , \n ,$등으로 구분할 수 있는 문자열 예)
		유병승,19,남,경기도시흥$최솔,27,여,경기도안양시$</p>
	<button id="btncsv">csv데이터 가져오기</button>
	<div id="csvcontainer"></div>
	<script>
		$("#btncsv").click(e=>{
			$.ajax({
				url:"<%=request.getContextPath()%>/ajax/csvdata.do",
				dataType:"text",
				success:data=>{
					console.log(data);
					if(data!=""){
						const actors=data.split("\n");
						console.log(actors);
						const table=$("<table>");
						const header="<tr><th>이름</th><th>전화번호</th><th>프로필</th></tr>";
						table.html(header);
						actors.forEach(e=>{
							const tr=$("<tr>");
							const actor=e.split(",");
							const name=$("<td>").text(actor[0]);
							const phone=$("<td>").text(actor[1]);
							const profile=$("<td>").append($("<img>").attr({
								"src":"<%=request.getContextPath()%>/images/"+actor[2],
								"width":100,
								"height":100
								}));
							tr.append(name).append(phone).append(profile);
							table.append(tr);
						});
						$("#csvcontainer").html(table);
					}else{
						$("#csvcontainer").html("<h3>조회된 데이터가 없습니다</h3>");
					}
				}
			});
		});
	</script>

	<h2>html페이지</h2>
	<button id="btnhtml">btnhtml</button>
	<div id="htmlcontainer"></div>
	<script>
		$("#btnhtml").click(e=>{
			$.ajax({
				url:"<%=request.getContextPath()%>/ajax/htmlTest.do",
				dataType:"html",
				success:(data)=>{
					console.log(data);
					$("#htmlcontainer").text(data);
				}
			});
		});
		
		
	</script>
	





</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value="메인화면"/>
</jsp:include>    

<section id="content">
	<h2>Hello Spring</h2>
	<h3>ajax통신하기</h3>
	<h4><button class="btn btn-outline-primary" onclick="basicAjax();">기본ajax처리</button></h4>
	<h4><button class="btn btn-outline-success" onclick="convertAjax();">json받기</button></h4>
	<h4><button class="btn btn-outline-warning" onclick="basic2();">jsp받아오기</button></h4> 
	<h4><button class="btn btn-outline-danger" onclick="selectAll();">전체회원 가져오기</button></h4>
	<h4><button class="btn btn-outline-dark" onclick="insertData();" >데이터 저장하기</button></h4>
	<div id="ajaxContainer"></div>
	
	<script>
	const insertData=()=>{
		const data={userId:"test1",password:"test",userName:"테스트",age:19,gender:"M"};
		/* $.post("${pageContext.request.contextPath}/ajax/insertData.do",
				{userId:"test1",password:"test",userName:"테스트",age:19,gender:"M"},
				data=>{
					console.log(data);
				}); */
		/* $.ajax({
			url:"${pageContext.request.contextPath}/ajax/insertData.do",
			type:"post",
			data:JSON.stringify(data),
			contentType:"application/json;charset=utf-8",
			success:data=>{
				console.log(data);
			}
		});			 */		
		
		//fetch함수를 제공함. -> 다른라이브러리가 필요없다
		//fetch("URL주소",{요청에 대한 옵션})
		//	.then(response=>response.json())//응답내용파싱,,에러처리
		//	.then(data=>{처리로직})//success함수
		//	.catch(e=>{})//예외처리2
		
		/* fetch("${pageContext.request.contextPath}/ajax/memberAll.do",{
			method:"get",
			//headers:{}
			//body:JSON.stringify(객체)
		}).then(response=>{
					console.log(response);
					if(!response.ok) throw new Error("요청실패!"); 
					return response.json()
				}
		).then(data=>{
			console.log(data)
			}
		).catch(e=>{
			alert(e);
		}); */
		
		fetch("${pageContext.request.contextPath}/ajax/insertData.do",{
			method:"post",
			headers:{
				"Content-type":"application/json"
			},body:JSON.stringify(data)
		}).then(response=>{
				if(!response.ok) new Error("입력실패"); 
				return response.json()//서버가 json으로 응답했을 때
				//일반문자를 반환했을 때 response.text()
			}
		)
		.then(data=>{
			console.log(data);
		}).catch(e=>{
			
		});
							
	}
		

	const selectAll=()=>{
		$.get("${pageContext.request.contextPath}/ajax/memberAll.do", data=>{
			console.log(data);
			const table=$("<table>");
			const header=["아이디","이름","나이","성별","이메일","전화번호","주소","취미","가입일"];
			const tr=$("<tr>");
			header.forEach(e=>{
				const th=$("<th>").text(e);
				tr.append(th);
			})
			table.append(tr);
			data.forEach(e=>{
				const bodyTr=$("<tr>");
				const userId=$("<td>").text(e.userId);
				const name=$("<td>").text(e.userName);
				const age=$("<td>").text(e.age);
				const gender=$("<td>").text(e.gender);
				const email=$("<td>").text(e.email);
				const phone=$("<td>").text(e.phone);
				const address=$("<td>").text(e.address);
				const hobby=$("<td>").html(e.hobby.toString());
				const enrollDate=$("<td>").text(new Date(e.enrollDate));
				bodyTr.append(userId).append(name).append(age).append(gender)
				.append(email).append(phone).append(address).append(hobby).append(enrollDate);
				table.append(bodyTr);
			})
			$("#ajaxContainer").html(table);
		});
	}

	const basic2=()=>{
		$.get("${pageContext.request.contextPath}/ajax/basic2",data=>{
			console.log(data);
			$("#ajaxContainer").html(data);
		});
	} 

	const basicAjax=()=>{
		$.get('${pageContext.request.contextPath}/ajax/basicTest.do',
				(data)=>{
				console.log(data);
				$("#ajaxContainer").html("<h2>"+data+"</h2>");
			});
		}
	const convertAjax=()=>{
		$.get("${pageContext.request.contextPath}/ajax/converter",data=>{
			console.log(data);
		});
	}
	</script>
	
	<h1>JAP테스트</h1>
	<h3>
		<a href="${pageContext.request.contextPath }/jpa/basicTest.do">
		기본 EntityManager이용하기
		</a>
	</h3>

	<h3>
		<a href="${pageContext.request.contextPath }/jpa/manyToOne.do">
		manyToOne단방향
		</a>
	</h3>
	<h3>
		<a href="${pageContext.request.contextPath }/jpa/onetoone.do">
			onetoone단방향관계 저장
		</a>
	</h3>
	<h3>
		<a href="${pageContext.request.contextPath }/jpa/entitydelete.do?no=1">
			student삭제하기
		</a>
	</h3>
	<h3>entity수정하기</h3>
	<form action="${pageContext.request.contextPath }/jpa/updatestudent.do" method="post">
		학생번호 <input type="text" name="no"><br>
		학생이름 <input type="text" name="name"><br>
		학생학년 <input type="number" name="grade"><br>
		<input type="submit" value="변경">
	</form>
	
	<h3>
		<a href="${pageContext.request.contextPath }/jpa/insertClub.do">다대다테스트</a>
	</h3>
	
		<h2>web계정 테이블 조회하기</h2>
	<h3>
		<button onclick="getMembers();">전체회원가져오기</button>
	</h3>
	<h3>
		<input type="text" id="name"><button onclick="searchMemberName();">검색</button>
	</h3>
<script>
	function searchMemberName(){
		const name=$("#name").val();
		$.get("${pageContext.request.contextPath}/web/members/"+name,data=>{
			console.log(data);
		})
	}
	function getMembers(){
		$.get("${pageContext.request.contextPath}/web/members",data=>{
			console.log(data);
		});
	}
	
</script>
</section>

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/views/common/header.jsp" %>

		<section id="content">
			<h2 align="center" style="margin-top=200px">
				안녕하세요. MVC입니다.
			</h2>
		</section>
		<button id="memberAll">전체회원조회</button>
		<input type="text" id="Id"><button id="memberSearchById">아이디조회</button>
		
		<div id="memberList"></div>
		
		<script>
			$("#memberAll").click(e=>{
				$.get("<%=request.getContextPath()%>/memberList.do", /* com.web.controller - AjaxUserListServlet */
						data=>{
							const table=$("<table>");
							const header=$("<tr>");
							const headerdata=["아이디","이름","나이","성별","이메일","전화번호","주소","취미","가입일"];
							headerdata.forEach(e=>{
								const th=$("<th>").text(e);
								header.append(th);
							});
							table.append(header);
							data.forEach(e=>{
								const tr=$("<tr>");
								const td1=$("<td>").text(e.userId);
								const td2=$("<td>").text(e.userName);
								const td3=$("<td>").text(e.age);
								const td4=$("<td>").text(e.gender);
								const td5=$("<td>").text(e.email);
								const td6=$("<td>").text(e.phone);
								const td7=$("<td>").text(e.address);
								const td8=$("<td>").text(e.hobby);
								const td9=$("<td>").text(e.enrollDate);
								tr.append(td1).append(td2).append(td3).append(td4).append(td5).append(td6).append(td7).append(td8).append(td9);
								table.append(tr);
							});
							table.css("border","1px solid black");
							table.css("border-color","gray");
							$("#memberList").html(table);
						});
			});
			
			$("#memberSearchById").click(e=>{
				$.ajax({
					url:"<%=request.getContextPath()%>/memberListById.do", /* com.web.controller - AjaxMemberListById */
					/* dataType:"json", */
					data:{id:"$('#id').val()"},
					success:(data)=>{
						const table=$("<table>");
						const header=$("<tr>");
						const headerdata=["아이디","이름","나이","성별","이메일","전화번호","주소","취미","가입일"];
						headerdata.forEach(e=>{
							const th=$("<th>").text(e);
							header.append(th);
						});
						table.append(header);
						data.forEach(e=>{
							const tr=$("<tr>");
							const td1=$("<td>").text(e.userId);
							const td2=$("<td>").text(e.userName);
							const td3=$("<td>").text(e.age);
							const td4=$("<td>").text(e.gender);
							const td5=$("<td>").text(e.email);
							const td6=$("<td>").text(e.phone);
							const td7=$("<td>").text(e.address);
							const td8=$("<td>").text(e.hobby);
							const td9=$("<td>").text(e.enrollDate);
							tr.append(td1).append(td2).append(td3).append(td4).append(td5).append(td6).append(td7).append(td8).append(td9);
							table.append(tr);
						});
						table.css("border","1px solid black");
						table.css("border-color","gray");
						$("#memberList").html(table);	
					},
					error:(r,m)=>{
						console.log(r);
						console.log(m);
						if(e.status==404) alert("요청한 페이지가 없습니다");
					}
				});
				
			}); 
		</script>
<%@ include file="/views/common/footer.jsp" %>		
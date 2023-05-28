<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="views/error/500error.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 첫 jsp페이지</title>
</head>
<body>

	<h2>나의 첫 jsp페이지</h2>
	<h3>jsp가 제공하는 태그에 대해 알아보자</h3>
	
	<ul>
		<li>
			지시자 	: <%-- <%@ 태그명 속성설정(속성명="속성값") %> --%> <br>
			page 	: 페이지에 대한 설정을 하는 태그, contenttype, import정보, 언어정보 등을 설정 <br>
			include : 페이지(jsp파일) 내에 다른 페이지를 불러올 때 사용하는 태그 <br>
			taglib 	: jsp에 적용할 외부라이브러리 등록(JSTL, springform) *세미프로젝트 마친 뒤 개념 알려주신다고함! <br>
		</li>
		<li>
			선언문 : <%-- <%! 자바코드 %> --%> <br>
			자바클래스 구현부에 작성하는 코드 클래스 중괄호 부분에 들어갈 코드를 작성할 때 사용 <br>
			ex.메소드선언, 멤버변수 선언에 사용(*거의 사용안함!) <br>
			*조건문, 반복문 등은 사용이 불가능함 <br>
		</li>
		<li>
			스크립트릿 : <%-- <% 자바코드 %> --%> <br>
			자바의 메소드 내부에 들어갈 코드 작성할 때 사용 <br>
			(*많이 사용함) <br>
			지역변수, 반복문, 조건문 사용 <br>
		</li>
		<li>
			표현식 : <%-- <%= 출력할 문구||변수명||메소드호출(~리턴값) %> --%> <br>
			html 화면에 변수나, 메소드 실행결과를 출력할 때 사용 <br>
		</li>
	</ul>
	
	<h3>선언문 활용하기</h3>
	<%!
		//멤버변수, 멤버메소드를 선언할 때 사용
		String testData;
		static String testData2;
		public int age=19;
		
		public String getMsg(){
			return "안녕하세요";
		}
		
		//*조건문, 반복문 등은 사용이 불가능함
		/* if(test.equals("test")){
		
		} */
		/* for(int i=0; i<0;i++){
			
		} */

	%>
	<h3>선언문에서 작성한 내용 이용하기</h3>
	<ol>
		<li>testData : <%= testData %> </li>
		<li>age : <%= age %> </li>
		<li>getMsg() : <%= getMsg() %> </li>
	</ol>
	
	<h3>스크립트릿 이용하기</h3>
	<%
		//자바코드를 작성하는 부분
		//_jspservice()메소드 내부에 작성됨.
		String msg="이제 곧 점심시간";
		/* double height=180.5; */
		/* Illegal modifier for the variable height; only final is permitted */
	
				
		int rndNum=(int)(Math.random()*10+1);
		if(rndNum>3){
			out.print("3보다 크다!");
		}
		for(int i=0;i<10;i++){
			out.println("출력"+i+"<br>");
		}
	%>

	<h3><%= msg %></h3>
	<h3>랜덤수 : <%=rndNum %></h3>
	<%
		String[] names={"유병승","최주영","이은지","김현영","허성현","김찬은"};
		
	%>
	<ul>
		<%for(String name:names){
			if(!name.equals("최주영")) { %>
				<li><%=name %></li>
			<% }
		} %>
		<!-- ㄴ 잘 생각해보자 -->
	</ul>
	<%if(msg.contains("점심")){ %>
	<h1>점심 맛있게 드세요</h1>
	<%} %>
	
	<% 	for(String name:names){out.println(name+"<br>");} %>
	<!-- ㄴ 되기는 하는군!  -->
	
    <form action="" >
        <ul>
            <li>취미 선택<br>
            <%String[] hobby={"코딩","독서","게임","등산","취침"};%> 
           		<%for(String h:hobby){ %>
                	<label><input type="checkbox" name="hobbys" value="<%=h %>"
                	 <%= h.equals("코딩")?"checked":""%>><%=h %></label><br>
                <% } %>
            </li>
        </ul>
    </form>
	
	<h3>jsp내장객체에 대해 알아보자</h3>
	<p>
		서블릿에서 데이터를 저장하거나 정보를 가져왔던 객체를 지역변수로 가지고 있음<br>
		HttpServletRequest  : request
		HttpServletResponse : response
		HttpSession         : session
		ServletContext		: application
		Cookie				: request.getCookies();
		Header				: request.getHeader()
		PrintWriter			: out
	</p>
	
	<h3>request : <%=request %></h3>
	<h3>response : <%=response %></h3>
	<h3>session : <%=session %></h3>
	<h3>ServletContext : <%=application %></h3>
	<h3>jspOut : <%=out %></h3>

	<h3>contextRoot는 <%=request.getContextPath() %></h3>
	<h3>요청주소 : <%=request.getRequestURI() %></h3>
	<h3>요청주소 : <%=request.getRequestURL() %></h3>
	
	<h3>내장객체에 저장된 데이터 활용하기</h3>
	<h4>
		<a href="<%=request.getContextPath()%>/views/datasave.jsp">데이터저장(*views/datasave.jsp)</a>
	</h4>
	
	<h3>지시자태그 이용하기</h3>
	<h4>include태그 이용하기</h4>
	<p>
		include태그는 다른 jsp내용을 합쳐서 출력하는 것 <br>
		공통페이지를 반영할 때 사용(header, footer, aside) <br>
	</p>
	<h3>
		<a href="<%=request.getContextPath() %>/views/main.jsp">
			메인화면(*views/main.jsp)
		</a>
	</h3>	
	
	<h4>page태그 속성 알아보기</h4>
	<p>
		import		: 외부패키지에 있는 클래스를 이용할 때 import해줘야 한다. ""안에 작성, 여러 클래스를 호출할 때 ,로 구분한다. <br> 
		errorPage	: 페이지에서 error가 발생했을 때(500_서버 측 에러) 연결될 페이지를 지정할 때 사용 <br>
		isErrorPage : 에러를 출력하는 페이지에 설정, 설정하면 exception객체를 이용할 수 있음 <br>
		session 	: 세션객체를 자동 생성할지 생성하지 않을지 결정
	</p>
	<h3>에러페이지설정하기</h3>
	<h3>
		<a href="<%=request.getContextPath() %>/views/errortest.jsp">에러발생 페이지(*views/errortest.jsp)</a>
	</h3>
	
	<h3>
		<a href="<%=request.getContextPath() %>/errorServlet.do">서블릿 에러(*errorServlet.do)</a>
	</h3>
	
	<h3>
		<a href="<%=request.getContextPath() %>/memberAll.do">회원정보조회(*memberAll.do)</a>
	</h3>
	
	
	
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp" %>
<%@ page import="java.util.List,com.btc.rooms.model.vo.*" %>
<%
	List<Room> oceans=(List<Room>)request.getAttribute("ocean");
	List<Room> sunsets=(List<Room>)request.getAttribute("sunset");
	List<Room> spas=(List<Room>)request.getAttribute("spa");
	List<Room> allRooms=(List)request.getAttribute("rooms");
	List<RoomImage> images=(List)request.getAttribute("images");
	
%>
<section>
<div class="category-image">
    <img src="<%=request.getContextPath() %>/images/rooms.png" width="100%" height="200px">
    <div>ROOMS</div>
</div>
    <br><br><br><br>
    <div class="text-center container">
        <!-- 객실타입 + 카드형식으로 이미지와 객실이름, 간단한 소개 -->
         <!-- 전체 객실 한번에 보기 -->
         
         
        <div class="row d-flex justify-content-evenly"> 
        	<%if(allRooms.isEmpty()){ %>
        		<h2>rooms불러오기 실패</h2>
         	<% }else{
         		for (Room r : allRooms) {%>
                    <div class="card col-md-6 b" style="width: 20rem;">
                        <a href="<%= request.getContextPath() %>/RoomViewServlet.do?no=<%= r.getRoomNo() %>" class="text-decoration-none">
                          <%--   <img src="<%= request.getContextPath() %>/upload/rooms/<%=images %>" class="card-img-top" alt="..."> --%>
                            <%for(int i=0;i<images.size();i++){
                            	if(r.getRoomNo()==images.get(i).getRoomNo()){%>
                            	<img src="<%= request.getContextPath() %>/upload/rooms/<%=images.get(i).getSaveFilename() %>" class="card-img-top" alt="...">
							
                            <%} 
                            }%>
                   
                            <div class="card-body">
                                <h5 class="card-title"><%= r.getRoomName() %></h5>
                                <p class="card-text">MIN CAP <%= r.getRoomCap() %>PPL/MAX CAP <%= r.getRoomMaxCap() %>PPL</p>
                            </div>
                        </a> 
                    </div>
                    
                 <%} 
         	
           	 }%>
           	<br><br><br>
        </div>
      
         

    	<br><br><br><br>
</section>
<%@ include file="/views/common/footer.jsp" %>

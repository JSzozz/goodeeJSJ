package com.websocket.controller;

import javax.websocket.EndpointConfig;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/chatting")
public class ChattingServer {
	
	@OnOpen
	public void open(Session session, EndpointConfig config) {
		//클라이언트가 접속 요청하면 실행되는 메소드
		System.out.println(session.getId());
		System.out.println("서버접속");
	}
		
}

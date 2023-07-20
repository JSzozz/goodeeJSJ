package com.bs.spring.common.websocket;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;


@Slf4j 
public class ChattingServer extends TextWebSocketHandler{
	//key userId, session은 전달한 값을 저장
	private Map<String, WebSocketSession> clients=new HashMap();
	private ObjectMapper mapper;

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		log.info("채팅서버 입장");
		log.info(session.getId()+" "+session.getRemoteAddress());
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		log.info("{}",message);
		log.info(message.getPayload());//클라이언트가 보낸 데이터
		ChattingMessage msg=mapper.readValue(message.getPayload(),ChattingMessage.class);
		System.out.println(msg);

	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
	
	
	}
}

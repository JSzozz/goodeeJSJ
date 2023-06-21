package com.btc.rooms.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Room {
	private int roomNo;
	private String roomName;
	private int roomPrice;
	private int roomSize;
	private int roomCap;
	private int roomMaxCap;
	private char bookable;
	private String roomImage;
	private Date dateCreated;
	private Date dateModified;
	private String roomDescription;
}
/*
 * ROOM_NO NUMBER, ROOM_NAME VARCHAR2(50), ROOM_PRICE NUMBER, ROOM_SIZE
 * VARCHAR2(10), ROOM_CAP NUMBER, ROOM_MAX_CAP NUMBER, BOOKABLE VARCHAR2(4),
 * ROOM_IMAGE VARCHAR(255), DATE_CREATED DATE DEFAULT SYSDATE, DATE_MODIFIED
 * DATE DEFAULT SYSDATE, ROOM_DESCRIPTION VARCHAR2(500)
 */
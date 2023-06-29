package com.btc.rooms.model.service;

import java.sql.Connection;
import java.util.List;

import com.btc.rooms.model.dao.RoomDao;
import com.btc.rooms.model.vo.Room;
import com.btc.rooms.model.vo.RoomImage;

import static com.btc.common.JDBCTemplate.*;

public class RoomService {
	private RoomDao dao=new RoomDao();
	
	/*
	 * public List<Room> selectRoomByType(String type){ Connection
	 * conn=getConnection(); List<Room>list=dao.selectRoomByType(conn, type);
	 * close(conn); return list; }
	 */

	public List<Room> selectAllRoom() {
		Connection conn=getConnection();
		List<Room> list=dao.selectAllRoom(conn);
		close(conn);
		return list;
	}

	public Room viewRoom(int roomNo) {
		Connection conn=getConnection();
		Room r=dao.viewRoom(conn,roomNo);
		close(conn);
		return r;
	}

	public List<RoomImage> selectAllImages() {
		Connection conn=getConnection();
		List<RoomImage> images=dao.selectAllImages(conn);
		close(conn);
		return images;
	}

}

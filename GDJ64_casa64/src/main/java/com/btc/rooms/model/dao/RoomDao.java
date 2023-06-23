package com.btc.rooms.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.btc.rooms.model.vo.Room;
import static com.btc.common.JDBCTemplate.*;

public class RoomDao {
	private Properties sql=new Properties();
	
	public RoomDao() {
		String path=RoomDao.class.getResource("/sql/room/sql_room.properties").getPath();
		try {
			sql.load(new FileReader(path));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	private Room getRoom(ResultSet rs) throws SQLException{
		return Room.builder().roomNo(rs.getInt("room_no")).roomName(rs.getString("room_name"))
				.roomPrice(rs.getInt("room_price")).roomSize(rs.getInt("room_size"))
				.roomCap(rs.getInt("room_cap")).roomMaxCap(rs.getInt("room_max_cap"))
				.bookable(rs.getString("bookable").charAt(0)).roomImage(rs.getString("room_image"))
				.dateCreated(rs.getDate("date_created")).dateModified(rs.getDate("date_modified"))
				.roomDescription(rs.getString("room_description")).build();
	}

	public List<Room> selectAllRoom(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Room> list=new ArrayList();
		try {
			String query=sql.getProperty("selectAllRoom");
			pstmt=conn.prepareStatement(query);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				list.add(getRoom(rs));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			close(rs);
			close(conn);
		}
		return list;
	}
	public Room viewRoom(Connection conn, int roomNo) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Room r=null;
		try {
			String query=sql.getProperty("selectAllRoom");
			pstmt=conn.prepareStatement(query);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				r=getRoom(rs);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			close(rs);
			close(conn);
		}
		return r;
	}
	
}

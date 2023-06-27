package com.btc.admin.model.dao;

import static com.btc.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.btc.rooms.model.dao.RoomDao;
import com.btc.rooms.model.vo.Room;

public class AdminRoomDao {
	private Properties sql=new Properties();
	public AdminRoomDao() {
		String path=AdminRoomDao.class.getResource("/sql/admin/sql_adminRoom.properties").getPath();
		try {
			sql.load(new FileReader(path));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public int selectRoomCount(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectRoomCount"));
			rs=pstmt.executeQuery();
			if(rs.next()) result=rs.getInt(1);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return result;
	}
	public List<Room> selectAllExistingRoom(Connection conn, int cPage, int numPerPage) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Room> list=new ArrayList<Room>();
		/* Room m=null; */
		try {
			String query=sql.getProperty("selectAllExistingRoom");
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, (cPage-1)*numPerPage+1);
			pstmt.setInt(2, cPage*numPerPage);
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
	private Room getRoom(ResultSet rs) throws SQLException{
		return Room.builder().roomNo(rs.getInt("room_no")).roomName(rs.getString("room_name"))
				.roomPrice(rs.getInt("room_price")).roomSize(rs.getInt("room_size"))
				.roomCap(rs.getInt("room_cap")).roomMaxCap(rs.getInt("room_max_cap"))
				.bookable(rs.getString("bookable").charAt(0)).roomImage(rs.getString("room_image"))
				.dateCreated(rs.getDate("date_created")).dateModified(rs.getDate("date_modified"))
				.roomDescription(rs.getString("room_description")).build();
	}
	public Room viewRoom(Connection conn, int roomNo) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Room r=null;
		try {
			String query=sql.getProperty("showSelectedRoom");
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, roomNo);
			rs=pstmt.executeQuery();
			if(rs.next()) r=getRoom(rs);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			close(rs);
			close(conn);
		}
		return r;
	}
	public int updateRoom(Connection conn, Room r) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("updateRoom"));
			pstmt.setString(1, r.getRoomName());
			pstmt.setInt(2, r.getRoomPrice());
			pstmt.setInt(3,r.getRoomSize());
			pstmt.setInt(4, r.getRoomCap());
			pstmt.setInt(5, r.getRoomMaxCap());
			pstmt.setString(6, String.valueOf(r.getBookable()));
			pstmt.setString(7,String.join(",", r.getRoomImage()));
			pstmt.setString(8, r.getRoomDescription());
			pstmt.setInt(9,r.getRoomNo());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}

}

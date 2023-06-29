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
import com.btc.rooms.model.vo.OptionFree;
import com.btc.rooms.model.vo.OptionXtra;
import com.btc.rooms.model.vo.Room;
import com.btc.rooms.model.vo.RoomImage;
import com.btc.rooms.model.vo.RoomOption;

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
			close(pstmt);
		}
		return list;
	}
	private Room getRoom(ResultSet rs) throws SQLException{
		return Room.builder()
				.roomNo(rs.getInt("room_no"))
				.roomName(rs.getString("room_name"))
				.roomPrice(rs.getInt("room_price"))
				.roomSize(rs.getInt("room_size"))
				.roomCap(rs.getInt("room_cap"))
				.roomMaxCap(rs.getInt("room_max_cap"))
				.bookable(rs.getString("bookable").charAt(0))
				.dateCreated(rs.getDate("date_created"))
				.dateModified(rs.getDate("date_modified"))
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
			close(pstmt);
		}
		return r;
	}
	public int updateRoom(Connection conn, Room r) {
		PreparedStatement pstmt=null;
		int result=0;
		String query=sql.getProperty("updateRoom");
		try {
			pstmt=conn.prepareStatement(sql.getProperty("updateRoom"));
			pstmt.setString(1, r.getRoomName());
			pstmt.setInt(2, r.getRoomPrice());
			pstmt.setInt(3,r.getRoomSize());
			pstmt.setInt(4, r.getRoomCap());
			pstmt.setInt(5, r.getRoomMaxCap());
			pstmt.setString(6, String.valueOf(r.getBookable()));
			pstmt.setString(7, r.getRoomDescription());
			pstmt.setInt(8,r.getRoomNo());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;

	}
	public int deleteRoom(Connection conn, int roomNo) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("deleteRoom"));
			pstmt.setInt(1, roomNo);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}
	public List<OptionFree> selectAllFree(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<OptionFree> list=new ArrayList();
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectAllFree"));
			rs=pstmt.executeQuery();
			while(rs.next()) {
				list.add(getFree(rs));
			}
			System.out.println(list);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return list;
	}
	private OptionFree getFree(ResultSet rs) throws SQLException{
		return OptionFree.builder().freeNo(rs.getInt("free_no")).freeName(rs.getString("free_name")).build();
	}
	
	public List<OptionXtra> selectAllXtra(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<OptionXtra> list=new ArrayList();
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectAllXtra"));
			rs=pstmt.executeQuery();
			while(rs.next()) {
				list.add(getXtra(rs));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return list;
	}
	private OptionXtra getXtra(ResultSet rs) throws SQLException{
		return OptionXtra.builder().xtraNo(rs.getInt("xtra_no")).xtraName(rs.getString("xtra_name"))
				.xtraPrice(rs.getInt("xtra_price")).xtraExplanation(rs.getString("xtra_explanation")).build();
	}
	
	
	public List<RoomOption> selectCheckedOption(Connection conn, int roomNo) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<RoomOption> options=new ArrayList();
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectCheckedOption"));
			pstmt.setInt(1, roomNo);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				options.add(getChecked(rs));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return options;
	}
	private RoomOption getChecked(ResultSet rs) throws SQLException{
		return RoomOption.builder().freeNo(rs.getInt("free_no")).roomNo(rs.getInt("room_no")).optionName(rs.getString("free_name")).build();
	}
	
	
	public List<Room> selectRoomByKeyword(Connection conn, String keyword, int cPage, int numPerpage) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String query=sql.getProperty("selectRoomByKeyword");
		List<Room> rooms=new ArrayList();
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setInt(2, (cPage-1)*numPerpage+1);
			pstmt.setInt(3, cPage*numPerpage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				rooms.add(getRoom(rs));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return rooms;
	}
	public int selectRoomByKeywordCount(Connection conn, String keyword) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String query=sql.getProperty("selectRoomByKeywordCount");
		int count=0;
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, "%"+keyword+"%");
			rs=pstmt.executeQuery();
			if(rs.next()) count=rs.getInt(1);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return count;
	}

	public int insertInquiry(Connection conn, Room r) {
		
		return 0;
		
	}

	public int insertUpfiles(Connection conn, RoomImage roomimage) {
		PreparedStatement pstmt=null;
		int result=0;
		String query=sql.getProperty("insertRoomImage");
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, roomimage.getRoomNo());
			pstmt.setString(2, roomimage.getSaveFilename());
			pstmt.setString(3, roomimage.getOriFilename());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}
	public int deleteOldOption(Connection conn,int roomNo) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("deleteOldOption"));
			pstmt.setInt(1, roomNo);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}

	public int updateRoomOption(Connection conn, int roomNo, String[] frees) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			for(int i=0;i<frees.length;i++) {
				pstmt=conn.prepareStatement(sql.getProperty("updateRoomOption"));
				pstmt.setInt(1, Integer.parseInt(frees[i]));
				pstmt.setInt(2, roomNo);
				result+=pstmt.executeUpdate();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}

	public int insertRoom(Connection conn, Room r) {
		PreparedStatement pstmt=null;
		int result=0;
		String query=sql.getProperty("addNewRoom");
		System.out.println(String.valueOf(r.getBookable()));
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1,r.getRoomName());
			pstmt.setInt(2,r.getRoomPrice());
			pstmt.setInt(3,r.getRoomSize());
			pstmt.setInt(4,r.getRoomCap());
			pstmt.setInt(5,r.getRoomMaxCap());
			pstmt.setString(6,String.valueOf(r.getBookable()));
	
			pstmt.setString(7,r.getRoomDescription());	
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}

	public int selectRoomNo(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String query=sql.getProperty("selectRoomNo");
		int count=0;
		try {
			pstmt=conn.prepareStatement(query);
			rs=pstmt.executeQuery();
			if(rs.next()) count=rs.getInt(1);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return count;
	}

	public int deleteOldRoomImage(Connection conn, Room r) {
		PreparedStatement pstmt=null;
		int result=0;
		try {
			pstmt=conn.prepareStatement("DELETE FROM ROOM_IMAGE WHERE ROOM_NO=?");
//			sql.getProperty("deleteOldRoomImage")
			pstmt.setInt(1,r.getRoomNo());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
		
	}

	public RoomImage selectRoomImage(Connection conn, int roomNo) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		RoomImage result=new RoomImage();
		try {
			pstmt=conn.prepareStatement("SELECT * FROM ROOM_IMAGE WHERE ROOM_NO=?");
			pstmt.setInt(1, roomNo);
			rs=pstmt.executeQuery();
			if(rs.next()) result=getRoomImage(rs);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return result;
	}
	private RoomImage getRoomImage(ResultSet rs) throws SQLException{
		return RoomImage.builder().roomNo(rs.getInt("room_no")).saveFilename(rs.getString("save_filename")).oriFilename(rs.getString("ori_filename")).fileNo(rs.getInt("file_no")).build();
	}

}


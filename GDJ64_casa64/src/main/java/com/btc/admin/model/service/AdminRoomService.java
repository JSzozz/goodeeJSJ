package com.btc.admin.model.service;

import static com.btc.common.JDBCTemplate.close;
import static com.btc.common.JDBCTemplate.commit;
import static com.btc.common.JDBCTemplate.getConnection;
import static com.btc.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.btc.admin.model.dao.AdminRoomDao;
import com.btc.rooms.model.vo.OptionFree;
import com.btc.rooms.model.vo.OptionXtra;
import com.btc.rooms.model.vo.Room;
import com.btc.rooms.model.vo.RoomOption;

public class AdminRoomService {
	private AdminRoomDao dao=new AdminRoomDao();
	public List<Room> selectAllExistingRooms(int cPage, int numPerPage) {
		Connection conn=getConnection();
		List<Room> list=dao.selectAllExistingRoom(conn,cPage,numPerPage);
		close(conn);
		return list;
	}
	public Room viewRoom(int roomNo) {
		Connection conn=getConnection();
		Room r=dao.viewRoom(conn,roomNo);
		close(conn);
		return r;
	}
	public int updateRoom(Room r) {
		Connection conn=getConnection();
		int result=dao.updateRoom(conn,r);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
		
	}
	public int selectRoomCount() {
		Connection conn=getConnection();
		int result=dao.selectRoomCount(conn);
		close(conn);
		return result;
	}
	public int deleteRoom(int roomNo) {
		Connection conn=getConnection();
		int result=dao.deleteRoom(conn,roomNo);
		close(conn);
		return result;
	}
	public List<OptionFree> selectAllFree() {
		Connection conn=getConnection();
		List<OptionFree> list=dao.selectAllFree(conn);
		close(conn);
		return list;
	}
	public List<OptionXtra> selectAllXtra() {
		Connection conn=getConnection();
		List<OptionXtra> list=dao.selectAllXtra(conn);
		close(conn);
		return list;
	}
	public List<RoomOption> selectCheckedOption(int roomNo) {
		Connection conn=getConnection();
		List<RoomOption> options = dao.selectCheckedOption(conn, roomNo);
		close(conn);
		return options;
		
	}

	public List<Room> selectRoomByKeyword(String keyword, int cPage, int numPerpage) {
		Connection conn=getConnection();
		List<Room> rooms=dao.selectRoomByKeyword(conn,keyword,cPage,numPerpage);
		close(conn);
		return rooms;
		
	}
	public int selectRoomByKeywordCount(String keyword) {
		Connection conn=getConnection();
		int count=dao.selectRoomByKeywordCount(conn,keyword);
		close(conn);
		return count;
	}
//	public int insertInquiry(String memberId, String boardType, String boardTitle, String boardContent, List<String> files) {
//	       Connection conn = getConnection();
//	       int result = dao.InsertInquiry(conn, memberId, boardType, boardTitle, boardContent); 파일없이 게시글만 등록하는
//	       
//	       if (result > 0) {
//	           int fileResult = 0;
//	           if (files != null && !files.isEmpty()) {
//	               for (int i = 0; i < files.size(); i++) {
//	                   fileResult = dao.Insertupfiles(conn, files.get(i));
//	                   if (fileResult <= 0) {
//	                       break; 
//	                   }
//	               }
//	           }
//	           
//	           if (fileResult > 0 || (files != null && files.isEmpty())) { 
//	               commit(conn);
//	           } else {
//	               rollback(conn);
//	           }
//	       } else {
//	           rollback(conn);
//	       }
//	       
//	       return result;
//	   }
	public int insertInquiry(Room r, List<String> files) {
		Connection conn=getConnection();
		int result=dao.insertInquiry(conn,r);
		if(result>0) {
			int fileResult=0;
			if(files!=null&&!files.isEmpty()) {
				for(int i=0;i<files.size();i++) {
					fileResult=dao.insetUpfiles(conn, files.get(i));
					if(fileResult<=0) {
						break;
					}
				}
			}
			if(fileResult>0||(files!=null&&files.isEmpty())) {
				commit(conn);
				
			}else {
				rollback(conn);
			}
		}rollback(conn);
		return result;
	}


}


package com.btc.admin.model.service;

import static com.btc.common.JDBCTemplate.close;
import static com.btc.common.JDBCTemplate.commit;
import static com.btc.common.JDBCTemplate.getConnection;
import static com.btc.common.JDBCTemplate.rollback;

import java.nio.file.Files;
import java.sql.Connection;
import java.util.List;

import org.apache.tomcat.jni.File;

import com.btc.admin.model.dao.AdminRoomDao;
import com.btc.rooms.model.vo.OptionFree;
import com.btc.rooms.model.vo.OptionXtra;
import com.btc.rooms.model.vo.Room;
import com.btc.rooms.model.vo.RoomImage;
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

	public int insertInquiry(Room r, List<RoomImage> files, String[] frees) {
		Connection conn=getConnection();
		int result=dao.insertRoom(conn, r);
		int roomNo=dao.selectRoomNo(conn);
		if(result>0) {
			if(files!=null&&!files.isEmpty()) {
				for(int i=0;i<files.size();i++) {
					files.get(i).setRoomNo(roomNo);
					result=dao.insertUpfiles(conn, files.get(i));
					if(result<=0) {
						rollback(conn);
						break;
					}
				}
				if(result>0) {
					result=dao.updateRoomOption(conn,r.getRoomNo(),frees);
					if(result>0) commit(conn);
					else rollback(conn);
				}else {
					rollback(conn);
				}
			}
		}
		return roomNo;
	}
	public int updateInquiry(Room r, List<RoomImage> filesName, String[] frees) {
		Connection conn=getConnection();
		int result=dao.updateRoom(conn, r);
		//이전 파일삭제하는 구문 작성 -> 방번호를 기준으로....
		int deleteImageResult=dao.deleteOldRoomImage(conn,r);
		if(deleteImageResult<0) {
			//예전 이미지 삭제 실패
			rollback(conn);
		}else {
			//예전 이미지 삭제 성공->새로운 내용 등록
			if(result>0) {
				if(filesName!=null&&!filesName.isEmpty()) {
					for(int i=0;i<filesName.size();i++) {
						result=dao.insertUpfiles(conn, filesName.get(i));
						if(result<=0) {
							rollback(conn);
							break;
						}
					}
					result=dao.deleteOldOption(conn, r.getRoomNo());
					if(result>0) {
						result=dao.updateRoomOption(conn,r.getRoomNo(),frees);
						if(result>0) commit(conn);
						else rollback(conn);
					}else {
						rollback(conn);
					}
				}
			}
		}
		return result;
		
	}
//	public int updateRoomOption(int roomNo, String[] frees) {
//		Connection conn=getConnection();
//		int result=dao.updateRoomOption(conn,roomNo,frees);
//		close(conn);
//		return result;
//		
//	}
//	public int deleteOldOption(int roomNo) {
//		Connection conn=getConnection();
//		int result=dao.deleteOldOption(conn, roomNo);
//		close(conn);
//		return result;
//	}


}


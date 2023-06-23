package com.btc.admin.model.service;

import static com.btc.common.JDBCTemplate.close;
import static com.btc.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.btc.admin.model.dao.AdminDao;
import com.btc.rooms.model.vo.Room;

public class AdminService {
	private AdminDao dao=new AdminDao();
	public List<Room> selectAllExistingRooms() {
		Connection conn=getConnection();
		List<Room> list=dao.selectAllExistingRoom(conn);
		close(conn);
		return list;
	}

}

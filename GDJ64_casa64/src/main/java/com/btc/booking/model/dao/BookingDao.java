package com.btc.booking.model.dao;

import static com.btc.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.btc.booking.model.vo.Booking;
import com.btc.booking.model.vo.OptionXtra;
import com.btc.booking.model.vo.SeasonalPrice;
import com.btc.member.model.dto.Member;
import com.btc.rooms.model.vo.Room;


public class BookingDao {
	
	private Properties sql=new Properties();
	
	public BookingDao() {
		String path=BookingDao.class
				.getResource("/sql/reserve/sql_reserve.properties")
				.getPath();
		try {
			sql.load(new FileReader(path));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
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
				.roomImage(rs.getString("room_image"))
				.dateCreated(rs.getDate("date_created"))
				.dateModified(rs.getDate("date_modified"))
				.roomDescription(rs.getString("room_description"))
				.build();
	}
	
	private Booking getBooking(ResultSet rs) throws SQLException {
		return Booking.builder()
				.bookingNo(rs.getInt("BOOKING_NO"))
				.member(Member.builder().memberNo(rs.getInt("MEMBER_NO")).build())
				.room(Room.builder().roomNo(rs.getInt("ROOM_NO")).build())
				.checkIn(rs.getDate("CHECK_IN"))
				.checkOut(rs.getDate("CHECK_OUT"))
				.guestAdult(rs.getInt("GUEST_ADULT"))
				.guestChild(rs.getInt("GUEST_CHILD"))
				.guestInfant(rs.getInt("GUEST_INFANT"))
				.bookingPrice(rs.getInt("BOOKING_PRICE"))
				.bookingComment(rs.getString("BOOKING_COMMENT"))
				.bookingState(rs.getString("BOOKING_STATE"))
				.paymentDate(rs.getDate("PAYMENT_DATE"))
				.build();
	}
	private Booking getBookingPart(ResultSet rs) throws SQLException {
		return Booking.builder()
				.room(Room.builder().roomNo(rs.getInt("ROOM_NO")).build())
				.checkIn(rs.getDate("CHECK_IN"))
				.checkOut(rs.getDate("CHECK_OUT"))
				.build();
	}
	
	private SeasonalPrice getSeasonalPrice(ResultSet rs) throws SQLException {
		return SeasonalPrice.builder()
				.season(rs.getString("SEASON"))
				.startDate(rs.getDate("START_DATE"))
				.endDate(rs.getDate("END_DATE"))
				.weekdayRate(rs.getFloat("WEEKDAY_RATE"))
				.weekendRate(rs.getFloat("WEEKEND_RATE"))
				.build();
	}	
	
	private OptionXtra getOptionXtra(ResultSet rs) throws SQLException {
		return OptionXtra.builder()
				.xtraNo(rs.getInt("XTRA_NO"))
				.xtraName(rs.getString("XTRA_NAME"))
				.xtraPrice(rs.getInt("XTRA_PRICE"))
				.xtraExplanation(rs.getString("XTRA_EXPLANATION"))
				.build();
	}	
	
	private Room getRoomPart(ResultSet rs) throws SQLException{
		return Room.builder()
				.roomNo(rs.getInt("room_no"))
				.roomName(rs.getString("room_name"))
				.build();
	}
	
	public List<Room> selectFilteringRoom(Connection conn, List<String> optionList){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Room> list=new ArrayList();
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectFilteringRoom1"));
			//SELECT * FROM ROOM
			pstmt.setString(1, optionList.get(0));
			rs=pstmt.executeQuery();
			while(rs.next()) {
				list.add(getRoomPart(rs));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return list;	
	}

	public List<OptionXtra> selectAllOption(Connection conn){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<OptionXtra> list=new ArrayList();
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectAllOption"));
			//SELECT * FROM ROOM
			rs=pstmt.executeQuery();
			while(rs.next()) {
				list.add(getOptionXtra(rs));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return list;	
	}
	
	
	public List<SeasonalPrice> selectAllSeason(Connection conn){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<SeasonalPrice> list=new ArrayList();
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectAllSeason"));
			//SELECT * FROM ROOM
			rs=pstmt.executeQuery();
			while(rs.next()) {
				list.add(getSeasonalPrice(rs));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return list;	
	}
	
	public List<Room> selectAllRoom(Connection conn){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Room> list=new ArrayList();
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectAllRoom"));
			//SELECT * FROM SEASONAL_PRICE
			rs=pstmt.executeQuery();
			while(rs.next()) {
				list.add(getRoom(rs));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return list;	
	}
	
	public List<Booking> selectAllBooking(Connection conn){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Booking> list=new ArrayList();
		try {
			pstmt = conn.prepareStatement(sql.getProperty("selectAllBooking"));
			//SELECT ROOM_NO, CHECK_IN, CHECK_OUT, BOOKING_STATE FROM BOOKING WHERE BOOKING_STATE='결제완료' OR BOOKING_STATE='취소요청' ORDER BY CHECK_IN
			rs=pstmt.executeQuery();
			while(rs.next()) {
				list.add(getBookingPart(rs));
			};
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return list;
	}
	
	public int searchRoomNo(Connection conn, String roomNm) {
		PreparedStatement pstmt= null;
		ResultSet rs=null;
		int roomNo=0;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("searchRoomNo"));
			//SELECT ROOM_NO FROM ROOM WHERE ROOM_NAME = ?
			pstmt.setString(1, roomNm);
			rs=pstmt.executeQuery();
			if(rs.next()) roomNo=rs.getInt(1);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return roomNo;
	}
	
	public int insertBooking(Connection conn, Booking b	) {
	PreparedStatement pstmt=null;
	int result=0;
	String bookingState="결제완료";
	try{
		pstmt=conn.prepareStatement(sql.getProperty("insertBooking"));
//INSERT INTO BOOKING VALUES(SEQ_BOOKING_NO.NEXTVAL,?,?,?,?,?,?,?,?,?,?,DEFAULT)
		pstmt.setInt(1, b.getMember().getMemberNo());
		pstmt.setInt(2, b.getRoom().getRoomNo());
		pstmt.setDate(3, b.getCheckIn());
		pstmt.setDate(4, b.getCheckOut());
		pstmt.setInt(5, b.getGuestAdult());
		pstmt.setInt(6, b.getGuestChild());
		pstmt.setInt(7, b.getGuestInfant());
		pstmt.setInt(8, b.getBookingPrice());
		pstmt.setString(9, b.getBookingComment());
		pstmt.setString(10,bookingState);
		result=pstmt.executeUpdate();
		
	}catch(SQLException e) {
		e.printStackTrace();
	}finally {
		close(pstmt);
	}return result;
	}
	
//	private Notice getNotice(ResultSet rs) throws SQLException{
//		return Notice.builder()
//				.noticeNo(rs.getInt("notice_no"))
//				.noticeTitle(rs.getString("notice_title"))
//				.noticeWriter(rs.getString("notice_writer"))
//				.noticeContent(rs.getString("notice_content"))
//				.noticeDate(rs.getDate("notice_date"))
//				.filePath(rs.getString("filepath"))
//				.build();
//	}
//
//	public List<Notice> selectNotice(Connection conn, int cPage, int numPerpage){
//		PreparedStatement pstmt=null;
//		ResultSet rs=null;
//		List<Notice> list=new ArrayList();
//		try {
//			pstmt=conn.prepareStatement(sql.getProperty("selectNotice"));
//			//SELECT * FROM (SELECT ROWNUM AS RNUM, N.* FROM(SELECT * FROM NOTICE ORDER BY NOTICE_DATE DESC)N) WHERE RNUM BETWEEN ? AND ?
//			pstmt.setInt(1, (cPage-1)*numPerpage+1);
//			pstmt.setInt(2, cPage*numPerpage);
//			rs=pstmt.executeQuery();
//			while(rs.next()) {
//				list.add(getNotice(rs));
//			}
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}finally {
//			close(rs);
//			close(pstmt);
//		}return list;	
//	}
//	
//	public int selectNoticeCount(Connection conn) {
//		PreparedStatement pstmt=null;
//		ResultSet rs=null;
//		int result=0;
//		try {
//			pstmt=conn.prepareStatement(sql.getProperty("selectNoticeCount"));
//			rs=pstmt.executeQuery();
//			if(rs.next()) result=rs.getInt("RN");
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}finally {
//			close(rs);
//			close(pstmt);
//		}return result;
//	}
//	
//	public int insertNotice(Connection conn, Notice n) {
//		PreparedStatement pstmt=null;
//		int result=0;
//		try{
//			pstmt=conn.prepareStatement(sql.getProperty("insertNotice"));//INSERT INTO NOTICE VALUES(SEQ_NOTICE_NO.NEXTVAL,?,?,?,DEFAULT,?,DEFAULT)
//			pstmt.setString(1, n.getNoticeTitle());
//			pstmt.setString(2, n.getNoticeWriter());
//			pstmt.setString(3, n.getNoticeContent());
//			pstmt.setString(4, n.getFilePath());
//			result=pstmt.executeUpdate();
//			
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}finally {
//			close(pstmt);
//		}return result;
//	}
//	
//	public Notice selectNoticeByNo(Connection conn, int no) {
//		PreparedStatement pstmt=null;
//		ResultSet rs=null;
//		Notice n= null;
//		try {
//			pstmt=conn.prepareStatement(sql.getProperty("selectNoticeByNo"));//SELECT * FROM NOTICE WHERE NOTICE_NO=?
//			/* pstmt.setInt(1, n.getNoticeNo()); */
//			pstmt.setInt(1, no);
//			rs=pstmt.executeQuery();
//			if(rs.next()) {
//				n=getNotice(rs);
//			}
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}finally {
//			close(rs);
//			close(pstmt);
//		}return n;
//	}
}

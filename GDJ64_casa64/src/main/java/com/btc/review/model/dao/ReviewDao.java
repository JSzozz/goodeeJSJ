package com.btc.review.model.dao;

import static com.btc.common.JDBCTemplate.close;
import static com.btc.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.btc.review.model.vo.Reviews;

public class ReviewDao {

   /**
    * 이용후기 전체 리스트
    * @param conn
    * @param type
    * @param keyword
    * @param roomNo
    * @return
    */
   public List<Reviews> selectReviews(Connection conn, String type, String keyword, String roomNo){
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      List<Reviews> list = new ArrayList();
      
      try {
         String sql = getBasicQuery(); // 실행할 기본 쿼리
         // 방 선택된 게 있을 경우
         if(roomNo != null && roomNo != "" ) {
            sql += " AND ROOM.NO = " + roomNo;
         }
         // 검색 타입과 검색어가 있을 경우
         if(type != null && keyword != "" && !type.equals("rooms")) {
            if(type.equals("title")) { // 검색 타입이 제목일 경우
               sql += " AND REVIEWS.TITLE ";
            } else if (type.equals("contents")) { // 검색 타입이 내용일 경우
               sql += " AND REVIEWS.CONTENTS ";
            } else if (type.equals("writer")) { // 검색 타입이 작성자 일 경우
               sql += " AND MEMBER.NICKNAME ";
            }
            sql += " LIKE '%" + keyword + "%' ";
         }
         sql += " ORDER BY REVIEWS.NO DESC";
         
         pstmt = conn.prepareStatement(sql); // 실제 쿼리 들어가고
         rs = pstmt.executeQuery(); // 쿼리 실행
         
         while(rs.next()) { // rs 다음 값이 있을 경우
            Reviews reviews = getReviews(rs);
            list.add(reviews); // 
         }
         
      }catch(SQLException e) {
         e.printStackTrace();
      }finally {
         close(rs);
         close(pstmt);
      }
      
      return list;
      
   }
   /**
    * 이용후기 뷰
    * @param conn
    * @param reviewNo
    * @return
    */
   public Reviews getReviewView(Connection conn, int reviewNo) {
      
      String sql = getBasicQuery();
      sql += "AND REVIEWS.NO = ?";
      
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      
      Reviews reviews = new Reviews();
      try {
         conn = getConnection();
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, reviewNo);
         rs = pstmt.executeQuery();
         
         while(rs.next()) {
            reviews = getReviews(rs);
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
        close(pstmt);
        close(rs);
      }
      
      return reviews;
   }
   /**
    * 이용후기 작성
    * @param rs
    * @return
    * @throws SQLException
    */
   public int insertReviews(Connection conn, Reviews reviews) {
      PreparedStatement pstmt = null;
      int result = 0; // 실패를 기본 값으로

      try {
         conn = getConnection(); // DB 접속
         // 3. 쿼리 작성
         String sql = "INSERT INTO REVIEWS (NO,TITLE,CONTENTS,VIEWS,IS_DELETED,ROOM_NO,MEMBER_NO,BOOKING_NO, DATE_CREATED, DATE_MODIFIED) " 
                  + "VALUES (REVIEWS_SEQ.NEXTVAL,?,?,?,?,?,?,?, TO_DATE(SYSDATE , 'yyyy/mm/dd hh24:mi:ss'), TO_DATE(SYSDATE , 'yyyy/mm/dd hh24:mi:ss'))"; // 실행할 쿼리
         
         pstmt = conn.prepareStatement(sql); // 실행 준비
//         4. 쿼리에 파라미터 셋팅
         pstmt.setString(1, reviews.getTitle());
         pstmt.setString(2, reviews.getContents());
         pstmt.setInt(3, reviews.getViews());
         pstmt.setInt(4, reviews.getIsDeleted());
         pstmt.setInt(5, reviews.getRoomNo());
         pstmt.setInt(6, reviews.getMemberNo());
         pstmt.setInt(7, reviews.getBookingNo());
         
         result = pstmt.executeUpdate(); // 쿼리 실행
         if(result > 0) {
            conn.commit();
         } else {
            conn.rollback();
         }
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } finally {
        close(pstmt);
      }
      return result;
   }
   
   /**
    * 마이페이지용 이용 후기 리스트
    * @param rs
    * @return
    * @throws SQLException
    */
   public List<Reviews> selectReviewsMypage(Connection conn, int memberNo) {
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      List<Reviews> list = new ArrayList();
   
      try {
         String sql = getBasicQuery(); // 실행할 기본 쿼리
         sql += " AND REVIEWS.MEMBER_NO = ? ";
//         sql += " ORDER BY REVIEWS.NO DESC";
         
         pstmt = conn.prepareStatement(sql); // 실제 쿼리 들어가고
         pstmt.setInt(1, memberNo);
         rs = pstmt.executeQuery(); // 쿼리 실행
         
         while(rs.next()) { // rs 다음 값이 있을 경우
            Reviews reviews = getReviews(rs);
            list.add(reviews); // 
         }
         
      }catch(SQLException e) {
         e.printStackTrace();
      }finally {
         close(rs);
         close(pstmt);
      }
      return list;
   }
   
   private Reviews getReviews(ResultSet rs) throws SQLException{
//      Reviews reviews = new Reviews();

      return Reviews.builder()
            .no(rs.getInt("NO"))
            .title(rs.getString("TITLE"))
            .contents(rs.getString("CONTENTS"))
            .views(rs.getInt("VIEWS"))
            .dateCreated(rs.getDate("DATE_CREATED"))
            .dateModified(rs.getDate("DATE_MODIFIED"))
            .dateDeleted(rs.getDate("DATE_DELETED"))
            .isDeleted(rs.getInt("IS_DELETED"))
            .roomName(rs.getString("ROOM_NAME"))
            .nickName(rs.getString("NICKNAME"))
            .roomNo(rs.getInt("ROOM_NO"))
            .memberNo(rs.getInt("MEMBER_NO"))
            .bookingNo(rs.getInt("BOOKING_NO"))
            .reply(rs.getString("REPLY"))
            .lastReplyDate(rs.getDate("LAST_REPLY_DATE"))
            .isReply(rs.getInt("IS_REPLY"))
            .build();
   }
   
   public String getBasicQuery() {
      return "SELECT "
         + "  REVIEWS.* , MEMBER.NICKNAME, ROOM.ROOM_NAME "
         + "    FROM REVIEWS  "
         + "    JOIN MEMBER ON MEMBER.NO = REVIEWS.MEMBER_NO "
         + "    JOIN ROOM ON ROOM.NO = REVIEWS.ROOM_NO " 
         + "    WHERE 1=1 AND REVIEWS.IS_DELETED = 0 "; // 실행할 기본 쿼리
   }
}
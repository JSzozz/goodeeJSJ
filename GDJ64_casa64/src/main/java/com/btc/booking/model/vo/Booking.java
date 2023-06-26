package com.btc.booking.model.vo;

import java.sql.Date;

import com.btc.member.model.dto.Member;
import com.btc.rooms.model.vo.Room;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Booking {
   
   private int bookingNo;
   private Member member;
   private Room room;
   private Date checkIn;
   private Date checkOut;
   private int guestAdult;
   private int guestChild;
   private int guestInfant;
   private int bookingPrice;
   private String bookingComment;
   private String bookingState;
   private Date paymentDate;
   
   @Override
   public String toString() {
	   return bookingNo + "," + member + "," + room + "," + checkIn + "," + checkOut + "," + guestAdult + ","
			   + guestChild + "," + guestInfant + "," + bookingPrice + "," + bookingComment + "," + bookingState + "," + paymentDate;
   }
}
package com.btc.mypage.model.vo;

import java.sql.Date;


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
   private int memberNo;
   private int roomNo;
   private Date checkIn;
   private Date checkOut;
   private int guestAdult;
   private int guestChild;
   private int guestInfant;
   private int bookingPrice;
   private String bookingComment;
   private String bookingState;
   private Date paymentDate;
   private String roomName;
   private int reviewNo;
   
   
}
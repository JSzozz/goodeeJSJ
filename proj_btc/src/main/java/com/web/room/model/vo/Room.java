package com.web.room.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Room {
   
   private int roomNo;
   private String roomName;
   private int roomPrice;
   private String roomSize;
   private int roomCap;
   private int roomMaxCap;
   private String bookable;
   private String roomImage;
   private Date dateCreated;
   private Date dateModified;
   private String roomDescription;
   
//   private int noticeNo;
//   private String noticeTitle;
//   private String noticeWriter;
//   private String noticeContent;
//   private Date noticeDate;
//   private String filePath;
//   private String status;
   
   
}
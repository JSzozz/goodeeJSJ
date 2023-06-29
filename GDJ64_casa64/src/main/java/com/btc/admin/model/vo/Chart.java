package com.btc.admin.model.vo;

import java.sql.Date;

import com.btc.booking.model.vo.Booking;
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
public class Chart {
	private String roomName;
	private int roomInfo;
}

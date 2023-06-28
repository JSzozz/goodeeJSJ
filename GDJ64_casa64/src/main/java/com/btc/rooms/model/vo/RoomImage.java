package com.btc.rooms.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomImage {
	private int roomNo;
	private String saveFilename;
	private String oriFilename;
	private int fileNo;
}

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
	private int fileNo;
	private int roomNo;
	private String roomRenamedFilename;
	private String originalFilename;
	
}

package com.btc.rooms.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomOption {
	private int roomNo;
	private int freeNo;
	private String optionName;
}

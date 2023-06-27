package com.btc.rooms.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OptionXtra {
	private int xtraNo;
	private String xtraName;
	private int xtraPrice;
	private String xtraExplanation;
}

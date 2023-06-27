package com.btc.booking.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class OptionXtra {

	private int xtraNo;//XTRA_NO;
	private String xtraName;//XTRA_NAME;
	private int xtraPrice;//XTRA_PRICE;
	private String xtraExplanation;//XTRA_EXPLANATION;
}

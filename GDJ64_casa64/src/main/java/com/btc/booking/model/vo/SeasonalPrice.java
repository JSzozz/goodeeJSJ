package com.btc.booking.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SeasonalPrice {

	private String season;
	private Date startDate;
	private Date endDate;
	private float weekdayRate;
	private float weekendRate;
	
}

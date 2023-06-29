package com.btc.rooms.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeasonalPrice {
	private String season;
	private Date startDate;
	private Date endDate;
	private double weekdayRate;
	private double weekendRate;
}

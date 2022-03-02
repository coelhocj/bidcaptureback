package com.effecti.bidcaptureapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bid {
    private int id;
	private String name;
	private int year;
	private String month;
	private int day;
	private String link;
}

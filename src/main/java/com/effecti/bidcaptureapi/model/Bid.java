package com.effecti.bidcaptureapi.model;

import java.util.List;

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
	private String description;
	private String status;
	private List<Doc> docs;
	private List<String> history;
}

package com.effecti.bidcaptureapi.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bid {
    private int id;
	private String name;
	private int Year;
	private String Month;
	private int Day;
	private String link;
}

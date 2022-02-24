package com.effecti.bidcaptureapi.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bid {
    private Long id;
	private String name;
	private LocalDateTime startDate;
	private String link;
}

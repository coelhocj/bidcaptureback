package com.effecti.bidcaptureapi.controller;

import java.util.List;

import com.effecti.bidcaptureapi.model.Bid;
import com.effecti.bidcaptureapi.service.BidService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api-bids/bids")
public class BidController {

	@Autowired
	private BidService bidService;

	@GetMapping
	public ResponseEntity<List<Bid>> find() {
		List<Bid> bidsFound = bidService.find();
		if (bidsFound.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(bidsFound);
		}
	}
}

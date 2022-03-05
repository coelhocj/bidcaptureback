package com.effecti.bidcaptureapi.controller;

import java.util.List;

import com.effecti.bidcaptureapi.model.Bid;
import com.effecti.bidcaptureapi.service.BidService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api-bids/bids")
public class BidController {

	@Autowired
	private BidService bidService;

	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Bid>> find(@RequestParam String pageFrom, String pageTo) {
		try {
			List<Bid> bidsFound = bidService.find(pageFrom, pageTo);
			if (bidsFound.isEmpty()) {
				return ResponseEntity.notFound().build();
			} else {
				return ResponseEntity.status(HttpStatus.OK).body(bidsFound);
			}
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}

	}
}

package com.effecti.bidcaptureapi.controller;

import java.util.List;

import com.effecti.bidcaptureapi.model.Bid;
import com.effecti.bidcaptureapi.service.BidService;
import com.effecti.bidcaptureapi.service.WebCrawler;

import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
@RequestMapping("/api-bids/bids")
public class BidController {
    //private static final Logger logger2 = Logger.getLogger(BidController.class);


    @Autowired
	private BidService bidService;

    @GetMapping
	public ResponseEntity<List<Bid>> find() {
		if(bidService.find().isEmpty()) {
			return ResponseEntity.notFound().build(); 
		}else{
			return ResponseEntity.status(HttpStatus.OK).body(bidService.find());
		}
	}
	
	/*
    @DeleteMapping
	public ResponseEntity<Boolean> delete() {
		try {
			bidService.delete();
			return ResponseEntity.noContent().build();
		}catch(Exception e) {
			//logger.error(e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

    @PostMapping
	@ResponseBody
	public ResponseEntity<Bid> create(@RequestBody JSONObject bid) {
		var newBid = new Bid();
		newBid.setName("oioioi");
		return ResponseEntity.status(HttpStatus.OK).body(newBid);
		try {
			if(bidService.isJSONValid(bid.toString())) {
				Bid bidCreated = bidService.create(bid);
				var uri = ServletUriComponentsBuilder.fromCurrentRequest().path(bidCreated.getName()).build().toUri();
				
                bidService.add(bidCreated);
				return ResponseEntity.created(uri).body(null);
			}else {
				return ResponseEntity.badRequest().body(null);
			}
		}catch(Exception e) {
			//logger.error("JSON fields are not parsable. " + e);
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}

    @PutMapping(path = "/{id}", produces = { "application/json" })
	public ResponseEntity<Bid> update(@PathVariable("id") long id, @RequestBody JSONObject bid) {
		try {
			if(bidService.isJSONValid(bid.toString())) {
				Bid bidToUpdate = bidService.findById(id);
				if(bidToUpdate == null){
					//logger.error("Bid not found.");
					return ResponseEntity.notFound().build(); 
				}else {
					Bid bidToUpdate2 = bidService.update(bidToUpdate, bid);
					return ResponseEntity.ok(bidToUpdate2);
				}
			}else {
				return ResponseEntity.badRequest().body(null);
			}
		}catch(Exception e) {
			//logger.error("JSON fields are not parsable." + e);
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}*/

}

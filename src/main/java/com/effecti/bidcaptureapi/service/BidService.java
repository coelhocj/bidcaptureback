package com.effecti.bidcaptureapi.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.effecti.bidcaptureapi.model.Bid;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject;
import org.springframework.stereotype.Service;


@Service
public class BidService {

	private List<Bid> bids;

	public List<Bid> find() {
		createBidList();
		return bids;
	}

	public void createBidList() {
		if(bids == null) {
			bids = new ArrayList<>();
			Bid bid1 = new Bid();
			bid1.setName("bid1");
			bid1.setId(0l);
			bid1.setStartDate(LocalDateTime.of(2022,02,04,18,48));
			bid1.setLink("link teste");

			Bid bid2 = new Bid();
			bid2.setName("bid2");
			bid2.setId(1l);
			bid2.setStartDate(LocalDateTime.of(2022,02,04,18,58));
			bid2.setLink("link teste2");

			bids.add(bid1);
			bids.add(bid2);
		}
	}
	/*

    public boolean isJSONValid(String jsonInString) {
	    try {
	       return new ObjectMapper().readTree(jsonInString) != null;
	    } catch (IOException e) {
	       return false;
	    }
	}

	

    private long parseId(JSONObject bid) {
		return Long.valueOf((int) bid.get("id"));
	}

    private void setBidValues(JSONObject jsonBid, Bid bid) {
		
		String name = (String) jsonBid.get("name");
		
        bid.setName(name);
	}

	public Bid create(JSONObject jsonBid) {
		
		Bid bid = new Bid();
		bid.setName(jsonBid.get("name").toString());
		
		return bid;
	}

	public Bid update(Bid bid, JSONObject jsonBid) {
		
		setBidValues(jsonBid, bid);
		return bid;
	}
	
	public void add(Bid bid) {
		createBidList();
		bids.add(bid);
	}

	

	public Bid findById(long id) {
		return bids.stream().filter(t -> id == t.getId()).collect(Collectors.toList()).get(0);
	}

	public void delete() {
		bids.clear();
	}

	public void clearObjects() {
		bids = null;
	}*/
}

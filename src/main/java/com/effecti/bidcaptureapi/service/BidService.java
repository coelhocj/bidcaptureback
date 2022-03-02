package com.effecti.bidcaptureapi.service;

import java.util.ArrayList;
import java.util.List;
import com.effecti.bidcaptureapi.model.Bid;
import org.springframework.stereotype.Service;

@Service
public class BidService {

	private List<String> urls = new ArrayList<String>();

	private WebCrawler webCrawler = new WebCrawler();

	private int numberOfPages = 2;

	public List<Bid> find() {
		if (urls.size() == 0) {
			createURLList();
		}
		return webCrawler.getPageData(urls);
	}

	public void createURLList() {
		for (int i = 1; i <= numberOfPages; i++) {
			urls.add(
					"https://www.bombinhas.sc.gov.br/licitacoes/index/rotear/actionDestino/listar/codMapaItem/11152/pagina/"
							+ i);
		}
	}
}

package com.effecti.bidcaptureapi.service;

import java.util.ArrayList;
import java.util.List;
import com.effecti.bidcaptureapi.model.Bid;
import org.springframework.stereotype.Service;

@Service
public class BidService {

	private List<String> urls = new ArrayList<String>();

	private WebCrawler webCrawler = new WebCrawler();

	public List<Bid> find(String pageFrom, String pageTo) throws Exception {
		createURLList(pageFrom, pageTo);
		return webCrawler.getPageData(urls);
	}

	public void createURLList(String pageFrom, String pageTo) {
		urls.clear();
		for (int i = Integer.parseInt(pageFrom); i <= Integer.parseInt(pageTo); i++) {
			urls.add(
					"https://www.bombinhas.sc.gov.br/licitacoes/index/rotear/actionDestino/listar/codMapaItem/11152/pagina/"
							+ i);
		}
	}
}

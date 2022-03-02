package com.effecti.bidcaptureapi.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.effecti.bidcaptureapi.model.Bid;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebCrawler {

    private List<Bid> bids = new ArrayList<>();

    private List<String> visitedUrls = new ArrayList<>();

    private boolean checkIfUrlIsVisited(String url){
        for (String urlToVisit : this.visitedUrls) {
            if(urlToVisit == url){
                return true;
            }
        }
        return false;
    }

    private boolean addNewBid(Bid bidToAdd){
        for (Bid bid : bids) {
            if(bid.getId()==bidToAdd.getId()){
                return false;
            }
        }
        bids.add(bidToAdd);
        return true;
    }

    public List<Bid> getPageData(List<String> URLs) {

        for (String url : URLs) {
            if(checkIfUrlIsVisited(url)){
                break;
            }
            try {
                Document doc = Jsoup.connect(url).get();

                Elements list = doc.select("ul.item-lista");
                Elements itens = list.select("li");
                for (Element item : itens) {
                    Element data = item.selectFirst("div.data");
                    Element info = item.selectFirst("div.nome-objeto");
                    if (info != null) {
                        Bid bid = new Bid();

                        bid.setName(item.select("a").text());
                        String link = item.select("a").attr("href");
                        bid.setId(Integer.parseInt(link.substring(link.lastIndexOf("/") + 1)));
                        bid.setLink("https://www.bombinhas.sc.gov.br" + link);
                        bid.setYear(Integer.parseInt(data.selectFirst("span.ano").text()));
                        bid.setMonth(data.selectFirst("span.mes").text());
                        bid.setDay(Integer.parseInt(data.selectFirst("span.dia").text()));
                        addNewBid(bid);
                    }

                }
                visitedUrls.add(url);
            } catch (IOException e) {
                return this.bids;
            }

        }
        return this.bids;
    }
}

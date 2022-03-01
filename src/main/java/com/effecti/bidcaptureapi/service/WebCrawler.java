package com.effecti.bidcaptureapi.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.effecti.bidcaptureapi.model.Bid;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebCrawler {

    public List<Bid> getPageLinks(String URL) {

        // we use the conditional statement to check whether we have already crawled the
        // URL or not.
        List<Bid> preparedList = new ArrayList<>();
            try {

                Document doc = Jsoup.connect(URL).get();

                Elements list = doc.select("ul.item-lista");
                Elements itens = list.select("li");
                for (Element item : itens) {
                    Element data = item.selectFirst("div.data");
                    Element info = item.selectFirst("div.nome-objeto");
                    if(info!=null){
                        Bid bid = new Bid();

                        bid.setName(item.select("a").text());
                        String link = item.select("a").attr("href");
                        bid.setId(Integer.parseInt(link.substring(link.lastIndexOf("/")+1)));
                        bid.setLink("https://www.bombinhas.sc.gov.br"+link);
                        bid.setYear(Integer.parseInt(data.selectFirst("span.ano").text()));
                        bid.setMonth(data.selectFirst("span.mes").text());
                        bid.setDay(Integer.parseInt(data.selectFirst("span.dia").text()));
                        preparedList.add(bid);
                    }

                }
                
                return preparedList;
                //System.out.println(item); 
                // for each extracted URL, we repeat process

            }
            // handle exception
            catch (IOException e) {
                // print exception messages
                return null;
                //System.err.println("For '" + URL + "': " + e.getMessage());
            }
    }
}

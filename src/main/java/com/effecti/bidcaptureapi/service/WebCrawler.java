package com.effecti.bidcaptureapi.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.effecti.bidcaptureapi.model.Bid;
import com.effecti.bidcaptureapi.model.Doc;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebCrawler {

    private List<Bid> bids = new ArrayList<>();

    private boolean addNewBid(Bid bidToAdd){
        for (Bid bid : bids) {
            if(bid.getId()==bidToAdd.getId()){
                return false;
            }
        }
        bids.add(bidToAdd);
        return true;
    }

    private void completeBidFullData() throws Exception{
        for (Bid bid : this.bids) {
            try {
                Document document = Jsoup.connect(bid.getLink()).get();
                Element details = document.selectFirst("div.licitacoes");
                bid.setStatus(details.selectFirst("div[class^=status]").text());
                bid.setDescription(details.selectFirst("dd[class=objeto]").text());

                Element docs = details.selectFirst("div.docs");
                Elements itens = docs.select("li");
                List<Doc> docsList = new ArrayList<>();
                for (Element item : itens) {
                    Doc doc = new Doc();
                    doc.setDate(item.selectFirst("strong.data-docs").text());
                    Element link = item.selectFirst("a");
                    doc.setTitle(link.text());
                    doc.setLink("https://www.bombinhas.sc.gov.br"+link.attr("href"));
                    docsList.add(doc);
                }
                bid.setDocs(docsList);
                
                Element history = details.selectFirst("div[class=docs historico]");
                Elements historyItens = history.select("li");

                List<String> historyList = new ArrayList<>();
                for (Element historyItem : historyItens) {
                    historyList.add(historyItem.selectFirst("p").text());
                }
                bid.setHistory(historyList);
            } catch (IOException e) {
                throw new Exception("Error");
            }

        }
    }

    public List<Bid> getPageData(List<String> URLs) throws Exception {
        bids.clear();
        for (String url : URLs) {
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
            } catch (IOException e) {
                throw new Exception("Error");
            }

        }
        completeBidFullData();
        return this.bids;
    }
}

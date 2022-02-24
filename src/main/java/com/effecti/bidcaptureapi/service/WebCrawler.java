package com.effecti.bidcaptureapi.service;

import java.io.IOException;
import java.util.HashSet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebCrawler {

    public void getPageLinks(String URL) {

        // we use the conditional statement to check whether we have already crawled the
        // URL or not.
            try {

                Document doc = Jsoup.connect(URL).get();

                Elements list = doc.select("ul.item-lista");
                Elements itens = list.select("li");
                //System.out.println(itens); 
                for (Element item : itens) {
                    System.out.println(item.select("span.dia").text()); 
                }
                
                System.out.println("Passou aquiiiii"); 
                //System.out.println(item); 
                // for each extracted URL, we repeat process

            }
            // handle exception
            catch (IOException e) {
                // print exception messages
                System.err.println("For '" + URL + "': " + e.getMessage());
            }
    }
}

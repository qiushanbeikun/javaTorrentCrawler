package Model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.ArrayList;

public class AnimeCrawler {

    private String input;
    private final String NYAA_SEARCH_URL = "https://nyaa.si/?f=0&c=0_0&q=";
    private final String NYAA_VIEW_URL = "https://nyaa.si";

    public AnimeCrawler(String input) {
        this.input = inputParser(input);
        this.startSearch();
    }

    private String inputParser(String input){
        String[] splited = input.split(" ");
        if (splited.length != 1) {
            String result = "";
            for (int i = 0; i < splited.length - 1; i++) {
                result += splited[i];
                result += "+";
            }
            result += splited[splited.length-1];
            return result;
        } else {
            return input;
        }
    }

    private void startSearch () {

        String url = NYAA_SEARCH_URL + this.input + "&s=size&o=desc";

        try {
            Document document = Jsoup.connect(url).get();
            Elements tableElements = document.select("table");

            Elements tableHeaderEles = tableElements.select("thead tr th");
            for (int i = 0; i < tableHeaderEles.size(); i++) {
                System.out.println(tableHeaderEles.get(i).text());
            }
            System.out.println();

            Elements tableRowElements = tableElements.select(":not(thead) tr");

            for (int i = 0; i < tableRowElements.size(); i++) {
                Element row = tableRowElements.get(i);
                System.out.println("row" + i);
                Elements rowItems = row.select("td");
                System.out.println(rowItems.size());
                for (int j = 0; j < rowItems.size(); j++) {
                    System.out.println(rowItems.get(j).text());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}

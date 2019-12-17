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
    private final String NYAA_DOWNLOAD_URL = "https://nyaa.si";


    /**
     * SPEC about the String array array
     * each string array contains the info of the source
     * 0 the detailed site page on nyaa.si
     * 1 name of the torrent
     * 2 magnet value
     * 3 size of the files
     * 4 date of created
     * 5 number of seeders
     * 6 number of leechers
     * 7 count of completion
     * */

    private String[][] arrays = new String[1000][];

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
                System.out.println("@!@#!@#!@#!#!#" + tableHeaderEles.get(i).text());
            }
            System.out.println();

            Elements tableRowElements = tableElements.select(":not(thead) tr");

            for (int i = 1; i < tableRowElements.size(); i++) {
                Element row = tableRowElements.get(i);
                System.out.println("row@ " + i);
                Elements rowItems = row.select("td");
                System.out.println(rowItems.size());
                String[] eachResult = new String[8];
                for (int j = 0; j < rowItems.size(); j++) {
                    // System.out.println(rowItems.get(j).text());
                    eachResult[j] = rowItems.get(j).text();
                }
                // System.out.println(rowItems.get(2).select("a").get(1).attr("href").split("&")[0]);
                eachResult[0] = NYAA_DOWNLOAD_URL + rowItems.get(1).select("a").attr("href");
                eachResult[2] = rowItems.get(2).select("a").get(1).attr("href").split("&")[0];
                for (String string :
                        eachResult) {
                    System.out.println(string);
                }
                arrays[i] = eachResult;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}

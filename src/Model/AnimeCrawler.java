package Model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.ArrayList;

public class AnimeCrawler extends Crawler{

    private String input;


    /**
     * SPEC about the String array array
     * each string array contains the info of the source
     * 0 the detailed site page on nyaa.si
     * 1 name of the torrent
     * 2 magnet hash value
     * 3 size of the files
     * 4 date of created
     * 5 number of seeders
     * 6 number of leechers
     * 7 count of completion
     * */

    private String[][] arrays;

    public AnimeCrawler(String input) {
        this.input = inputParser(input);
        // this.startSearch();
    }

    public String startSearch () {

        String NYAA_SEARCH_URL = "https://nyaa.si/?f=0&c=0_0&q=";
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

            arrays = new String[tableRowElements.size()][];
            for (int i = 1; i < tableRowElements.size(); i++) {
                Element row = tableRowElements.get(i);
                System.out.println("row@ " + i);
                Elements rowItems = row.select("td");
                // System.out.println(rowItems.size());
                String[] eachResult = new String[8];
                for (int j = 0; j < rowItems.size(); j++) {
                    // System.out.println(rowItems.get(j).text());
                    eachResult[j] = rowItems.get(j).text();
                }
                // System.out.println(rowItems.get(2).select("a").get(1).attr("href").split("&")[0]);
                String NYAA_DOWNLOAD_URL = "https://nyaa.si";
                eachResult[0] = NYAA_DOWNLOAD_URL + rowItems.get(1).select("a").attr("href").split("#")[0];
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
        return animeSelector(arrays);

    }

    /**
     * @param resultList the string array array containing the search result
     * @return
     * return a single hash value if only one type of resource exists
     * return up to 2 hash values if two options exists 1080p and 720p
     */
    private String animeSelector(String[][] resultList){
        // first, calculate the average value of calculable items
        // calculating the average size of all resources is useless

        Integer[] seederList;
        Integer count = 0;
        int determination;
        if (arrays.length >= 10){
            determination = 10;
            System.out.println("@@@@@@@@@@@@@");
        } else {
            determination = arrays.length-1;
        }
        System.out.println(arrays[10][5]);
        for (int i = 0; i < determination ; i++) {
            System.out.println(arrays[i][5]);
        }
        Integer seederAverage = count / resultList.length;
        System.out.println(seederAverage);

        System.out.println(arrays[10][5]);


        return "123";
    }

    private boolean seedHealthyCheck(){
        return false;
    }


}

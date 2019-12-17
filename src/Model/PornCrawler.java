package Model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class PornCrawler extends Crawler{
    private String input;
    private final String SUKEBEI_SEARCH_URL = "https://sukebei.nyaa.si/?f=0&c=0_0&q=";
    private final String SUKEBEI_VIEW_URL = "https://sukebei.nyaa.si/";

    /**
     * SUKEBEI has almost the same structure of nyaa, the code in this page will be much similar with the anime page
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

    public PornCrawler(String input) {
        this.input = inputParser(input);
    }

    public void startSearch () {

        String url = SUKEBEI_SEARCH_URL + this.input + "&s=size&o=desc";

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
                eachResult[0] = SUKEBEI_VIEW_URL + rowItems.get(1).select("a").attr("href");
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

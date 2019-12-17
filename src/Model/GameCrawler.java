package Model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class GameCrawler {


    private String input;
    private final String TPB_SEARCH_URL = "https://thepiratebay.org/search/";
    private final String TPB_VIEW_URL = "https://thepiratebay.org/";


    /**
     * SPECS for this page
     * 0 game type ie. PC or PS4 or XBOX or others
     * 1 torrent name
     * 2 # of seeders
     * 3 # of leechers
     * 4 magnet
     */

    private String[][] arrays = new String[1000][];

    public GameCrawler(String input) {
        this.input = inputParser(input);
    }

    public void startSearch () {

        String url = TPB_SEARCH_URL + this.input + "/0/99/400";

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
                String[] eachResult = new String[5];
                for (int j = 0; j < rowItems.size(); j++) {
                    // System.out.println(rowItems.get(j).text());
                    // System.out.println(rowItems.get(j).attr("href"));
                    eachResult[j] = rowItems.get(j).text();
                }
                // System.out.println(rowItems.get(1).select("a").get(1).attr("href").split("&dn")[0]);
                eachResult[4] = rowItems.get(1).select("a").get(1).attr("href").split("&dn")[0];
                for (String string :
                        eachResult) {
                    System.out.println(string);
                }
                // System.out.println(rowItems.get(2).select("a").get(1).attr("href").split("&")[0]);
                arrays[i] = eachResult;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String inputParser(String input){
        String[] splited = input.split(" ");
        if (splited.length != 1) {
            String result = "";
            for (int i = 0; i < splited.length - 1; i++) {
                result += splited[i];
                result += "%20";
            }
            result += splited[splited.length-1];
            return result;
        } else {
            return input;
        }
    }

}

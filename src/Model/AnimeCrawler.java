package Model;

import javax.swing.text.html.HTML;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class AnimeCrawler {

    private String input;
    private final String NYAA_URL = "https://nyaa.si/?f=0&c=0_0&q=";

    String fileName = "D:\\HTMLCache\\" + "nyaa_" + this.input + ".html";

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
        

        try {
            URL url = new URL(NYAA_URL + this.input);
            URLConnection conn = url.openConnection();

            // open the stream and put it into BufferedReader
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));

            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                System.out.println(inputLine);
                bw.write(inputLine);
                bw.newLine();
            }
            br.close();
            bw.close();

            System.out.println("Done");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void HTMLParser(){

    }



}

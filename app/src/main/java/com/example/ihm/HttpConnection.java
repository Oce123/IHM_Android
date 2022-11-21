package com.example.ihm;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class HttpConnection {


    public static void main(String[] args) throws IOException {
        try {
            String link = "https://planif.esiee.fr/jsp/standard/index.jsp";
            URL url = new URL(link);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.connect();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}

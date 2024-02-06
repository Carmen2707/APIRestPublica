package org.example;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Ejercicio2 {
    public static void main(String[] args) throws IOException, InterruptedException {

        System.out.println(getPosterUrl());


    }

    public static String getFirstSeriesId() {
        String serieId = "";
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://moviesminidatabase.p.rapidapi.com/series/byYear/1995/"))
                    .header("X-RapidAPI-Key", "d904802967msh879ea48d1ec1f0cp1575adjsn97b6acff64a9")
                    .header("X-RapidAPI-Host", "moviesminidatabase.p.rapidapi.com")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject jsObject = new JSONObject(response.body());
            System.out.println(jsObject.toString(2));
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return serieId;
    }

    public static JSONObject getPosterUrl() {
        JSONObject jsonObject = new JSONObject();
        try {
            HttpRequest request1 = HttpRequest.newBuilder()
                    .uri(URI.create("https://moviesminidatabase.p.rapidapi.com/series/id/tt0111976/"))
                    .header("X-RapidAPI-Key", "d904802967msh879ea48d1ec1f0cp1575adjsn97b6acff64a9")
                    .header("X-RapidAPI-Host", "moviesminidatabase.p.rapidapi.com")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response1 = HttpClient.newHttpClient().send(request1, HttpResponse.BodyHandlers.ofString());

            JSONObject jsObject1 = new JSONObject(response1.body());

            int startYear = jsObject1.getJSONObject("results").getInt("start_year");
            String bannerUrl = jsObject1.getJSONObject("results").getString("banner");

            System.out.println("start_year: " + startYear);
            System.out.print("banner: " + bannerUrl);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return jsonObject;
    }

}


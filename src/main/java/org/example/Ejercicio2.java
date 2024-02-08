package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Ejercicio2 {
    public static void main(String[] args) {

        System.out.println(getPosterUrl(getFirstSeriesId()));
    }

    private static String getFirstSeriesId() {
        String serieId = "";
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://moviesminidatabase.p.rapidapi.com/series/byYear/1995/"))
                    .header("X-RapidAPI-Key", "d904802967msh879ea48d1ec1f0cp1575adjsn97b6acff64a9")
                    .header("X-RapidAPI-Host", "moviesminidatabase.p.rapidapi.com")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject jsonResponse = new JSONObject(response.body());
            JSONArray jsonArray = jsonResponse.getJSONArray("results");

            //for para encontrar la serie con en titulo "Dragon Ball"
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject serie = jsonArray.getJSONObject(i);
                String title = serie.getString("title");
                if ("Dragon Ball".equalsIgnoreCase(title)) {
                    serieId = serie.getString("imdb_id");
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return serieId;
    }

    public static JSONObject getPosterUrl(String serieId) {
        JSONObject jsonObject = new JSONObject();
        try {
            HttpRequest request1 = HttpRequest.newBuilder()
                    .uri(URI.create("https://moviesminidatabase.p.rapidapi.com/series/id/" + serieId + "/"))
                    .header("X-RapidAPI-Key", "d904802967msh879ea48d1ec1f0cp1575adjsn97b6acff64a9")
                    .header("X-RapidAPI-Host", "moviesminidatabase.p.rapidapi.com")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response1 = HttpClient.newHttpClient().send(request1, HttpResponse.BodyHandlers.ofString());

            JSONObject jsObject1 = new JSONObject(response1.body());

            String bannerUrl = jsObject1.getJSONObject("results").getString("banner");

            System.out.print("banner: " + bannerUrl);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return jsonObject;
    }

}


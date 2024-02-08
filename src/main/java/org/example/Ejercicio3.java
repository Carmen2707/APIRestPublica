package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Ejercicio3 {
    public static void main(String[] args) {

        System.out.println(getEpisodio(getSerie()));
    }

    private static String getEpisodio(String seriesId) {
        String episodio = "";
        try {
            HttpRequest request1 = HttpRequest.newBuilder()
                    .uri(URI.create("https://moviesminidatabase.p.rapidapi.com/series/id/" + seriesId + "/season/3/episode/1/"))
                    .header("X-RapidAPI-Key", "d904802967msh879ea48d1ec1f0cp1575adjsn97b6acff64a9")
                    .header("X-RapidAPI-Host", "moviesminidatabase.p.rapidapi.com")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response1 = HttpClient.newHttpClient().send(request1, HttpResponse.BodyHandlers.ofString());
            JSONObject jsObject1 = new JSONObject(response1.body());
            System.out.println(jsObject1.toString(2));
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return episodio;
    }

    private static String getSerie() {
        String seriesId = "";
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://moviesminidatabase.p.rapidapi.com/series/idbyTitle/Dragon%20Ball/"))
                    .header("X-RapidAPI-Key", "d904802967msh879ea48d1ec1f0cp1575adjsn97b6acff64a9")
                    .header("X-RapidAPI-Host", "moviesminidatabase.p.rapidapi.com")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject jsObject = new JSONObject(response.body());
            JSONArray jsonArray = jsObject.getJSONArray("results");

            //for para encontrar la serie con en titulo "Dragon Ball"
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject serie = jsonArray.getJSONObject(i);
                String title = serie.getString("title");
                if ("Dragon Ball".equalsIgnoreCase(title)) {
                    seriesId = serie.getString("imdb_id");
                    break;
                }
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return seriesId;

    }
}

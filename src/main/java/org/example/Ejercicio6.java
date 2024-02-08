package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Ejercicio6 {
    public static void main(String[] args) {
        System.out.println(awards(getMovieId()));

    }

    public static String awards(String movieId) {
        String awards = "";
        try {

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://moviesminidatabase.p.rapidapi.com/movie/id/" + movieId + "/awards/"))
                    .header("X-RapidAPI-Key", "d904802967msh879ea48d1ec1f0cp1575adjsn97b6acff64a9")
                    .header("X-RapidAPI-Host", "moviesminidatabase.p.rapidapi.com")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject jsObject = new JSONObject(response.body());
            System.out.println(jsObject.toString(2));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return awards;
    }

    public static String getMovieId() {
        String movieId = "";
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://moviesminidatabase.p.rapidapi.com/movie/byYear/1982/"))
                    .header("X-RapidAPI-Key", "d904802967msh879ea48d1ec1f0cp1575adjsn97b6acff64a9")
                    .header("X-RapidAPI-Host", "moviesminidatabase.p.rapidapi.com")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject jsonResponse = new JSONObject(response.body());


            // Obtener el ID de la película "Poltergeist"
            JSONArray resultsArray = jsonResponse.getJSONArray("results");
            for (int i = 0; i < resultsArray.length(); i++) {
                JSONObject movie = resultsArray.getJSONObject(i);
                if ("Poltergeist".equals(movie.getString("title"))) {
                    movieId = movie.getString("imdb_id");
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return movieId;
    }

}

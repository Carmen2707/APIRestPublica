package org.example;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Ejercicio5 {
    public static void main(String[] args) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://moviesminidatabase.p.rapidapi.com/movie/byYear/1982/"))
                .header("X-RapidAPI-Key", "d904802967msh879ea48d1ec1f0cp1575adjsn97b6acff64a9")
                .header("X-RapidAPI-Host", "moviesminidatabase.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        JSONObject jsObject = new JSONObject(response.body());
        int cantidad = jsObject.getJSONArray("results").length();

        System.out.println("En el año 1982 hay registradas " + cantidad + " películas");
    }
}

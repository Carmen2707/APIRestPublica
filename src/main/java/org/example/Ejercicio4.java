package org.example;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Ejercicio4 {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println(getActorDescription());
    }

    private static String getMovieByTitle() {
        String movie = "";

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://moviesminidatabase.p.rapidapi.com/movie/imdb_id/byTitle/Dragonball%20Evolution/"))
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

        return movie;
    }

    private static String getActor() {
        String actor = "";

        try {
            HttpRequest request1 = HttpRequest.newBuilder()
                    .uri(URI.create("https://moviesminidatabase.p.rapidapi.com/movie/id/tt1098327/cast/"))
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
        return actor;
    }

    private static JSONObject getActorDescription() {
        JSONObject jsonObject = new JSONObject();
        try {

            HttpRequest request2 = HttpRequest.newBuilder()
                    .uri(URI.create("https://moviesminidatabase.p.rapidapi.com/actor/id/nm0154226/"))
                    .header("X-RapidAPI-Key", "d904802967msh879ea48d1ec1f0cp1575adjsn97b6acff64a9")
                    .header("X-RapidAPI-Host", "moviesminidatabase.p.rapidapi.com")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response2 = HttpClient.newHttpClient().send(request2, HttpResponse.BodyHandlers.ofString());
            JSONObject jsObject2 = new JSONObject(response2.body());
            System.out.println(jsObject2.toString(2));


            String birth_place = jsObject2.getJSONObject("results").getString("birth_place");
            String star_sign = jsObject2.getJSONObject("results").getString("star_sign");

            System.out.println("birth_place: " + birth_place);
            System.out.print("star_sign: " + star_sign);
        } catch (IOException | InterruptedException e) {
        }
        return jsonObject;
    }

}

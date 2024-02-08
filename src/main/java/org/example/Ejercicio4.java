package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Ejercicio4 {
    public static void main(String[] args) {
        System.out.println(getActorDescription(getActor(getMovieByTitle())));
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
            JSONArray resultsArray = jsObject.getJSONArray("results");
            if (!resultsArray.isEmpty()) {
                JSONObject movieInfo = resultsArray.getJSONObject(0);
                movie = movieInfo.getString("imdb_id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return movie;
    }

    private static String getActor(String idPelicula) {
        String actor = "";

        try {
            HttpRequest request1 = HttpRequest.newBuilder()
                    .uri(URI.create("https://moviesminidatabase.p.rapidapi.com/movie/id/" + idPelicula + "/cast/"))
                    .header("X-RapidAPI-Key", "d904802967msh879ea48d1ec1f0cp1575adjsn97b6acff64a9")
                    .header("X-RapidAPI-Host", "moviesminidatabase.p.rapidapi.com")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response1 = HttpClient.newHttpClient().send(request1, HttpResponse.BodyHandlers.ofString());
            JSONObject jsObject1 = new JSONObject(response1.body());
            if (jsObject1.has("results")) {
                JSONArray rolesArray = jsObject1.getJSONObject("results").getJSONArray("roles");

                // Buscar el actor que interpreta a Goku
                for (int i = 0; i < rolesArray.length(); i++) {
                    JSONObject role = rolesArray.getJSONObject(i);
                    if ("Goku".equalsIgnoreCase(role.getString("role"))) {
                        actor = role.getJSONObject("actor").getString("imdb_id");
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return actor;
    }

    private static JSONObject getActorDescription(String idActor) {
        JSONObject jsonObject = new JSONObject();
        try {

            HttpRequest request2 = HttpRequest.newBuilder()
                    .uri(URI.create("https://moviesminidatabase.p.rapidapi.com/actor/id/" + idActor + "/"))
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
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
        return jsonObject;
    }

}

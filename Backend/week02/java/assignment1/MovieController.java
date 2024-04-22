package assignment1;

import com.google.gson.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MovieController {

    static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    static Gson gson2 = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .setPrettyPrinting()
            .create();


    public List<MovieDTO> getByRatingAbove(double rating, List<MovieDTO> list) {

        List<MovieDTO> newList = list.stream()
                .filter(movie -> movie.getRating() > rating)
                .sorted(Comparator.comparing(MovieDTO::getRating).reversed())
                .collect(Collectors.toList());

        return newList;
    }

    public List<MovieDTO> getSortedByReleaseYear(List<MovieDTO> list) {

        List<MovieDTO> newList = list.stream().sorted(Comparator.comparing(MovieDTO::getReleaseYear).reversed())
                .collect(Collectors.toList());

        return newList;
    }


    public MovieDTO2 getMovie(String movieId, String key) {
        String url = "https://api.themoviedb.org/3/movie/id?api_key={API}"
                .replace("id", movieId)
                .replace("{API}", key);

        String res = getResponseBody(url);

        MovieDTO2 dto2 = gson2.fromJson(res, MovieDTO2.class);

        return dto2;
    }

    public List<MovieDTO> getListOfMovies(String key) {

        List<MovieDTO> listOfDTO = new ArrayList<MovieDTO>();

        String url = "https://api.themoviedb.org/3/movie/popular?api_key={API}"
                .replace("{API}", key);

        String res = getResponseBody(url);

        JsonObject jsonResponse = gson.fromJson(res, JsonObject.class);
        JsonArray results = jsonResponse.getAsJsonArray("results");

        for (JsonElement j : results) {

            JsonObject object = j.getAsJsonObject();

            boolean isAdult = object.get("adult").getAsBoolean();
            int id = object.get("id").getAsInt();
            String title = object.get("title").getAsString();
            String original_language = object.get("original_language").getAsString();
            String original_title = object.get("original_title").getAsString();
            String overview = object.get("overview").getAsString();
            String media_type = "Movie";
            String release_date = object.get("release_date").getAsString();
            double rating = object.get("vote_average").getAsDouble();

            listOfDTO.add(new MovieDTO(isAdult, id, title, original_language, original_title, overview, media_type, release_date, rating));

        }
        return listOfDTO;
    }

    public String getResponseBody(String url) {
        // Using OkHttp: Can sometime cause program to hang. Then use Apache HttpClient instead.
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder().url(url).addHeader("accept", "application/json").method("GET", null).build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            String res = response.body().string();
            System.out.println();
            return res;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /*
    public MovieDTO2 searchMovie(String movieId, String key) {
        String url = "https://api.themoviedb.org/3/movie/title?api_key={API}"
                .replace("title", movieId)
                .replace("{API}", key);

        String res = getResponseBody(url);

        MovieDTO2 dto2 = gson2.fromJson(res, MovieDTO2.class);

        return dto2;
    }
     */

}

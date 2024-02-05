package assignment2;

import com.google.gson.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class APIExercise {
    // Show how to read Rest API Json into a DTO or a JsonObject
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final String API_KEY = System.getenv("API_KEY");
    //private static String url = "https://api.themoviedb.org/3/movie/id?api_key={api_key}";

    public static void main(String[] args) {

        APIExercise API = new APIExercise();

        API.getMovie("tt0111161", API_KEY);

    }

    private String getResponseBody(String url) {
        // Using OkHttp: Can sometime cause program to hang. Then use Apache HttpClient instead.
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(url)
                .addHeader("accept", "application/json")
                .method("GET", null)
                .build();
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

    public MovieDTO getMovie(String movieId, String key) {
        String url = "https://api.themoviedb.org/3/movie/id?api_key={api_key}"
                .replace("id", movieId)
                .replace("{api_key}", key);

        String res = getResponseBody(url);
        System.out.println("JSON Structure: " + res);
        MovieDTO movieDTOS = gson.fromJson(res, MovieDTO.class);
        System.out.println(movieDTOS);
        return movieDTOS;
    }


}

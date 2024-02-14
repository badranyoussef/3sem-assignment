package JasonAPI;

import com.google.gson.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class JasonAPIExercise {

    // Show how to read Rest API Json into a DTO or a JsonObject
    static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void main(String[] args) {

        jokeDTO dto = getRandomJoke();
        System.out.println(dto.getJoke());

    }


    public static String getResponseBody(String url) {
        // Using OkHttp: Can sometime cause program to hang. Then use Apache HttpClient instead.
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Accept", "application/json")
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


    public static jokeDTO getRandomJoke(){
        String joke = getResponseBody("https://icanhazdadjoke.com/");
        jokeDTO dto = gson.fromJson(joke, jokeDTO.class);
        return dto;
    }
}

class jokeDTO {
    String joke;
    public String getJoke(){
        return joke;
    }
}
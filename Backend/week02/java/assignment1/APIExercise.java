package assignment1;

import com.google.gson.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.List;

public class APIExercise {
    // Show how to read Rest API Json into a DTO or a JsonObject
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final String API_KEY = System.getenv("API_KEY");


    public static void main(String[] args) {

        MovieController mc = new MovieController();

        APIExercise API = new APIExercise();

        MovieDTO dto = API.getMovie("tt0111161", API_KEY);
        String releaseDate = dto.getRelease_date();
        System.out.println(dto.getReleaseYear());

        MovieDTO2 dto2 = mc.getMovie("tt0111161", API_KEY);

        System.out.println(dto2.getRelease_date());

        List<MovieDTO> listOfMovies = mc.getListOfMovies(API_KEY);

        //System.out.println(listOfMovies.size());

        List<MovieDTO> filteredListByRating = mc.getByRatingAbove(7.1, listOfMovies);

        //filteredListByRating.stream().forEach(System.out::println);

        List<MovieDTO> sortedByReleaseYear = mc.getSortedByReleaseYear(filteredListByRating);

        //sortedByReleaseYear.stream().forEach(System.out::println);
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

    public MovieDTO getMovie(String movieId, String key) {
        String url = "https://api.themoviedb.org/3/movie/id?api_key={API}"
                .replace("id", movieId)
                .replace("{API}", key);

        String res = getResponseBody(url);

        JsonElement jsonElement = JsonParser.parseString(res);
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        boolean isAdult = jsonObject.get("adult").getAsBoolean();
        int id = jsonObject.get("id").getAsInt();
        String title = jsonObject.get("title").getAsString();
        String original_language = jsonObject.get("original_language").getAsString();
        String original_title = jsonObject.get("original_title").getAsString();
        String overview = jsonObject.get("overview").getAsString();
        String media_type = "Movie";
        String release_date = jsonObject.get("release_date").getAsString();
        double rating = jsonObject.get("vote_average").getAsDouble();

        MovieDTO dto = new MovieDTO(isAdult, id, title, original_language, original_title, overview, media_type, release_date, rating);

        return dto;
    }
}

package assignment2;

import assignment1.APIExercise;
import assignment1.MovieController;
import assignment1.MovieDTO;
import org.junit.jupiter.api.BeforeAll;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MovieControllerTest {


    private static final String API_KEY = System.getenv("API_KEY");
    static APIExercise api;
    static MovieController mc;

    static List<MovieDTO> list;

    @BeforeAll
    static void beforeAll() {
        mc = new MovieController();
        api = new APIExercise();
        list = mc.getListOfMovies(API_KEY);
    }

    @org.junit.jupiter.api.Test
    void getSortedByReleaseYear() {
    }


    @org.junit.jupiter.api.Test
    void getByRatingAbove() {
        //Listen bør have 20 objekter
        assertEquals(list.size(),20);
    }

    @org.junit.jupiter.api.Test
    void getListOfMovies() {
        boolean containsShawshank = list.stream()
                .anyMatch(movie -> "Attack".equals(movie.getTitle()));
        assertTrue(containsShawshank);
    }
}


/*
Write unit tests for the MovieController where you search for the following titles and persist them:
The Shawshank Redemption
The Godfather
The Dark Knight
The Godfather: Part II
The Lord of the Rings: The Return of the King
Pulp Fiction
12 Angry Men
The Good, the Bad and the Ugly
Forrest Gump
Fight Club
Inception
*/
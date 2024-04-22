package assignment1;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class MovieDTO2{

    private boolean adult;
    private int id;
    private String title;
    private String original_language;
    private String original_title;
    private String overview;
    private String media_type;
    private LocalDate release_date;
    private String releaseYear;
    private double vote_average;

    public MovieDTO2(boolean adult, int id, String title, String original_language, String original_title, String overview, String media_type, LocalDate release_date, double vote_average) {
        this.adult = adult;
        this.id = id;
        this.title = title;
        this.original_language = original_language;
        this.original_title = original_title;
        this.overview = overview;
        this.media_type = media_type;
        this.release_date = release_date;
        this.vote_average = vote_average;
        this.releaseYear = String.valueOf(release_date.getYear());
    }


    @Override
    public String toString() {
        return "Title: " + getTitle() + "\n" +
                "Rating: " + getVote_average() + "\n" +
                "Release Year: " + getReleaseYear() + "\n" +
                "Adult: " + isAdult() + "\n" +
                "ID: " + getId() + "\n" +
                "Media type: " + getMedia_type() + "\n";
    }
}

package assignment1;

import java.time.LocalDate;

public class MovieDTO implements ItemDTO{

    private boolean adult;
    private int id;
    private String title;
    private String original_language;
    private String original_title;
    private String overview;
    private String media_type;
    private LocalDate release_date;
    private LocalDate localDate;
    private String releaseYear;
    private double rating;

    public MovieDTO(boolean adult, int id, String title, String original_language, String original_title, String overview, String media_type, String release_date, double rating) {
        this.adult = adult;
        this.id = id;
        this.title = title;
        this.original_language = original_language;
        this.original_title = original_title;
        this.overview = overview;
        this.media_type = media_type;
        //this.release_date = release_date;
        this.localDate = LocalDate.parse(release_date);
        this.releaseYear = String.valueOf(localDate.getYear());
        this.rating = rating;
    }

    public boolean isAdult() {
        return adult;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public double getRating() {
        return rating;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public String getRelease_date() {
        return null; //release_date;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getOverview() {
        return overview;
    }

    public String getMedia_type() {
        return media_type;
    }

    @Override
    public String toString() {
        return "Title: " + title + "\n" +
                "Rating: " + getRating() + "\n" +
                "Release Year: " + getReleaseYear() + "\n" +
                "Adult: " + adult + "\n" +
                "ID: " + id + "\n" +
                "Media type: " + media_type + "\n";
    }
}

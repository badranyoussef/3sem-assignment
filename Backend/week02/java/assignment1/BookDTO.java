package assignment1;

import java.time.LocalDate;

public class BookDTO implements ItemDTO{

    private boolean adult;
    private int id;
    private String title;
    private String original_language;
    private String original_title;
    private String overview;
    private String media_type;
    private String release_date;
    private LocalDate localDate;
    private String releaseYear;
    private double rating;

    public BookDTO(boolean adult, int id, String title, String original_language, String original_title, String overview, String media_type, String release_date, double rating) {
        this.adult = adult;
        this.id = id;
        this.title = title;
        this.original_language = original_language;
        this.original_title = original_title;
        this.overview = overview;
        this.media_type = media_type;
        this.release_date = release_date;
        this.localDate = LocalDate.parse(release_date);
        this.releaseYear = String.valueOf(localDate.getYear());
        this.rating = rating;
    }

    @Override
    public boolean isAdult() {
        return adult;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getOriginal_language() {
        return original_language;
    }

    @Override
    public String getOriginal_title() {
        return original_title;
    }

    @Override
    public String getOverview() {
        return overview;
    }

    @Override
    public String getMedia_type() {
        return media_type;
    }

    @Override
    public String getRelease_date() {
        return release_date;
    }

    @Override
    public LocalDate getLocalDate() {
        return localDate;
    }

    @Override
    public String getReleaseYear() {
        return releaseYear;
    }

    @Override
    public double getRating() {
        return rating;
    }
}

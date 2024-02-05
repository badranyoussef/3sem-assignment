package assignment2;

public class MovieDTO {
    private boolean adult;
    private int id;
    private String title;
    private String original_language;
    private String original_title;
    private String overview;
    private String media_type;

    public MovieDTO(boolean adult, int id, String title, String original_language, String original_title, String overview, String media_type) {
        this.adult = adult;
        this.id = id;
        this.title = title;
        this.original_language = original_language;
        this.original_title = original_title;
        this.overview = overview;
        this.media_type = media_type;
    }

    public boolean isAdult() {
        return adult;
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

    @Override
    public String toString() {
        return "MovieDTO{" +
                "adult=" + adult +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", original_language='" + original_language + '\'' +
                ", original_title='" + original_title + '\'' +
                ", overview='" + overview + '\'' +
                ", media_type='" + media_type + '\'' +
                '}';
    }

    public String getMedia_type() {
        return media_type;
    }


}

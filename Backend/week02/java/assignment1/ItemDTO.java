package assignment1;

import java.time.LocalDate;

public interface ItemDTO<T> {
    boolean isAdult();
    LocalDate getLocalDate();
    double getRating();
    String getReleaseYear();
    String getRelease_date();
    int getId();
    String getTitle();
    String getOriginal_language();
    String getOriginal_title();
    String getOverview();
    String getMedia_type();
}
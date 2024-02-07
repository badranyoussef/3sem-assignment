package assignment3;

import lombok.Getter;

@Getter
public class chucknorrisJokesDTO implements Jokes {
    String value;

    public chucknorrisJokesDTO(String value) {
        this.value = value;
    }
}

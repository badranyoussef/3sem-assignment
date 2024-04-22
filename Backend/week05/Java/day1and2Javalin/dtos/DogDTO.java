package day1and2Javalin.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class DogDTO {

    private int id;
    private String name;
    private String breed;
    private String gender;
    private int age;

    public enum Gender {
        MALE, FEMALE
    }

    public enum Breed {
        BULLDOG, ROTTWEILER, POODLE, LABRADOR
    }
}

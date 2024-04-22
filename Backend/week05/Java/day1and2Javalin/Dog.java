package day1and2Javalin;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Dog {

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

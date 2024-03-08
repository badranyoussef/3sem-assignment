package theVeterinarian.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Patient {

    private int id;
    private String name;
    private int age;
    private String breed;
    private String gender;
    private List<String> medication = new ArrayList<>();
    private List<String> allergies = new ArrayList<>();



}

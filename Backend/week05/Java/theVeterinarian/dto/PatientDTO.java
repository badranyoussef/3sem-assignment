package theVeterinarian.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class PatientDTO {
    private String name;
    private int age;
    private String breed;
    private String gender;
}

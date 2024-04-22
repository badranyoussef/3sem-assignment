package theVeterinarian.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import theVeterinarian.model.Patient;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@JsonView
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AppointmentDTO {

    private int Id;
    private String doctorName;
    private String dateTime;

}

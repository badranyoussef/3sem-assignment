package theVeterinarian.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@JsonView
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Appointment {

    private int Id;
    Patient patient;
    //private int patientId;
    private String doctorName;
    private String appointmentReason;
    private String dateTime;

}

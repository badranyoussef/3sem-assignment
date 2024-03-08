package theVeterinarian.controller;

import io.javalin.http.Handler;
import theVeterinarian.dto.PatientDTO;
import theVeterinarian.model.Patient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PatientController {

    public static Handler getAllPatients(Map<Integer, Patient> patients) {
        return ctx -> {
            if (patients.isEmpty()) {
                ctx.status(404).result("No patients available");
            } else {
                ctx.json(mapToList(patients));
            }
        };
    }

    public static Handler getPatientById(Map<Integer, Patient> patients) {
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            if (patients.containsKey(id)) {
                Patient p = patients.get(id);
                ctx.json(new PatientDTO(p.getName(), p.getAge(), p.getBreed(), p.getGender()));
            } else {
                ctx.status(404).result("The id you are looking for does not exist");
            }
        };
    }

    private static List<PatientDTO> mapToList(Map<Integer, Patient> patients){
        List<PatientDTO> patientDTOList = patients.values().stream().toList().stream()
                .map(p -> new PatientDTO(p.getName(), p.getAge(), p.getBreed(), p.getGender()))
                .collect(Collectors.toList());
        return patientDTOList;
    }

}

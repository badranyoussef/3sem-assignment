package theVeterinarian.controller;

import io.javalin.http.Handler;
import theVeterinarian.dto.AppointmentDTO;
import theVeterinarian.model.Appointment;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AppointmentController {

    public static Handler getAllAppointments(Map<Integer, Appointment> appointments) {
        return ctx -> {
            if (appointments.isEmpty()) {
                ctx.status(404).result("No appointments available");
            } else {
                ctx.json(mapToList(appointments));
            }
        };
    }

    public static Handler getAppointmentById(Map<Integer, Appointment> appointments) {
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            if (appointments.containsKey(id)) {
                Appointment a = appointments.get(id);
                ctx.json(new AppointmentDTO(a.getId(), a.getDoctorName(), a.getDateTime()));
            } else {
                ctx.status(404).result("The id you are looking for does not exist");
            }
        };
    }

    private static List<AppointmentDTO> mapToList(Map<Integer, Appointment> appointments){
        List<AppointmentDTO> appointmentDTOList = appointments.values().stream().toList().stream()
                .map(a -> new AppointmentDTO(a.getId(),a.getDoctorName(),a.getDateTime()))
                .collect(Collectors.toList());
        return appointmentDTOList;
    }
}

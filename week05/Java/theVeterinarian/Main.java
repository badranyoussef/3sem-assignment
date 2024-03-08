package theVeterinarian;

import io.javalin.Javalin;
import theVeterinarian.controller.AppointmentController;
import theVeterinarian.controller.PatientController;
import theVeterinarian.model.Appointment;
import theVeterinarian.model.Patient;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Main {

    public static void main(String[] args) {

        Javalin app = Javalin.create().start(7007);

                //HUSK... Når metoden List.of() benyttes kan man efterfølgende ikke tilføge objekter til collection igen.
                //derfor brug denne KUN hvis der ikke skal være flere!!
        List<String> allergies = List.of("aaaa", "sssss", "eeeee");
        List<String> medications = List.of("YYY", "Ibuprofen", "Panodil");

        Patient p1 = new Patient(1, "Simba", 2, "Labrador", "male", medications, allergies);
        Patient p2 = new Patient(2, "Lola", 4, "Labrador", "female", medications, allergies);
        Patient p3 = new Patient(3, "muma", 5, "Labrador", "female", medications, allergies);

        Appointment a1 = new Appointment(1, p1, "Steven", "Feber", LocalDateTime.now().plusDays(2).toString());
        Appointment a2 = new Appointment(2, p2, "Toby", "Feber", LocalDateTime.now().plusDays(5).toString());
        Appointment a3 = new Appointment(3, p3, "Isabelle", "Feber", LocalDateTime.now().plusDays(8).toString());


        Map<Integer, Appointment> appointments = new HashMap<>();
        appointments.put(a1.getId(), a1);
        appointments.put(a2.getId(), a2);
        appointments.put(a3.getId(), a3);

        Map<Integer, Patient> patients = new HashMap<>();
        patients.put(p1.getId(), p1);
        patients.put(p2.getId(), p2);
        patients.put(p3.getId(), p3);

        //EX1:
        //get patient by id

//        app.get("patientss", ctx -> ctx.json(patients));
//
//        //Get patient by id
//        app.get("patient/{id}", ctx -> {
//            int id = Integer.parseInt(ctx.pathParam("id"));
//            if (patients.containsKey(id)) {
//                ctx.json(patients.get(id));
//            } else {
//                ctx.result("The id you are looking for does not exist");
//            }
//        });
//        //get all patients
//        app.get("patients", ctx -> ctx.json(patients));
//
//        //get appointment by id
//        app.get("appointment/{id}", ctx -> {
//            int id = Integer.parseInt(ctx.pathParam("id"));
//            if (appointments.containsKey(id)) {
//                ctx.json(appointments.get(id));
//            } else {
//                ctx.result("The id you are looking for does not exist");
//            }
//        });
//        //get all appointments
//        app.get("appointments", ctx -> ctx.json(appointments));

        app.routes(
                () -> {
                    path("api/vet/", () -> {
                        //Get patient by id
                        get("patients/{id}", PatientController.getPatientById(patients));
                        //get all patients
                        get("patients", PatientController.getAllPatients(patients));
                        //get appointment by id
                        get("appointments/{id}", AppointmentController.getAppointmentById(appointments));
                        //get all appointments
                        get("appointments", AppointmentController.getAllAppointments(appointments));
                    });
                });
    }
}

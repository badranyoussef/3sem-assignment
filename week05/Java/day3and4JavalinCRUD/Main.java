package day3and4JavalinCRUD;

import day3and4JavalinCRUD.config.HibernateConfig;
import day3and4JavalinCRUD.controller.HotelController;
import day3and4JavalinCRUD.controller.RoomController;
import day3and4JavalinCRUD.dao.HotelDAO;
import day3and4JavalinCRUD.dao.RoomDAO;
import io.javalin.Javalin;
import jakarta.persistence.EntityManagerFactory;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig(false);

        HotelDAO hDAO = new HotelDAO(emf);
        RoomDAO rDAO = new RoomDAO(emf);

        Javalin app = Javalin.create().start(7007);

        //benytter metoden routes for at samle endpoints og
        //definere den samme start path for alle endpoint
        app.routes(
                () -> {
                    path("api/hotel", () -> {
                        get("/", HotelController.getAll(hDAO));
                        get("{id}", HotelController.getHotel(hDAO));
                        get("{id}/rooms", HotelController.getHotelRooms(hDAO));
                        post("create", HotelController.create(hDAO));
                        delete("delete/{id}", HotelController.delete(hDAO));
                        put("update/{id}", HotelController.update(hDAO));
                    });
                    path("api/room", () -> {
                        get("/", RoomController.getAll(rDAO));
                        get("/{id}", RoomController.getRoom(rDAO));
                        post("/create/{hotelId}", RoomController.create(rDAO, hDAO));
                        delete("/delete/{id}", RoomController.delete(rDAO));
                        put("/update/{id}", RoomController.update(rDAO));
                    });

                });


//        app.get("rooms", RoomController.getAll(roomDAO));

//        ApplicationConfig applicationConfig = ApplicationConfig.getInstance();
//
//        applicationConfig
//                .initiateServer()
//                .startServer(7007)
//                .setExceptionHandling()
//                .setRoute(() -> {
//                    path("api", () -> {
//                        get("/", ctx -> HotelController.getAll(hotelDAO));
//                        get("/{id}", HotelController.get(hotelDAO));
//                    });
//                });


        //Data persisted:

//        Hotel h = new Hotel(1, "Hilton", "Vesterbrogade");
//        Room r1 = new Room(1, 101, 50.4);
//        Room r2 = new Room(2, 121, 50.4);
//        Room r3 = new Room(3, 142, 50.4);
//        Room r4 = new Room(4, 112, 50.4);
//        Room r5 = new Room(5, 109, 50.4);
//        h.addRoom(r1);
//        h.addRoom(r2);
//        h.addRoom(r3);
//        h.addRoom(r4);
//        h.addRoom(r5);


    }

}

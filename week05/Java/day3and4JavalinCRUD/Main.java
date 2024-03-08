package day3and4JavalinCRUD;

import day3and4JavalinCRUD.controller.HotelController;
import day3and4JavalinCRUD.controller.RoomController;
import day3and4JavalinCRUD.dao.HotelDAO;
import day3and4JavalinCRUD.dao.RoomDAO;
import io.javalin.Javalin;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Main {

    public static void main(String[] args) {
        //EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig(false);
        HotelDAO hotelDAO = new HotelDAO();
        RoomDAO roomDAO = new RoomDAO();


        Javalin app = Javalin.create().start(7007);

        //Routes: hvordan applicationen skal reagere på forskellige

        app.get("hotels/{id}", HotelController.getHotel(hotelDAO));
        app.get("hotels", HotelController.getAll(hotelDAO));
        app.post("hotels/create", HotelController.create(hotelDAO));
        app.delete("hotels/delete/{id}", HotelController.delete(hotelDAO));
        app.put("hotels/update/{id}", HotelController.update(hotelDAO));

        //benytter metoden routes for at samle endpoints og
        //definere den samme start path for alle endpoint

        app.routes(
                () -> path("api/hotels", () ->{
                    get("/", HotelController.getAll(hotelDAO));
                    get("{id}", HotelController.getHotel(hotelDAO));
                    post("create", HotelController.create(hotelDAO));
                    delete("delete/{id}", HotelController.delete(hotelDAO));
                    put("update/{id}", HotelController.update(hotelDAO));
                })
        );



        app.get("rooms", RoomController.getAll(roomDAO));

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

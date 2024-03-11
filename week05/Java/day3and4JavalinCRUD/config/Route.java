package day3and4JavalinCRUD.config;

import day3and4JavalinCRUD.controller.HotelController;
import day3and4JavalinCRUD.controller.RoomController;
import day3and4JavalinCRUD.dao.HotelDAO;
import day3and4JavalinCRUD.dao.RoomDAO;
import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManagerFactory;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Route {

    private static EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig(false);
    private static HotelDAO hDAO = new HotelDAO(emf);
    private static RoomDAO rDAO = new RoomDAO(emf);


    public static EndpointGroup hotelEndPoints() {
        return () -> {
            path("hotel", () -> {
                get("/", HotelController.getAll(hDAO));
                get("{id}", HotelController.getHotel(hDAO));
                get("{id}/rooms", HotelController.getHotelRooms(hDAO));
                post("create", HotelController.create(hDAO));
                delete("delete/{id}", HotelController.delete(hDAO));
                put("update/{id}", HotelController.update(hDAO));
            });
        };
    }

    public static EndpointGroup roomEndPoints() {
        return () -> {
            path("room", () -> {
                get("/", RoomController.getAll(rDAO));
                get("/{id}", RoomController.getRoom(rDAO));
                post("/create/{hotelId}", RoomController.create(rDAO, hDAO));
                delete("/delete/{id}", RoomController.delete(rDAO));
                put("/update/{id}", RoomController.update(rDAO));
            });
        };
    }

}
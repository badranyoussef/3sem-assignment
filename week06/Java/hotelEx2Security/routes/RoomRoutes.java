package hotelEx2Security.routes;

import hotelEx2Security.dao.HotelDAO;
import hotelEx2Security.controller.RoomController;
import hotelEx2Security.dao.RoomDAO;
import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManagerFactory;
import static io.javalin.apibuilder.ApiBuilder.*;

public class RoomRoutes {
    private HotelDAO hDAO;
    private static RoomDAO rDAO;


    public RoomRoutes(EntityManagerFactory emf){
        hDAO = HotelDAO.getInstance(emf);
        rDAO = RoomDAO.getInstance(emf);
    }

    public EndpointGroup getRoomRoutes() {
        return () -> {
            path("rooms", () -> {
                get("/", RoomController.getAll(rDAO));
                get("/{id}", RoomController.getRoom(rDAO));
                post("/create/{hotelId}", RoomController.create(rDAO, hDAO));
                delete("/delete/{id}", RoomController.delete(rDAO));
                put("/update/{id}", RoomController.update(rDAO));
            });
        };
    }
}

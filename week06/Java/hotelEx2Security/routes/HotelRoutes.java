package hotelEx2Security.routes;

import hotelEx2Security.persistence.HibernateConfig;
import hotelEx2Security.controller.HotelController;
import hotelEx2Security.dao.HotelDAO;
import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManagerFactory;

import static io.javalin.apibuilder.ApiBuilder.*;

public class HotelRoutes {
    private static EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig(false);
    private static HotelDAO hDAO;
    public HotelRoutes(EntityManagerFactory emf){
        hDAO = HotelDAO.getInstance(emf);
    }

    public EndpointGroup getHotelRoutes() {

        return () -> {
            path("hotels", () -> {
                get("/", HotelController.getAll(hDAO));
                get("{id}", HotelController.getHotel(hDAO));
                get("{id}/rooms", HotelController.getHotelRooms(hDAO));
                post("create", HotelController.create(hDAO));
                delete("delete/{id}", HotelController.delete(hDAO));
                put("update/{id}", HotelController.update(hDAO));
            });
        };
    }

}

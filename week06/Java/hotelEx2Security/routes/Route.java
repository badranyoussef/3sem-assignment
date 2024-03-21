package hotelEx2Security.routes;


import com.fasterxml.jackson.databind.ObjectMapper;
import hotelEx2Security.Security.RouteRoles;
import hotelEx2Security.controllers.*;
import hotelEx2Security.daos.HotelDAO;
import hotelEx2Security.daos.RoomDAO;
import hotelEx2Security.persistence.HibernateConfig;
import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManagerFactory;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Route {

    private static EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig(false);
    private static HotelDAO hDAO = HotelDAO.getInstance(emf);
    private static RoomDAO rDAO = RoomDAO.getInstance(emf);
    private static ISecurityController securityController = new SecurityController();
    private static ObjectMapper jsonMapper = new ObjectMapper();
    private static RouteRoles roles;
    
    
    public static EndpointGroup getRoomRoutes() {
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

    public static EndpointGroup getUserRoutes() {
        return () -> {
            path("auth", () -> {
                post("/login", securityController.login(), roles.ANYONE);
                post("/register", securityController.register(), roles.ANYONE);
            });
        };
    }

    public static EndpointGroup getSecuredRoutes() {
        return () -> {
            path("/protected", () -> {
                before(securityController.authenticate()); // alle forespørger som kommer ind bliver fanget her og bliver behandlet
                get("/user_login_with_token", (ctx) -> ctx.json(jsonMapper.createObjectNode().put("msg", "Hello from USER Protected")), roles.USER);
                get("/admin_login_with_token", (ctx) -> ctx.json(jsonMapper.createObjectNode().put("msg", "Hello from ADMIN Protected")), roles.ADMIN);
            });
        };
    }

    public static EndpointGroup getHotelRoutes() {
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


//    Således kan jeg samle alle route og dermed kalde en metode som returnerer alle routes
    public static EndpointGroup getAllRoutes() {
        return () -> {
            // Samler alle dine ruter her
            path("rooms", () -> {
                get("/", RoomController.getAll(rDAO), roles.ADMIN, roles.USER);
                get("/{id}", RoomController.getRoom(rDAO), roles.ANYONE);
                post("/create/{hotelId}", RoomController.create(rDAO, hDAO), roles.ANYONE);
                delete("/delete/{id}", RoomController.delete(rDAO), roles.ANYONE);
                put("/update/{id}", RoomController.update(rDAO), roles.ANYONE);
            });

            path("auth", () -> {
                post("/login", securityController.login(), roles.ANYONE);
                post("/register", securityController.register(), roles.ANYONE);
            });

            path("/protected", () -> {
                before(securityController.authenticate());
                get("/user_login_with_token", (ctx) -> ctx.json(jsonMapper.createObjectNode().put("msg", "Hello from USER Protected")), roles.USER);
                get("/admin_login_with_token", (ctx) -> ctx.json(jsonMapper.createObjectNode().put("msg", "Hello from ADMIN Protected")), roles.ADMIN);
            });

            path("hotels", () -> {
                get("/", HotelController.getAll(hDAO), roles.ANYONE);
                get("{id}", HotelController.getHotel(hDAO), roles.ANYONE);
                get("{id}/rooms", HotelController.getHotelRooms(hDAO), roles.ANYONE);
                post("create", HotelController.create(hDAO), roles.ANYONE);
                delete("delete/{id}", HotelController.delete(hDAO), roles.ANYONE);
                put("update/{id}", HotelController.update(hDAO), roles.ANYONE);
            });
        };
    }
}

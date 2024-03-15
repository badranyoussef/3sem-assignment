package hotelEx2Security.routes;

import hotelEx2Security.Security.RouteRoles;
import hotelEx2Security.controller.UserController;
import hotelEx2Security.dao.UserDAO;
import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManagerFactory;

import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;

public class UserRoutes {

    private static UserDAO uDAO;

    public UserRoutes(EntityManagerFactory emf){
        uDAO = UserDAO.getInstance(emf);
    }

    private final UserController userController = new UserController();
    public EndpointGroup getUserRoutes() {
        return () -> {
            path("/auth", () -> {
                post("/login", userController.login (uDAO), RouteRoles.ANYONE);
                //post("/register", userController::register, RouteRoles.ANYONE);
            });
        };
    }
}
package hotelEx2Security;

import hotelEx2Security.config.ApplicationConfig;
import hotelEx2Security.dao.UserDAO;
import hotelEx2Security.persistence.HibernateConfig;
import hotelEx2Security.routes.HotelRoutes;
import hotelEx2Security.routes.RoomRoutes;
import hotelEx2Security.routes.Route;
import hotelEx2Security.routes.UserRoutes;
import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManagerFactory;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig(false);

//        UserDAO userDAO = UserDAO.getInstance(emf);
//
//        userDAO.addRoleToUser("youssef","admin");
        Route route = new Route();
        //HotelRoutes hRoutes = new HotelRoutes(emf);
        //RoomRoutes rRoutes = new RoomRoutes(emf);
        UserRoutes userRoutes = new UserRoutes(emf);

        ApplicationConfig applicationConfig = ApplicationConfig.getInstance();

        applicationConfig.initiateServer()
                .startServer(7007)
                .setExceptionHandling()
//                .setRoute(Route.getRoomRoutes())
//                .setRoute(Route.getUserRoutes())
                .setRoute(Route.getHotelRoutes());
    }

}

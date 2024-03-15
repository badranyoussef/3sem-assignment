package hotelEx2Security;

import hotelEx2Security.config.ApplicationConfig;
import hotelEx2Security.dao.UserDAO;
import hotelEx2Security.model.Role;
import hotelEx2Security.model.User;
import hotelEx2Security.persistence.HibernateConfig;
import hotelEx2Security.routes.Route;
import jakarta.persistence.EntityManagerFactory;

import java.util.Set;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig(false);


        ApplicationConfig applicationConfig = ApplicationConfig.getInstance();

        applicationConfig.initiateServer()
                .startServer(7007)
                .setExceptionHandling()
                .setRoute(Route.getHotelRoutes())
                .setRoute(Route.getUserRoutes())
                .setRoute(Route.getRoomRoutes());


        UserDAO dao = UserDAO.getInstance(emf);

        //dao.createUser("youssef", "1234");

//        User user = dao.verifyUser("youssef", "1234");
//
//        System.out.println(user.getUsername());

        //TODO: Create token virkede ikke da brugerne som var persisteret ikke havde roller. Opdater nye metoder således at når en bruger oprettes
        //TODO: skal der tillknyttes en rolle. Derefter skal helel projektet ryddes op.



    }

}

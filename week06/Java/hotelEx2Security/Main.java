package hotelEx2Security;

import hotelEx2Security.config.ApplicationConfig;
import hotelEx2Security.dao.UserDAO;
import hotelEx2Security.persistence.HibernateConfig;
import hotelEx2Security.routes.Route;
import jakarta.persistence.EntityManagerFactory;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig(false);


        ApplicationConfig applicationConfig = ApplicationConfig.getInstance();

        applicationConfig.initiateServer()
                .startServer(7007)
                .setExceptionHandling()
                .checkSecurityRoles()
                .setRoute(Route.getHotelRoutes())
                .setRoute(Route.getUserRoutes())
                .setRoute(Route.getRoomRoutes())
                .setRoute(Route.getSecuredRoutes());


        UserDAO dao = UserDAO.getInstance(emf);

        //dao.createUser("youssef", "1234");

//        User user = dao.verifyUser("youssef", "1234");
//
//        System.out.println(user.getUsername());

        //TODO: Create token virkede ikke da brugerne som var persisteret ikke havde roller. Opdater nye metoder således at når en bruger oprettes
        //TODO: skal der tillknyttes en rolle. Derefter skal helel projektet ryddes op.

//        User user = new User("Badran", "1234");
//        Role role = new Role("admin");
//        user.addRole(role);
//
//        dao.createUser(user);

    }

}

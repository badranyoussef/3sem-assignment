package hotelEx2Security;

import hotelEx2Security.config.ApplicationConfig;
import hotelEx2Security.routes.Route;

public class Main {

    public static void main(String[] args) {
        ApplicationConfig applicationConfig = ApplicationConfig.getInstance();
        applicationConfig.initiateServer()
                .startServer(7007)
                .setExceptionHandling()
                .checkSecurityRoles()
                .setRoute(Route.getAllRoutes());
    }
}

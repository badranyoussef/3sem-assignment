package hotelEx2Security.routes;


import hotelEx2Security.Security.RouteRoles;
import hotelEx2Security.controller.*;
import hotelEx2Security.dao.HotelDAO;
import hotelEx2Security.dao.RoomDAO;
import hotelEx2Security.dao.UserDAO;
import hotelEx2Security.persistence.HibernateConfig;
import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManagerFactory;
import io.javalin.apibuilder.EndpointGroup;
import static io.javalin.apibuilder.ApiBuilder.*;

public class Route {

    private static EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig(false);
//    private final ExceptionController exceptionController = new ExceptionController();
//    private int count = 0;

//    private final HotelRoutes hotelRoute = new HotelRoutes(emf);
//    private final RoomRoutes roomRoute = new RoomRoutes(emf);
//    private final UserRoutes userRoutes = new UserRoutes(emf);

    private static HotelDAO hDAO = HotelDAO.getInstance(emf);
    private static RoomDAO rDAO = RoomDAO.getInstance(emf);
    private static UserDAO uDAO = UserDAO.getInstance(emf);
    private static UserController userController = new UserController();
    private static SecurityController securityController = new SecurityController(uDAO);




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
            path("/auth", () -> {
                post("/login", securityController.login());
                post("/register", securityController.register());
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






    //private final Logger LOGGER = LoggerFactory.getLogger(Routes.class);

//    private void requestInfoHandler(Context ctx) {
//        String requestInfo = ctx.req().getMethod() + " " + ctx.req().getRequestURI();
//        ctx.attribute("requestInfo", requestInfo);
//    }

//    public EndpointGroup getRoutes(Javalin app) {
//        return () -> {
//            app.before(this::requestInfoHandler);
//
//            app.routes(() -> {
//                path("/", userRoutes.getUserRoutes());
//                path("/", hotelRoute.getHotelRoutes());
//                path("/", roomRoute.getRoomRoutes());
//            });

//            app.after(ctx -> LOGGER.info(" Request {} - {} was handled with status code {}", count++, ctx.attribute("requestInfo"), ctx.status()));
//
//            app.exception(ConstraintViolationException.class, exceptionController::constraintViolationExceptionHandler);
//            app.exception(ValidationException.class, exceptionController::validationExceptionHandler);
//            app.exception(ApiException.class, exceptionController::apiExceptionHandler);
//            app.exception(AuthorizationException.class, exceptionController::exceptionHandlerNotAuthorized);
//            app.exception(Exception.class, exceptionController::exceptionHandler);
//
//        };
//    }
}

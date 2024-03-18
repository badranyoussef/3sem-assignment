package hotelEx2Security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import hotelEx2Security.dto.UserDTO;
import hotelEx2Security.exceptions.AuthorizationException;
import hotelEx2Security.persistence.HibernateConfig;
import hotelEx2Security.routes.Route;
import io.javalin.Javalin;
import io.javalin.apibuilder.EndpointGroup;
import io.javalin.http.HttpStatus;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

public class ApplicationConfig {
    ObjectMapper om = new ObjectMapper();
    private Javalin app;
    private static ApplicationConfig instance;


    //Private to ensure that it cant be initialized without singleton
    private ApplicationConfig() {
    }

    //Singleton
    public static ApplicationConfig getInstance() {
        if (instance == null) {
            instance = new ApplicationConfig();
        }
        return instance;
    }

    public ApplicationConfig initiateServer() {
        app = Javalin.create(config -> {
            config.http.defaultContentType = "application/jason";
            config.routing.contextPath = "/api";
        });
        return instance;
    }

    public ApplicationConfig startServer(int port) {
        app.start(port);
        return instance;
    }

    public ApplicationConfig setRoute(EndpointGroup route) {
        app.routes(route);
        return instance;
    }

    public ApplicationConfig setExceptionHandling() {
        //Benytter Exception for at fange alle former for exceptions.
        //Man burde lave flere exceptions så man kan være mere konkret i sin fejlhåndtering

        app.exception(Exception.class, (e, ctx) -> {
            ObjectNode node = om.createObjectNode().put("errorMessage", e.getMessage());
            ctx.status(500).json(node);
        });

        app.error(404, ctx -> {
            ctx.status(404).result("Not Found");
        });

        app.exception(IllegalStateException.class, (e, ctx) -> {
            ctx.status(400).result("Bad Request: " + e.getMessage());
        });
        return instance;
    }


//    public ApplicationConfig checkSecurityRoles() {
//
//        JsonMapper jsonMapper = new JsonMapper();
//
//        // Check roles on the user (ctx.attribute("username") and compare with permittedRoles using security
//        app.updateConfig(config -> {
//            config.accessManager((handler, ctx, permittedRoles) -> {
//// permitted roles are defined in the last arg to routes: get("/", ctx -> ctx.result("Hello
//                Set<String> allowedRoles = permittedRoles.stream().map(role -> role.toString().toUpperCase()).collect(Collectors.toSet());
//                if (allowedRoles.contains("ANYONE") || ctx.method().toString().equals("OPTIONS")) {
//// Allow requests from anyone and OPTIONS requests (preflight in CORS)
//                    handler.handle(ctx);
//                    return;
//                }
//                UserDTO user = ctx.attribute("user");
//                System.out.println("USER IN CHECK_SEC_ROLES: " + user);
//                if (user == null)
//                    ctx.status(HttpStatus.FORBIDDEN)
//                            .json(jsonMapper.createObjectNode().put("msg", "Not authorized. No username were added from the token"));
//
//                //TODO der skal et tjek her hvor user og allowed rows valideres.
//                if (SecurityController.getInstance().authorize(user, allowedRoles))
//                    handler.handle(ctx);
//                else
//                    throw new AuthorizationException(HttpStatus.FORBIDDEN.getCode(), "Unauthorized with roles: " + allowedRoles);
//            });
//        });
//        return instance;
//    }


    public void stopServer() {
        app.stop();
    }

    public static String getProperty(String propName) throws IOException {
        try (InputStream is = HibernateConfig.class.getClassLoader().getResourceAsStream("properties-from-pom.properties")) {
            Properties prop = new Properties();
            prop.load(is);
            return prop.getProperty(propName);
        } catch (IOException ex) {
            //LOGGER.error("Could not read property from pom file. Build Maven!");
            throw new IOException("Could not read property from pom file. Build Maven!");
        }
    }
}

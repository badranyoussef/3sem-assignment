package day3and4JavalinCRUD.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.javalin.Javalin;
import io.javalin.apibuilder.EndpointGroup;

public class ApplicationConfig {
    ObjectMapper om = new ObjectMapper();
    private Javalin app;
    private static ApplicationConfig instance;


    //Private to ensure that it cant be initialized without singleton
    private ApplicationConfig() {}

    //Singleton
    public static ApplicationConfig getInstance(){
        if(instance == null){
            instance = new ApplicationConfig();
        }
        return instance;
    }

    public ApplicationConfig initiateServer(){
        app = Javalin.create(config -> {
            config.http.defaultContentType = "application/jason";
            config.routing.contextPath = "/api";
        });
        return instance;
    }

    public ApplicationConfig startServer(int port){
        app.start(port);
        return instance;
    }

    public ApplicationConfig setRoute(EndpointGroup route){
        app.routes(route);
        return instance;
    }

    public ApplicationConfig setExceptionHandling(){
        //Benytter Exception for at fange alle former for exceptions.
        //Man burde lave flere exceptions så man kan være mere konkret i sin fejlhåndtering

        app.exception(Exception.class, (e, ctx) ->{
            ObjectNode node = om.createObjectNode().put("errorMessage", e.getMessage());
            ctx.status(500).json(node);
        });
        return instance;
    }


    public void stopServer() {
        app.stop();
    }
}

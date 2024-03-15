package hotelEx2Security.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hotelEx2Security.dao.UserDAO;
import hotelEx2Security.dto.UserDTO;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import jakarta.persistence.EntityNotFoundException;


public class UserController {

    private ObjectMapper objectMapper; // ObjectMapper fra Jackson biblioteket


    public Handler login(UserDAO dao) {
        return ctx -> {

         try{
            UserDTO dto = objectMapper.readValue(ctx.body(), UserDTO.class);
            dao.verifyUser(dto.getUsername(),dto.getPassword());
             System.out.println(dto);
            ctx.status(200).json(dto);
        } catch (EntityNotFoundException e) {
            ctx.status(404).result(e.getMessage());
        } catch (Exception e) {
            ctx.status(500).result("Internal server error");
        }
        };
    }
}
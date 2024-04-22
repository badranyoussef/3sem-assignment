package hotelEx2Security.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import hotelEx2Security.daos.UserDAO;
import hotelEx2Security.dto.UserDTO;
import hotelEx2Security.model.User;
import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;
import jakarta.persistence.EntityNotFoundException;


public class UserController {

    ObjectMapper objectMapper;

    public Handler login(UserDAO dao) {
        return ctx -> {
            ObjectNode returnObject; // For sending json message back
            try {
                UserDTO dto = ctx.bodyAsClass(UserDTO.class);
                System.out.println("USER in Login" + dto);

                User verifiedUserEntity = dao.verifyUser(dto.getUsername(), dto.getPassword());
                System.out.println(verifiedUserEntity);

                //String token = createToken(new UserDTO(verifiedUserEntity));
                ctx.status(200).json(dto.getUsername());
            } catch (EntityNotFoundException e) {
                ctx.status(404).result(e.getMessage());
            } catch (Exception e) {
                ctx.status(500).result("Internal server error");
            }
        };
    }

    public Handler register(UserDAO dao) {
        return (ctx) -> {
            ObjectNode returnObject = objectMapper.createObjectNode();
            try {
                UserDTO userInput = ctx.bodyAsClass(UserDTO.class);
                User created = dao.createUser(userInput.getUsername(), userInput.getPassword());

                ctx.status(HttpStatus.CREATED).json(userInput.getUsername());
            } catch (Exception e) {
                ctx.status(HttpStatus.UNPROCESSABLE_CONTENT);
                ctx.json(returnObject.put("msg", "User already exists"));
            }
        };
    }


}
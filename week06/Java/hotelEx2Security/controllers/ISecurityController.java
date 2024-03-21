package hotelEx2Security.controllers;

import com.nimbusds.jose.JOSEException;
import hotelEx2Security.dto.UserDTO;
import hotelEx2Security.exceptions.NotAuthorizedException;
import io.javalin.http.Handler;

import java.text.ParseException;
import java.util.Set;

public interface ISecurityController {
    Handler authenticate();

    Handler login();

    String createToken(UserDTO user) throws JOSEException;

    String createToken(UserDTO user, String ISSUER, String TOKEN_EXPIRE_TIME, String SECRET_KEY) throws
            JOSEException;

    Handler register();

    boolean authorize(UserDTO user, Set<String> allowedRoles);

    UserDTO verifyToken(String token);

    boolean tokenIsValid(String token, String secret) throws ParseException, JOSEException, NotAuthorizedException;

    boolean tokenNotExpired(String token) throws ParseException, NotAuthorizedException;

    UserDTO getUserWithRolesFromToken(String token) throws ParseException;

    int timeToExpire(String token) throws ParseException, NotAuthorizedException;
}

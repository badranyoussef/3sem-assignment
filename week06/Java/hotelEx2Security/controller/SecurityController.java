package hotelEx2Security.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import hotelEx2Security.dao.UserDAO;
import hotelEx2Security.dto.TokenDTO;
import hotelEx2Security.dto.UserDTO;
import hotelEx2Security.model.User;
import hotelEx2Security.persistence.HibernateConfig;
import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;
import io.javalin.validation.ValidationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityNotFoundException;
import org.eclipse.jetty.server.RequestLog;

import java.sql.Date;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

public class SecurityController {

    private static UserDAO userDAO;
    private static String SECRET_KEY = "nSUGc/1kEiZl97XiCSCrqEM61g0aIINEHzz1TR/tRLg8gWBfjVIhzlOc5TXkiR4h"; // Skal mindst være 256 bits.
    public SecurityController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public Handler authenticate() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode returnObject = objectMapper.createObjectNode();
        return (ctx) -> {
            if (ctx.method().toString().equals("OPTIONS")) {
                ctx.status(200);
                return;
            }
            String header = ctx.header("Authorization");
            if (header == null) {
                ctx.status(HttpStatus.FORBIDDEN).json(returnObject.put("msg", "Authorization header missing"));
                return;
            }
            String token = header.split(" ")[1];
            if (token == null) {
                ctx.status(HttpStatus.FORBIDDEN).json(returnObject.put("msg", "Authorization header malformed"));
                return;
            }
            UserDTO verifiedTokenUser = verifyToken(token);
            if (verifiedTokenUser == null) {
                ctx.status(HttpStatus.FORBIDDEN).json(returnObject.put("msg", "Invalid User or Token"));
            }
            System.out.println("USER IN AUTHENTICATE: " + verifiedTokenUser);
            ctx.attribute("user", verifiedTokenUser);
        };
    }

    private UserDTO verifyToken(String token) {
        try {
            // Parse the token and extract claims
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            // Extract username from claims
            String username = claims.getSubject();

            // Fetch user information from database using username
            EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig(false);
            User user;
            try (EntityManager em = emf.createEntityManager()) {
                user = em.find(User.class, username);
            }

            // Return UserDTO if user exists
            return user != null ? new UserDTO(user) : null;
        } catch (JwtException | IllegalArgumentException e) {
            // Token parsing failed or invalid
            e.printStackTrace();
            return null;
        }
    }


    public Handler login() {
        return (ctx) -> {
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode returnObject = objectMapper.createObjectNode(); // for sending json messages back to the client
            try {
                UserDTO user = ctx.bodyAsClass(UserDTO.class);
                User verifiedUserEntity = userDAO.verifyUser(user.getUsername(), user.getPassword());
                String token = createToken(new UserDTO(verifiedUserEntity));
                ctx.status(200).json(new TokenDTO(token, user.getUsername()));

            } catch (EntityNotFoundException | ValidationException e) {
                ctx.status(401);
                System.out.println(e.getMessage());
                ctx.json(returnObject.put("msg", e.getMessage()));
            }
        };
    }

    public String createToken(UserDTO user) throws JOSEException {
        try {
            String ISSUER;
            String TOKEN_EXPIRE_TIME;
            //String SECRET_KEY = null;

            if (System.getenv("DEPLOYED") != null) {
                ISSUER = System.getenv("ISSUER");
                TOKEN_EXPIRE_TIME = System.getenv("TOKEN_EXPIRE_TIME");
                SECRET_KEY = System.getenv("SECRET_KEY");
            } else {
                ISSUER = "Youssef Badran";
                TOKEN_EXPIRE_TIME = "1800000"; // 30 minutes in milliseconds
            }

            if (SECRET_KEY == null) {
                throw new IllegalStateException("Secret key is null");
            }

            return createToken(user, ISSUER, TOKEN_EXPIRE_TIME, SECRET_KEY);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            throw new JOSEException("Could not create token");
        }
    }

    public String createToken(UserDTO user, String ISSUER, String TOKEN_EXPIRE_TIME, String SECRET_KEY) throws
            JOSEException {
        try {
            // Validate user object
            if (user == null) {
                throw new IllegalArgumentException("UserDTO is null");
            }

            // Build the JWT claims set
            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .subject(user.getUsername())
                    .issuer(ISSUER)
                    .claim("username", user.getUsername())
                    .claim("roles", user.getRoles().stream().reduce((s1, s2) -> s1 + "," + s2).get())
                    .expirationTime(new Date(System.currentTimeMillis() + Long.parseLong(TOKEN_EXPIRE_TIME)))
                    .build();

            // Create payload
            Payload payload = new Payload(claimsSet.toJSONObject());

            // Create signer
            JWSSigner signer = new MACSigner(SECRET_KEY);

            // Create JWS header
            JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256);

            // Create JWS object
            JWSObject jwsObject = new JWSObject(jwsHeader, payload);

            // Sign the JWS object
            jwsObject.sign(signer);

            // Serialize the JWS object to produce the final JWT
            return jwsObject.serialize();

        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            throw new JOSEException("Could not create token", e);
        }
    }

    public Handler register() {
        return (ctx) -> {
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode returnObject = objectMapper.createObjectNode();
            try {
                UserDTO userInput = ctx.bodyAsClass(UserDTO.class);
                User created = userDAO.createUser(userInput.getUsername(), userInput.getPassword());

                String token = createToken(new UserDTO(created));
                ctx.status(HttpStatus.CREATED).json(new TokenDTO(token, userInput.getUsername()));
            } catch (EntityExistsException e) {
                ctx.status(HttpStatus.UNPROCESSABLE_CONTENT);
                ctx.json(returnObject.put("msg", "User already exists"));
            }
        };
    }
}

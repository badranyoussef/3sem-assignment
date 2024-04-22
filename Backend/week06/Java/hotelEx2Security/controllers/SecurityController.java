package hotelEx2Security.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import hotelEx2Security.daos.UserDAO;
import hotelEx2Security.dto.TokenDTO;
import hotelEx2Security.dto.UserDTO;
import hotelEx2Security.exceptions.ApiException;
import hotelEx2Security.exceptions.NotAuthorizedException;
import hotelEx2Security.model.User;
import hotelEx2Security.persistence.HibernateConfig;
import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;
import io.javalin.validation.ValidationException;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityNotFoundException;

import java.sql.Date;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;


public class SecurityController implements ISecurityController{

    private ObjectMapper objectMapper = new ObjectMapper();
    private static EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig(false);
    private static UserDAO userDAO = UserDAO.getInstance(emf);
    private static String SECRET_KEY = "nSUGc/1kEiZl97XiCSCrqEM61g0aIINEHzz1TR/tRLg8gWBfjVIhzlOc5TXkiR4h"; // Skal mindst være 256 bits.

    @Override
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

    @Override
    public Handler login() {
        return (ctx) -> {
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

    @Override
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

    @Override
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

    @Override
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

    @Override
    public boolean authorize(UserDTO user, Set<String> allowedRoles) {
        // Called from the ApplicationConfig.setSecurityRoles

        AtomicBoolean hasAccess = new AtomicBoolean(false); // Since we update this in a lambda expression, we need to use an AtomicBoolean
        if (user != null) {
            user.getRoles().stream().forEach(role -> {
                if (allowedRoles.contains(role.toUpperCase())) {
                    hasAccess.set(true);
                }
            });
        }
        return hasAccess.get();
    }

    @Override
    public UserDTO verifyToken(String token) {
        boolean IS_DEPLOYED = (System.getenv("DEPLOYED") != null);
        String SECRET = IS_DEPLOYED ? System.getenv("SECRET_KEY") : SECRET_KEY;

        try {
            if (tokenIsValid(token, SECRET) && tokenNotExpired(token)) {
                return getUserWithRolesFromToken(token);
            } else {
                throw new NotAuthorizedException(403, "Token is not valid");
            }
        } catch (ParseException | JOSEException | NotAuthorizedException e) {
            e.printStackTrace();
            throw new ApiException(HttpStatus.UNAUTHORIZED.getCode(), "Unauthorized. Could not verify token");
        }
    }

    @Override
    public boolean tokenIsValid(String token, String secret) throws ParseException, JOSEException, NotAuthorizedException {
        SignedJWT jwt = SignedJWT.parse(token);
        if (jwt.verify(new MACVerifier(secret)))
            return true;
        else
            throw new NotAuthorizedException(403, "Token is not valid");
    }

    @Override
    public boolean tokenNotExpired(String token) throws ParseException, NotAuthorizedException {
        if (timeToExpire(token) > 0)
            return true;
        else
            throw new NotAuthorizedException(403, "Token has expired");
    }

    @Override
    public UserDTO getUserWithRolesFromToken(String token) throws ParseException {
        // Return a user with Set of roles as strings
        SignedJWT jwt = SignedJWT.parse(token);
        String roles = jwt.getJWTClaimsSet().getClaim("roles").toString();
        String username = jwt.getJWTClaimsSet().getClaim("username").toString();

        Set<String> rolesSet = Arrays
                .stream(roles.split(","))
                .collect(Collectors.toSet());
        return new UserDTO(username, rolesSet);
    }

    @Override
    public int timeToExpire(String token) throws ParseException, NotAuthorizedException {
        SignedJWT jwt = SignedJWT.parse(token);
        return (int) (jwt.getJWTClaimsSet().getExpirationTime().getTime() - new java.util.Date().getTime());
    }
}

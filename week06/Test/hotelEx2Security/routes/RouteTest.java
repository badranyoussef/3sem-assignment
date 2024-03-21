package hotelEx2Security.routes;

import hotelEx2Security.config.ApplicationConfig;
import hotelEx2Security.persistence.HibernateConfig;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

class RouteTest {

    private static EntityManagerFactory emf;
    private static ApplicationConfig app;
    private static Integer port = 7007;

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "http://localhost:7007/api";
        emf = HibernateConfig.getEntityManagerFactoryConfig(true);

        app = ApplicationConfig.getInstance();

        app.initiateServer()
                .startServer(port)
                .setExceptionHandling()
                .checkSecurityRoles()
                .setRoute(Route.getUserRoutes())
                .setRoute(Route.getSecuredRoutes());
    }

    @AfterAll
    static void tearDown() {
    emf.close();
    app.stopServer();
    }

    @BeforeEach
    void beforeEach(){

       //Gem noget i databasen

    }

    @AfterEach
    void afterEach() {
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.createQuery("DELETE FROM User").executeUpdate();
            em.getTransaction().commit();
        }
    }

    @Test
    @DisplayName("Testing register user and token retrieval")
    void test1() {

        String setBody = "{\"username\": \"UserTest\", \"password\": \"1234\"}";

        String response =
                RestAssured
                        .given()
                        .contentType(ContentType.JSON)
                        .body(setBody)
                        .when()
                        .post("/auth/register")
                        .then()
                        .statusCode(201)
                        .extract().asString();

        String token = JsonPath.from(response).getString("token");
        String username = JsonPath.from(response).getString("username");
        System.out.println("Response: " + response + "\nToken: " + token + "\nUsername: " + username);
    }

    @Test
    @DisplayName("Login and retrieve token")
    void test2() {

        String setRegisterBody = "{\"username\": \"UserTest\", \"password\": \"1234\"}";
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(setRegisterBody)
                .when()
                .post("/auth/register")
                .then();

        String setLoginBody = "{\"username\": \"UserTest\", \"password\": \"1234\"}";

        String response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(setLoginBody)
                .when()
                .post("/auth/login")
                .then()
                .statusCode(200)
                .extract().asString();

        String token = JsonPath.from(response).getString("token");
        String username = JsonPath.from(response).getString("username");
        System.out.println("Response: " + response + "\nToken: " + token + "\nUsername: " + username);
    }


    @Test
    @DisplayName("Access specific route via token user-role")
    void test3(){

        String setUserName = "setUserName";
        String setUserPassword = "1234";

        String setBody = "{\"username\": \""+setUserName +"\", \"password\": \""+setUserPassword+"\"}";

        String response =
                RestAssured
                        .given()
                        .contentType(ContentType.JSON)
                        .body(setBody)
                        .when()
                        .post("/auth/register")
                        .then()
                        .statusCode(201)
                        .body("token", notNullValue())
                        .body("username", equalTo(setUserName))
                        .extract().asString();

        String token = JsonPath.from(response).getString("token");

        String expectedString = "Hello from USER Protected";
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                //.body(setBody) -> da vi ikke skal benytte POST sender vi ikke en body med
                .when()
                .get("/protected/user_login_with_token")
                .then()
                .statusCode(200)
                .body("msg", equalTo(expectedString));
    }
}
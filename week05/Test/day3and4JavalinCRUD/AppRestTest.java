package day3and4JavalinCRUD;

import day3and4JavalinCRUD.config.ApplicationConfig;
import day3and4JavalinCRUD.config.HibernateConfig;
import day3and4JavalinCRUD.config.Route;
import day3and4JavalinCRUD.ressources.Hotel;
import day3and4JavalinCRUD.ressources.Room;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

class AppRestTest {

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
//                .setExceptionHandlers()
                .setRoute(Route.hotelEndPoints())
                .setRoute(Route.roomEndPoints());

        Hotel hotel1 = new Hotel("Hotel A", "Lyngby vej");
        Hotel hotel2 = new Hotel("Hotel B", "Roskilde vej");

        hotel1.addRoom(new Room(1, 500));
        hotel1.addRoom(new Room(2, 800));

        hotel2.addRoom(new Room(1, 500));
        hotel2.addRoom(new Room(2, 800));

        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(hotel1);
            em.persist(hotel2);
            em.getTransaction().commit();
        }
    }

    @AfterAll
    static void tearDown() {
        emf.close();
        app.stopServer();
    }

    @Test
    @DisplayName("Test server is running.")
    public void test1() {
        RestAssured
                .given().log().all()
                .when()
                .get("/hotels")
                .then().log().all()
                .statusCode(200);
                //.body("hotels", hasSize(2));
    }

    @Test
    @DisplayName("Posting a hotel to the database.")
    public void test2() {

        Hotel postHotel = new Hotel("TestName", "TestAddress");

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(postHotel)
                .when()
                .post("/hotels/create")
                .then()
                .statusCode(200)
                .body("name", equalTo("TestName"))
                .body("address", equalTo("TestAddress"));
    }

    @Test
    @DisplayName("Reading hotel with specifik id 1.")
    public void test3() {

        Hotel postHotel = new Hotel("TestName", "TestAddress");

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(postHotel)
                .when()
                .post("/hotels/create")
                .then().log().all()
                .statusCode(200)
                .body("name", equalTo("TestName"))
                .body("address", equalTo("TestAddress"));
    }
}
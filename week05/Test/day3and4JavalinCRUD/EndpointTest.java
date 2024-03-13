package day3and4JavalinCRUD;

import day3and4JavalinCRUD.config.ApplicationConfig;
import day3and4JavalinCRUD.config.HibernateConfig;
import day3and4JavalinCRUD.config.Route;
import day3and4JavalinCRUD.dto.HotelDto;
import day3and4JavalinCRUD.dto.RoomDto;
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

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertEquals;

class EndpointTest {

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
                .given()
                .when()
                .get("/hotels")
                .then()
                .statusCode(200)
                .body("hotels", hasSize(2));
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
    @DisplayName("Reading hotel with specific id 1.")
    public void test3() {
        int id = 1;

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .pathParam("id", id) // Passing id as a path parameter
                .when()
                .get("/hotels/{id}") // Using GET request
                .then()
                .statusCode(200)
                .body("id", equalTo(id))
                .body("name", equalTo("Hotel A"))
                .body("address", equalTo("Lyngby vej"));
    }

    @Test
    @DisplayName("Reading hotel's room with specific hotel id 1.")
    public void test4() {

        int setId = 1;
        int expectedRoomSize = 2;
        int expectedStatusCode = 200;

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .pathParam("id", setId)
                .when()
                .get("/hotels/{id}/rooms")
                .then().log().all()
                .statusCode(expectedStatusCode)
                .body("rooms", hasSize(expectedRoomSize));
    }


    @Test
    @DisplayName("Returning the list as an HotelDTOs.")
    public void test5() {

        String[] expectedNames = {"Hotel A", "Hotel B"};
        String[] expectedAddresses = {"Lyngby vej", "Roskilde vej"};

        int expectedSize = 2;

        List<HotelDto> list = RestAssured
                .given()
                .when()
                .get("/hotels")
                .then()
                .extract()
                .body()
                .jsonPath().getList("", HotelDto.class);

        assertEquals(expectedSize, list.size());

        for (int i = 0; i < expectedSize; i++) {
            assertThat(list.get(i).getName(), equalTo(expectedNames[i]));
            assertThat(list.get(i).getAddress(), equalTo(expectedAddresses[i]));
        }
    }

    @Test
    @DisplayName("Updating an existing hotel.")
    public void test6() {

        int hotelId = 1;
        String newName = "Hotel C";
        String newAddress = "Risvej";
        int expectedStatusCode = 200;

        HotelDto updatedHotel = new HotelDto();
        updatedHotel.setName(newName);
        updatedHotel.setAddress(newAddress);

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(updatedHotel)
                .when()
                .put("/hotels/update/{id}", hotelId)
                .then().log().all()
                .statusCode(expectedStatusCode)
                .body("name", equalTo(newName))
                .body("address", equalTo(newAddress))
                .body("id", equalTo(1));
    }


    @Test
    @DisplayName("Deleting an existing hotel with a specific hotel id")
    public void test7() {

        int hotelId = 1;
        int expectedStatusCode = 204;

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .pathParam("id", hotelId)
                .when()
                .delete("/hotels/delete/{id}", hotelId)
                .then().log().all()
                .statusCode(expectedStatusCode);
    }

    @Test
    @DisplayName("Get all rooms")
    public void test8() {

        int expectedRooms = 4;
        int expectedStatusCode = 200;


        List<RoomDto> list = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .when()
                .get("/rooms")
                .then()
                .statusCode(expectedStatusCode)
                .extract()
                .body()
                .jsonPath().getList("", RoomDto.class);

        assertEquals(expectedRooms, list.size());
    }

    @Test
    @DisplayName("Get specific room by id")
    public void test9() {

        int setId = 1;
        int expectedStatusCode = 200;

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .pathParam("id", setId)
                .when()
                .get("/rooms/{id}")
                .then()
                .statusCode(expectedStatusCode)
                .body("number", equalTo(1))
                .body("price", equalTo(500.0f));
    }

    @Test
    @DisplayName("Create a new room")
    public void test10() {

        int hotelid = 1;
        Room room = new Room();
        room.setNumber(3);
        room.setPrice(700);
        int expectedStatusCode = 201;

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .pathParam("hotelId", hotelid)
                .body(room)
                .when()
                .post("/rooms/create/{hotelId}")
                .then().log().all()
                .statusCode(expectedStatusCode)
                .body("number", equalTo(3))
                .body("price", equalTo(700f));
    }

    @Test
    @DisplayName("Update specific room by id")
    public void test11() {

        int setId = 1;
        RoomDto roomDTO = new RoomDto();
        roomDTO.setNumber(4);
        roomDTO.setPrice(1000);
        int expectedStatusCode = 200;

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .pathParam("id", setId)
                .body(roomDTO)
                .when()
                .put("/rooms/update/{id}")
                .then().log().all()
                .statusCode(expectedStatusCode)
                .body("number", equalTo(4))
                .body("price", equalTo(1000f));
    }

    @Test
    @DisplayName("Delete specific room by id")
    public void test12() {

        int setId = 1;

        int expectedHotelId = 1;
        int expectedStatusCode = 204;

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .pathParam("id", setId)
                .when()
                .delete("/rooms/delete/{id}")
                .then().log().all()
                .statusCode(expectedStatusCode);
//                .body("number", equalTo(1))
//                .body("price", equalTo(500f))
//                .body("hotelId", equalTo(expectedHotelId))
//                .body("id", equalTo(expectedHotelId));
    }
}
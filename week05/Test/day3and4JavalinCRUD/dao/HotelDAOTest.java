package day3and4JavalinCRUD.dao;

import day3and4JavalinCRUD.config.ApplicationConfig;
import day3and4JavalinCRUD.config.HibernateConfig;
import day3and4JavalinCRUD.config.Route;
import day3and4JavalinCRUD.ressources.Hotel;
import day3and4JavalinCRUD.ressources.Room;
import io.restassured.RestAssured;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

class HotelDAOTest {

    private static EntityManagerFactory emf;
    private static ApplicationConfig app;
    private static Integer port = 7007;

    private static HotelDAO hotelDAO;

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "http://localhost:7007/api";

        emf = HibernateConfig.getEntityManagerFactoryConfig(true);

        hotelDAO = new HotelDAO(emf);

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
    @DisplayName("testing get all hotels method")
    void getAllHotels() {
        int expectedHotels = 2;

        List<Hotel> hotels = hotelDAO.getAll();

        assertTrue("true", !hotels.isEmpty());
        assertEquals(2, hotels.size());

    }

    @Test
    @DisplayName("testing get hotel by id method")
    void getHotelById() {

        int id = 1;
        String name = "Hotel A";
        String address = "Lyngby vej";

        Hotel hotel = hotelDAO.getById(1);

        assertEquals(name, hotel.getName());
        assertEquals(address, hotel.getAddress());

    }

    @Test
    @DisplayName("testing update method")
    void updateHotel() {
        int id = 1;

        String newName = "TestName";
        String newAddress = "TestAddress";

        Hotel foundHotel = hotelDAO.getById(1);

        foundHotel.setAddress(newAddress);
        foundHotel.setName(newName);

        Hotel updatetHotel = hotelDAO.update(foundHotel);

        assertEquals(updatetHotel.getName(), newName);
        assertEquals(updatetHotel.getAddress(), newAddress);

    }

    @Test
    @DisplayName("testing create method")
    void createHotel() {

        Hotel hotel = new Hotel("Hilton", "Østerbro");
        hotelDAO.create(hotel);

        List<Hotel> hotels = hotelDAO.getAll().stream().collect(Collectors.toList());

        boolean hotelExists = hotels.stream()
                .anyMatch(h -> h.getName().equals(hotel.getName()) && h.getAddress().equals(hotel.getAddress()));

        assertTrue("Hotellet blev ikke korrekt oprettet i databasen.", hotelExists);

    }

    @Test
    @DisplayName("testing delete method")
    void deleteHotels() {
        int expectedHotels = 2;
        Hotel foundHotel = hotelDAO.getById(1);
        hotelDAO.delete(foundHotel.getId());

        List<Hotel> hotels = hotelDAO.getAll();

        boolean found = hotels.stream().anyMatch(h -> h.getName().equals(foundHotel.getName()) && h.getAddress().equals(foundHotel.getName()));

        assertFalse("The hotel was not deleted", found);

    }
}
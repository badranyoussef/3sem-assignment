package day3and4JavalinCRUD.dao;

import day3and4JavalinCRUD.config.ApplicationConfig;
import day3and4JavalinCRUD.config.HibernateConfig;
import day3and4JavalinCRUD.ressources.Room;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;
import java.util.List;
import java.util.stream.Collectors;
import static org.junit.Assert.*;


class RoomDAOTest {

    private static EntityManagerFactory emf;

    private static RoomDAO roomDAO;

    @BeforeAll
    static void setUp() {

        emf = HibernateConfig.getEntityManagerFactoryConfig(true);

        roomDAO = new RoomDAO(emf);

        Room r1 = new Room(1, 500);
        Room r2 = new Room(2, 800);
        Room r3 = new Room(2, 800);

        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(r1);
            em.persist(r2);
            em.persist(r3);
            em.getTransaction().commit();
        }
    }

    @AfterAll
    static void tearDown() {
        emf.close();
        app.stopServer();
    }

    @Test
    @DisplayName("testing get all method")
    void getAllRooms() {
        int expectedHotels = 3;

        List<Room> rooms = roomDAO.getAll();

        assertTrue("true", !rooms.isEmpty());
        assertEquals(2, rooms.size());

    }

    @Test
    @DisplayName("testing get room by id method")
    void getRoomById() {

        int number = 1;
        double price = 200;

        Room room = roomDAO.getById(1);

        assertEquals(number, room.getNumber());
        assertEquals(price, room.getPrice(), 0);

    }

    @Test
    @DisplayName("testing update method")
    void updateHotel() {


        int number = 1;
        double price = 200;

        Room foundRoom = roomDAO.getById(1);

        foundRoom.setNumber(number);
        foundRoom.setPrice(price);

        Room updatetRoom = roomDAO.update(foundRoom);

        assertEquals(updatetRoom.getNumber(), number);
        assertEquals(updatetRoom.getPrice(),price, 0);

    }

    @Test
    @DisplayName("testing create method")
    void createHotel() {

        Room room = new Room(25, 209);
        roomDAO.create(room);

        List<Room> rooms = roomDAO.getAll().stream().collect(Collectors.toList());

        double test = rooms.stream()
                .anyMatch(r -> r.getNumber() == room.getNumber() && r.getPrice() == room.getPrice());

        assertEquals();

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
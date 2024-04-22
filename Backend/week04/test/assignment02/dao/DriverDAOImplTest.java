package assignment02.dao;

import assignment02.config.HibernateConfig;
import assignment02.model.Driver;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Array;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DriverDAOImplTest {

    private static EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig(true);
    private static DriverDAOImpl dao = new DriverDAOImpl();

    private static Driver d1;
    private static Driver d2;
    private static Driver d3;
    private static Driver d4;

    private static Driver[] drivers;



    @BeforeAll
    static void beforeAll() {
        d1 = new Driver("Youssef", "Badran", new BigDecimal(80000));
        d2 = new Driver("Lasse", "Hansen", new BigDecimal(36700));
        d3 = new Driver("Jon", "Larsen", new BigDecimal(52800));
        d4 = new Driver("Peter", "Madsen", new BigDecimal(58600));

    }

    @BeforeEach
    void setUp() {
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.persist(d1);
            em.persist(d2);
            em.persist(d3);
            em.persist(d4);
            em.getTransaction().commit();
        }
        drivers = new Driver[]{d1, d2, d3, d4};
    }

    @Test
    void saveDriver() {

        //Given
        Driver foundDriver;
        dao.saveDriver("Jakob", "Larsen", new BigDecimal(52800));

        //When
        try(var em = emf.createEntityManager()) {
            foundDriver = em.createQuery("SELECT d FROM Driver d WHERE d.name = :name AND d.surname = :surname", Driver.class)
                    .setParameter("name", "Jakob")
                    .setParameter("surname", "Larsen")
                    .getSingleResult();
        }

        //Then
        assertEquals("Jakob",foundDriver.getName());
    }

    @Test
    void getDriverById() {

        Driver driver = dao.getDriverById(d1.getId());
        assertEquals("Youssef",driver.getName());

    }

    @Test
    void updateDriver() {

        Driver driver = dao.getDriverById(d1.getId());
        driver.setName("James");
        dao.updateDriver(driver);

        assertEquals("James",driver.getName());


    }

    @Test
    void deleteDriver() {
        dao.deleteDriver(d1.getId());
    }
    @Test
    void fetchAllDriversWithSalaryGreaterThan10000() {
    }

    @Test
    void fetchHighestSalary() {

        double highestSalary = dao.fetchHighestSalary();
        // Konverterer BigDecimal til double
        double expectedSalary = d1.getSalary().doubleValue();

        assertEquals(highestSalary, expectedSalary);

    }

    @Test
    void fetchFirstNameOfAllDrivers() {
        List<String> names = dao.fetchFirstNameOfAllDrivers();

        String expected = names.get(0);
        String actual = d1.getName();

        assertEquals(expected,actual);

    }

    @Test
    void calculateNumberOfDrivers() {

        long expected = dao.calculateNumberOfDrivers();

        long actual = 4;

        assertEquals(expected,actual);


    }

    @Test
    void fetchDriverWithHighestSalary() {

        Driver expected = dao.fetchDriverWithHighestSalary();
        Driver actual = d1;

        double highestSalary = expected.getSalary().doubleValue();
        // Konverterer BigDecimal til double
        double expectedSalary = d1.getSalary().doubleValue();

//        assertEquals(expected,actual);

        assertNotNull(expected);
        assertEquals(expected.getName(), d1.getName());
        assertEquals(expected.getSurname(), d1.getSurname());
        assertEquals(highestSalary, expectedSalary);




    }


}
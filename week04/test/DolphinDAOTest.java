import assignment01.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DolphinDAOTest {

    EntityManager em;
    DolphinDAO dolphinDAO;

    private static Person p1;
    private static Person p2;
    private static Person p3;
    private static Person p4;
    private static Person p5;


    @BeforeAll
    static void setUpAll(){
        p1 = new Person("Hans",31);
        p2 = new Person("Flemming",55);
        p3 = new Person("Lasse",85);
        p4 = new Person("Thomas",44);
        p5 = new Person("Tom",85);
    }

    @BeforeEach
    void setUp() {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig(true);
        em = emf.createEntityManager();
        dolphinDAO = new DolphinDAO();
    }

    @AfterEach
    void tearDown() {
        em.close();
    }

    @Test
    void create() {
        dolphinDAO.create(p1);
        dolphinDAO.create(p2);
        dolphinDAO.create(p3);
        dolphinDAO.create(p4);
        dolphinDAO.create(p5);
    }

    @Test
    void read() {

        Person p = dolphinDAO.read(2);

        assertEquals("klaus",p.getName());

    }

    @Test
    void update() {
        Person p = dolphinDAO.read(2);
        p.setName(("klaus"));
        assertEquals("klaus", dolphinDAO.update(p).getName());

    }

    @Test
    void delete() {

    }
}
package assignment4;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.hamcrest.collection.IsEmptyCollection;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PointDAOTest {

    EntityManagerFactory emf;
    PointDAO dao;
    Point point;
    EntityManager em;

    @BeforeEach
    void setUp() {
        emf = HibernateConfig.getEntityManagerFactoryConfig();
        dao = new PointDAO();
        em = emf.createEntityManager();
    }

    @AfterEach
    void tearDown() {
        em.close();
        emf.close();
    }


    @Test
    void getPoint() {

    }

    @Test
    void totalPoints() {
        int points = Math.toIntExact(dao.totalPoints());
        assertEquals(1000, points);
    }

    @Test
    void getAveragePoints() {
        double points = dao.getAveragePoints();
        assertEquals(1000, points);
    }

    @Test
    void getAllPoint() {
        List<Point> points = dao.getAllPoint();
        assertThat(points.size(), is(1000));
        assertThat(points, not(IsEmptyCollection.empty()));

    }
}
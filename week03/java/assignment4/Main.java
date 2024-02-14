package assignment4;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class Main {

static EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
        EntityManager em = emf.createEntityManager();

        PointDAO pDAO = new PointDAO();

        // Store 1000 Point objects in the database:
    /*
        for (int i = 0; i < 1000; i++) {
            Point p = new Point(i, i);
            pDAO.savePoint(p);
        }
    */

        System.out.println(pDAO.totalPoints());
        System.out.println(pDAO.getAveragePoints());

        List<Point> points = pDAO.getAllPoint();
        points.stream().forEach(System.out::println);


    }


    //Just testing if the methods works here as well....
    // Find the number of Point objects in the database:
    public static Long totalPoints(){
        try(EntityManager em = emf.createEntityManager()){
            Query q1 = em.createQuery("SELECT COUNT(p) FROM Point p");
            System.out.println("Total Points: " + q1.getSingleResult());
            return (Long) q1.getSingleResult();
        }
    }

    // Find the average X value:
    public static double getAveragePoints() {
        try (EntityManager em = emf.createEntityManager()) {
            Query q2 = em.createQuery("SELECT AVG(p.x) FROM Point p");
            double average = (Double) q2.getSingleResult();
            return average;
        }
    }


    // Retrieve all the Point objects from the database:
    public static List<Point> getAllPoint() {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Point> query = em.createQuery("SELECT p FROM Point p", Point.class);
            List<Point> results = query.getResultList();
            return results;
        }
    }
}
package assignment02.dao;

import assignment02.config.HibernateConfig;
import assignment02.model.Driver;
import com.google.gson.internal.bind.util.ISO8601Utils;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.math.BigDecimal;
import java.util.List;

public class DriverDAOImpl implements IDriverDAO {

    private EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig(false);

    @Override
    public String saveDriver(String name, String surname, BigDecimal salary) {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(new Driver(name, surname, salary));
            em.getTransaction().commit();
            return "driver is now persisted";
        }
    }

    @Override
    public Driver getDriverById(String id) {
        try (var em = emf.createEntityManager()) {
            Driver driver = em.find(Driver.class, id);
            return driver;
        }
    }

    @Override
    public Driver updateDriver(Driver driver) {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Driver updDriver = em.merge(driver);
            em.getTransaction().commit();
            return updDriver;
        }
    }

    @Override
    public void deleteDriver(String id) {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Driver driver = em.find(Driver.class, id);
            em.remove(driver);
        }
    }

    @Override
    public List<Driver> findAllDriversEmployedAtTheSameYear(String year) {
        try (var em = emf.createEntityManager()) {
            TypedQuery<Driver> query = em.createQuery("SELECT d from Driver d where YEAR(d.employmentDate) = :year", Driver.class);
            query.setParameter("year", Integer.parseInt(year));
            return query.getResultList();
        }
    }

    @Override
    public List<Driver> fetchAllDriversWithSalaryGreaterThan10000() {
        try (var em = emf.createEntityManager()) {
            TypedQuery<Driver> query = em.createQuery("SELECT d from Driver d where d.salary > :salary", Driver.class);
            query.setParameter("salary", 10000);
            return query.getResultList();
        }
    }

    @Override
    public double fetchHighestSalary() {
        try (var em = emf.createEntityManager()) {
            TypedQuery<BigDecimal> query = em.createQuery("SELECT MAX (d.salary) from Driver d", BigDecimal.class);
            BigDecimal result = query.getSingleResult();
            return result != null ? result.doubleValue() : 0.0; // Hvis Driver ikke findes så returneres værdien 0.0
        }
    }

    @Override
    public List<String> fetchFirstNameOfAllDrivers() {
        try (var em = emf.createEntityManager()) {
            TypedQuery<String> query = em.createQuery("SELECT d.name from Driver d", String.class);
            List<String> names = query.getResultList();
            return names;
        }
    }

    @Override
    public long calculateNumberOfDrivers() {
        try (var em = emf.createEntityManager()) {
            long counter = 0;
            TypedQuery<Driver> query = em.createQuery("SELECT d from Driver d", Driver.class);
            List<Driver> drivers = query.getResultList();
            counter = getCounter(drivers, counter);
            return counter;
        }
    }

    private long getCounter(List<Driver> drivers, long counter) {
        for (Driver d : drivers) {
            counter++;
        }
        return counter;
    }

    @Override
    public Driver fetchDriverWithHighestSalary() {
        try (var em = emf.createEntityManager()) {
            TypedQuery<Driver> query = em.createQuery(
                    "SELECT d FROM Driver d ORDER BY d.salary DESC", Driver.class)
                    .setMaxResults(1);
            Driver driver = query.getSingleResult();
            return driver != null ? driver : null; // Hvis Driver ikke findes så returneres null
        }
    }
}

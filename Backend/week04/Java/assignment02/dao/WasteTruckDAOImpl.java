package assignment02.dao;

import assignment02.config.HibernateConfig;
import assignment02.model.Driver;
import assignment02.model.WasteTruck;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class WasteTruckDAOImpl implements IWasteTruckDAO {

    private EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig(false);
    private DriverDAOImpl driverDAO = new DriverDAOImpl();

    @Override
    public int saveWasteTruck(String brand, String registrationNumber, int capacity) {
        WasteTruck wasteTruck = new WasteTruck(brand,capacity,registrationNumber);

        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(wasteTruck);
            em.getTransaction().commit();
            return 200;
        }
    }

    @Override
    public WasteTruck getWasteTruckById(int id) {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            WasteTruck wasteTruck = em.find(WasteTruck.class, id);
            return wasteTruck;
        }
    }

    @Override
    public void setWasteTruckAvailable(WasteTruck wasteTruck, boolean available) {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            WasteTruck updwaste = em.find(WasteTruck.class, wasteTruck.getId());
            updwaste.setAvailable(available);
            em.merge(updwaste);
            em.getTransaction().commit();
        }
    }

    @Override
    public void deleteWasteTruck(int id) {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            WasteTruck wasteTruck = em.find(WasteTruck.class, id);
            em.remove(wasteTruck);
        }
    }

    @Override
    public void addDriverToWasteTruck(WasteTruck wasteTruck, Driver driver) {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            // Opdaterer driver's truck reference
            driver.setTruck(wasteTruck);

            // Tilføjer driveren til trucken
            wasteTruck.addDriver(driver);

            // Persisterer ændringerne for begge entiteter
            em.merge(wasteTruck);
            em.merge(driver);
            em.getTransaction().commit();
        }
    }

    @Override
    public void removeDriverFromWasteTruck(WasteTruck wasteTruck, String id) {
        Driver driver = driverDAO.getDriverById(id);
        if (wasteTruck != null && wasteTruck.getDrivers() != null && driver != null) {
            wasteTruck.getDrivers().remove(driver);

            // Nulstiller driver's truck reference
            driver.setTruck(null);
        }
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();

            // Persisterer ændringerne for begge entiteter
            em.merge(wasteTruck);
            em.merge(driver);

            em.getTransaction().commit();
        }
    }

    @Override
    public List<WasteTruck> getAllAvailableTrucks() {
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            TypedQuery<WasteTruck> query = em.createQuery(
                    "select w from WasteTruck w where w.isAvailable = true", WasteTruck.class);
            return query.getResultList();
        }
    }
}

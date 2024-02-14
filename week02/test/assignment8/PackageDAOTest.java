package assignment8;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PackageDAOTest {
    private static EntityManager entityManager;
    private static PackageDAO packageDAO;
    @BeforeEach
    void setUp() {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
        entityManager = emf.createEntityManager();
        packageDAO = new PackageDAO();
    }

    @AfterEach
    void tearDown() {
        entityManager.close();
    }

    @Test
    void create() {
        Package pkg = new Package();
        pkg.setTrackingNumber("GLS88jjXJ");
        pkg.setSenderName("Youssef");
        pkg.setReceiverName("CPH Business");
        pkg.setDeliveryStatus(Package.DeliveryStatus.PENDING);

        packageDAO.create(pkg);
        // Retrieve the package from the database and assert its existence
        Package retrievedPackage = entityManager.find(Package.class, pkg.getId());
        Assertions.assertNotNull(retrievedPackage);
        Assertions.assertEquals("GLS88jjXJ", retrievedPackage.getTrackingNumber());
    }

    @Test
    void read() {
        Package pkg = packageDAO.read(2);
        assertEquals("Youssef", pkg.getSenderName());
    }

    @Test
    void update() {
        Package pkg = packageDAO.read(2);
        pkg.setSenderName("Badran");
        Package updPkg = packageDAO.update(pkg);
        assertEquals("Badran", updPkg.getSenderName());
    }

    @Test
    void delete() {
        int deletePkgWithId = 1;
        packageDAO.delete(deletePkgWithId);

        //her er testen negativ i og med at ID 2 eksisterer
        //Package test = packageDAO.read(2);
        //assertNull(test, "Pakken med ID " + deletePkgWithId + " findes stadig. find fejlen.");

        //her er testen positiv i og med at ID 1 er blevet slettet
        Package result = packageDAO.read(deletePkgWithId);
        assertNull(result, "Pakken med ID " + deletePkgWithId + " skulle være slettet og derfor ikke findes.");
    }
}
package assignment8;


import jakarta.persistence.EntityManagerFactory;

public class PackageDAO {

    EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();

    public void create(Package p) {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            // entity is in managed state (after persist)
            em.persist(p);
            // entity is in detached state after the transaction is committed
            em.getTransaction().commit();
        }
    }


    public Package read(int id) {
        try (var em = emf.createEntityManager()) {
            //Entity is managed after being retrieved
            Package foundPackage = em.find(Package.class, id);
            // entity is detached after the entitry is returned
            return foundPackage;
        }
    }

    public Package update(Package updPkg) {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            //The entity is managed after the merge
            Package updatedPackage = em.merge(updPkg);
            // entity is in stransient state (after being retrieved)
            em.getTransaction().commit();
            //entitry is detached after it is returned
            return updatedPackage;
        }
    }


    public void delete(int id) {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            // the entity is managed after it is found/retrieved
            Package p = read(id);
            if (p != null) {
                em.remove(p);
            } else {
                System.out.println("The package you are looking for does not exist");
            }
            em.getTransaction().commit();
        }
    }
}

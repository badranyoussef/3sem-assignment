package assignment1;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class MainExercise {
    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
        var em = emf.createEntityManager();

        Person person = em.find(Person.class, 1);
        System.out.println(person);

        Address address = em.find(Address.class,2);
        System.out.println(address);

    }

        private static <T> void create (EntityManager em, T t){
            em.getTransaction().begin();
            em.persist(t);
            em.getTransaction().commit();
            em.close();
        }
}

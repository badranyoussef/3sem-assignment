package oneToMany;

import jakarta.persistence.EntityManagerFactory;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig(false);


        Student s1 = new Student("Youssef");
        Student s2 = new Student("salma");
        Course c1 = new Course("Java");

        c1.addStudent(s1);
        c1.addStudent(s2);

        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.persist(s1);
            em.persist(s2);
            em.persist(c1);
            em.getTransaction().commit();
        }
    }

}

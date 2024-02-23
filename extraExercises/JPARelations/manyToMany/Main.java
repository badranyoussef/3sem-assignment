package manyToMany;

import jakarta.persistence.EntityManagerFactory;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig(false);

        Course math = new Course("Math");
        Course java = new Course("Java");
        Course danish = new Course("Danish");

        Student s1 = new Student("Hans");
        Student s2 = new Student("Tom");
        Student s3 = new Student("Peter");

        math.addStudent(s1);
        math.addStudent(s2);

        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.persist(math);
            em.getTransaction().commit();
        }

    }
}

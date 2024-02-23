package oneToOne;

import jakarta.persistence.EntityManagerFactory;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig(false);

        Teacher teacher = new Teacher("Hans");
        Course course = new Course("JAVA");
        course.setTeacher(teacher);

        Teacher teacher2 = new Teacher("Jörg");
        Course course3 = new Course("JDD");
        Course course2 = new Course("JPA");

        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.persist(teacher);
            em.persist(course);
            em.getTransaction().commit();
        }

        try(var em = emf.createEntityManager()){
            Course c = em.find(Course.class, 1);
            System.out.println(c.getTeacher().getName());
        }



    }

}

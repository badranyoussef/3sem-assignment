package assignment6;


import jakarta.persistence.EntityManagerFactory;

public interface StudentDAOInterface {

    EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();

    void create(Student student);
    Student read(int id);
    Student update(Student updStd);
    void delete(int id);
}

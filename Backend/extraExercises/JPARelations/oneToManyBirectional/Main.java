package oneToManyBirectional;

import jakarta.persistence.EntityManagerFactory;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig(false);


        Company company = new Company("Toyota");
        Car c1 = new Car("Yaris");
        Car c2 = new Car("Corolla");

        company.addCar(c1);
        company.addCar(c2);

        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.persist(company);
            em.getTransaction().commit();
        }
    }
}

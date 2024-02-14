package assignment2;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PrePersist;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
        EntityManager em = emf.createEntityManager();
        UnicornDAO unicornDAO = new UnicornDAO();


        Unicorn Simba = new Unicorn("Simba", 20, 90);
        Unicorn Pumba = new Unicorn("Pumba", 80, 50);
        Unicorn Timon = new Unicorn("Timon", 55, 55);

        Unicorn s = unicornDAO.save(Simba);
        Unicorn p = unicornDAO.save(Pumba);
        Unicorn t = unicornDAO.save(Timon);

        //Unicorn unicornCreated = unicornDAO.save(unicorn1);

        //Unicorn unicornUpdated = unicornDAO.update(unicornFound, unicornFound.getId());

        //System.out.println(unicornUpdated);

        //unicornDAO.deleteUnicorn(unicornFound.getId());

        List<Unicorn> unicorns = unicornDAO.getAllUnicorns();

        unicorns.stream().forEach(System.out::println);

        Unicorn testingPrePersist = unicornDAO.save(new Unicorn("Tiger", 20, 500));

    }

}

package assignment2;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.util.List;

public class UnicornDAO {

    EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();

    @PrePersist
    //CREATE
    public Unicorn save(Unicorn unicorn){
        try(var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(unicorn);
            em.getTransaction().commit();
            em.close();
            return unicorn;
        }
    }
    @PreUpdate
    //UPDATE
    public Unicorn update(Unicorn unicorn, int id){
        try(var em = emf.createEntityManager()){
        em.getTransaction().begin();
        Unicorn updatedUnicorn = em.merge(unicorn);
        em.getTransaction().commit();
        return updatedUnicorn;
        }
    }

    //FIND A UNICORN BY ID
    public Unicorn findById(int id) {
        try(var em = emf.createEntityManager()) {
            Unicorn unicorn = em.find(Unicorn.class, id);
            return unicorn;
        }
    }

    //DELETE
    public void deleteUnicorn (int id){
        try(var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Unicorn unicorn = findById(id);
            if (unicorn != null) {
                em.remove(unicorn);
            } else {
                System.out.println("The unicorn you are looking for does not exist");
            }
            em.getTransaction().commit();
        }
    }

    //GET-ALL
    public List<Unicorn> getAllUnicorns (){
        try(var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            var query = em.createQuery("SELECT u FROM Unicorn u", Unicorn.class);
            return query.getResultList();
        }
    }
}

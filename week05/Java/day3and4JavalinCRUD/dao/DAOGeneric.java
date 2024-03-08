package day3and4JavalinCRUD.dao;

import day3and4JavalinCRUD.config.HibernateConfig;
import day3and4JavalinCRUD.dto.HotelDto;
import day3and4JavalinCRUD.ressources.Hotel;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

public abstract class DAOGeneric<T, ID> implements IDAO<T, ID> {

    private static EntityManagerFactory emf;
    private Class<T> entityType;

    public DAOGeneric(Class<T> entityType, EntityManagerFactory emf) {
        this.entityType = entityType;
        this.emf = emf;
    }

    @Override
    public List<T> getAll() {
        try (var em = emf.createEntityManager()) {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<T> cq = cb.createQuery(entityType);
            Root<T> rootEntry = cq.from(entityType);
            CriteriaQuery<T> all = cq.select(rootEntry);
            return em.createQuery(all).getResultList();
        }
    }


    @Override
    public T getById(ID id) {
        try (var em = emf.createEntityManager()) {
            return em.find(entityType, id);
        }
    }

    @Override
    public void create(T t) {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(t);
            em.getTransaction().commit();
        }
    }

    @Override
    public T update(T t) {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            //The entity is managed after the merge
            T entityUpdated = em.merge(t);
            // entity is in stransient state (after being retrieved)
            em.getTransaction().commit();
            //entitry is detached after it is returned
            return entityUpdated;
        }
    }

    @Override
    public void delete(ID id) {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            // the entity is managed after it is found/retrieved
            T foundEntity = getById(id);
            if (foundEntity != null) {
                em.remove(foundEntity);
                System.out.println("The entity has been deleted");
            } else {
                System.out.println("The entity you are looking for does not exist");
            }
            em.getTransaction().commit();
        }
    }
}

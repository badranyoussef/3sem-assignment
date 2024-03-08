package day3and4JavalinCRUD.classesNotUsed;

import day3and4JavalinCRUD.config.HibernateConfig;
import day3and4JavalinCRUD.dto.RoomDto;
import day3and4JavalinCRUD.ressources.Room;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.stream.Collectors;

public class RoomDAONotCorrectextend extends DAO<Room, Integer> {

    EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig(false);

    @Override
    public List getAll() {
        try(var em = emf.createEntityManager()){
            //TypedQuery<Student> query = em.createTypedQuery("SELECT s FROM Student s WHERE s.firstName = :name", Student.class);
            TypedQuery<Room> query = em.createQuery("SELECT r FROM Room r", Room.class);
            List<Room> rooms = query.getResultList();
            List<RoomDto> dtos = rooms.stream().map(r -> new RoomDto(r.getId(), r.getHotel().getId(), r.getNumber(), r.getPrice())).collect(Collectors.toList());
            return dtos;
        }
    }

    @Override
    public Room getById(Integer id) {
        try(var em = emf.createEntityManager()){
            //TypedQuery<Student> query = em.createTypedQuery("SELECT s FROM Student s WHERE s.firstName = :name", Student.class);
            TypedQuery<Room> query = em.createQuery("SELECT r FROM Room r WHERE r.id = :id", Room.class);
            query.setParameter("id", id);
            return query.getSingleResult();
        }
    }

    @Override
    public void create(Room room) {
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.persist(room);
            em.getTransaction().commit();
        }
    }

    @Override
    public Room update(Room room) {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            //The entity is managed after the merge
            Room roomUpdated = em.merge(room);
            // entity is in stransient state (after being retrieved)
            em.getTransaction().commit();
            //entitry is detached after it is returned
            return roomUpdated;
        }
    }

    @Override
    public void delete(Integer id) {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            // the entity is managed after it is found/retrieved
            Room foundRoom = getById(id);
            if (foundRoom != null) {
                em.remove(foundRoom);
                System.out.println("The entity has been deleted");
            } else {
                System.out.println("The entity you are looking for does not exist");
            }
            em.getTransaction().commit();
        }
    }
}

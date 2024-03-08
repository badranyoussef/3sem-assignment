package day3and4JavalinCRUD.dao;

import day3and4JavalinCRUD.config.HibernateConfig;
import day3and4JavalinCRUD.dto.HotelDto;
import day3and4JavalinCRUD.ressources.Hotel;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.stream.Collectors;

public class HotelDAO extends DAO <Hotel, Integer>{

    EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig(false);

    @Override
    public List getAll() {
        try(var em = emf.createEntityManager()){
            //TypedQuery<Student> query = em.createTypedQuery("SELECT s FROM Student s WHERE s.firstName = :name", Student.class);
            TypedQuery<Hotel> query = em.createQuery("SELECT h FROM Hotel h", Hotel.class);
            List<Hotel> hotels = query.getResultList();
            List<HotelDto> dtos = hotels.stream().map(hotel -> new HotelDto(hotel.getId(), hotel.getName(), hotel.getAddress())).collect(Collectors.toList());
            return dtos;
        }
    }

    @Override
    public Hotel getById(Integer id) {
        try(var em = emf.createEntityManager()){
            //TypedQuery<Student> query = em.createTypedQuery("SELECT s FROM Student s WHERE s.firstName = :name", Student.class);
            TypedQuery<Hotel> query = em.createQuery("SELECT h FROM Hotel h WHERE h.id = :id", Hotel.class);
            query.setParameter("id", id);
            return query.getSingleResult();
        }
    }

    @Override
    public void create(Hotel hotel) {
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.persist(hotel);
            em.getTransaction().commit();
        }
    }

    @Override
    public Hotel update(Hotel hotel) {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            //The entity is managed after the merge
            Hotel hotelUpdated = em.merge(hotel);
            // entity is in stransient state (after being retrieved)
            em.getTransaction().commit();
            //entitry is detached after it is returned
            return hotelUpdated;
        }
    }

    @Override
    public void delete(Integer id) {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            // the entity is managed after it is found/retrieved
            Hotel foundHotel = getById(id);
            if (foundHotel != null) {
                em.remove(foundHotel);
                System.out.println("The entity has been deleted");
            } else {
                System.out.println("The entity you are looking for does not exist");
            }
            em.getTransaction().commit();
        }
    }
}

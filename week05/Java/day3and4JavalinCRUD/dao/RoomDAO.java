package day3and4JavalinCRUD.dao;

import day3and4JavalinCRUD.config.HibernateConfig;
import day3and4JavalinCRUD.ressources.Room;
import jakarta.persistence.EntityManagerFactory;

public class RoomDAO extends DAOGeneric<Room, Integer>{
    EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig(false);

    public RoomDAO(EntityManagerFactory emf) {
        super(Room.class, emf);
    }
}

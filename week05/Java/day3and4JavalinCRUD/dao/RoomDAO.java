package day3and4JavalinCRUD.dao;

import day3and4JavalinCRUD.config.HibernateConfig;
import day3and4JavalinCRUD.ressources.Room;
import jakarta.persistence.EntityManagerFactory;

public class RoomDAO extends DAOGeneric<Room, Integer>{
    private static EntityManagerFactory emf;
    private static RoomDAO instance;


    public RoomDAO(EntityManagerFactory emf) {
        super(Room.class, emf);
    }

    public static RoomDAO getInstance(EntityManagerFactory _emf){
        if(instance == null){
            emf = _emf;
             instance = new RoomDAO(emf);
        }
        return instance;
    }
}

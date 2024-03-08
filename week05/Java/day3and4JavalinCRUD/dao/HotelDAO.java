package day3and4JavalinCRUD.dao;

import day3and4JavalinCRUD.ressources.Hotel;
import jakarta.persistence.EntityManagerFactory;

public class HotelDAO extends DAOGeneric<Hotel, Integer>{
    public HotelDAO(EntityManagerFactory emf) {
        super(Hotel.class, emf);
    }
}

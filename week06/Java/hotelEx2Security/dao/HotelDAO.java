package hotelEx2Security.dao;


import hotelEx2Security.model.Hotel;
import jakarta.persistence.EntityManagerFactory;

public class HotelDAO extends DAOGeneric<Hotel, Integer> {
    private static EntityManagerFactory emf;
    private static HotelDAO instance;

    public HotelDAO(EntityManagerFactory emf) {
        super(Hotel.class, emf);
    }

    public static HotelDAO getInstance(EntityManagerFactory _emf){
        if(instance == null){
            emf = _emf;
            instance = new HotelDAO(emf);
        }
        return instance;
    }

}

package hotelEx2Security.dao;


import hotelEx2Security.model.Hotel;
import hotelEx2Security.persistence.HibernateConfig;
import jakarta.persistence.EntityManager;
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

    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig(false);


        EntityManager em = emf.createEntityManager();
        Hotel hotel = new Hotel("Scandic", "Østerbro");
        em.getTransaction().begin();
        em.persist(hotel);
        em.getTransaction().commit();
        em.close();


    }

}

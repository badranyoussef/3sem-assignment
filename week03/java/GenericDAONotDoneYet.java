import jakarta.persistence.EntityManager;

public class GenericDAONotDoneYet {

    //CREATE
    public static <T> void create (EntityManager em, T t){
        em.getTransaction().begin();
        em.persist(t);
        em.getTransaction().commit();
        em.close();
    }

    //READ
    private static <T> void read (EntityManager em, T t){
        em.getTransaction().begin();
        em.persist(t);
        em.getTransaction().commit();
        em.close();
    }

    //UPDATE
    private static <T> void update (EntityManager em, T t){
        em.getTransaction().begin();
        em.persist(t);
        em.getTransaction().commit();
        em.close();
    }

    //DELETE
    private static <T> void delete (EntityManager em, T t){
        em.getTransaction().begin();
        em.persist(t);
        em.getTransaction().commit();
        em.close();
    }

    //GET-ALL
    private static <T> void getAll (EntityManager em, T t){
        em.getTransaction().begin();
        em.persist(t);
        em.getTransaction().commit();
        em.close();
    }


}

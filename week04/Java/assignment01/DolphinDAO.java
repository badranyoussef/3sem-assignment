package assignment01;

import jakarta.persistence.*;

import java.util.List;

public class DolphinDAO {

    EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig(false);

    //CRUD

    //CREATE
    public void create(Person p) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
        }
    }

    //READ
    public Person read(int id) {
        try (EntityManager em = emf.createEntityManager()) {
            //Entity is managed after being retrieved
            Person person = em.find(Person.class, id);
            // entity is detached after the entitry is returned
            return person;
        }
    }


    //UPDATE
    public Person update(Person person) {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            //The entity is managed after the merge
            Person personUpdated = em.merge(person);
            // entity is in stransient state (after being retrieved)
            em.getTransaction().commit();
            //entitry is detached after it is returned
            return personUpdated;
        }
    }


    //DELETE
    public void delete(int id) {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            // the entity is managed after it is found/retrieved
            Person foundPerson = read(id);
            if (foundPerson != null) {
                em.remove(foundPerson);
                System.out.println("The entity has been deleted");
            } else {
                System.out.println("The entity you are looking for does not exist");
            }
            em.getTransaction().commit();
        }
    }

    //US-2
    public long getTotalAmountPaidFees(int personId) {
        try (var em = emf.createEntityManager()) {
            Query querySum = em.createQuery("SELECT SUM(f.amount) FROM Fee f WHERE f.person.id = :person_id");
            querySum.setParameter("person_id", personId);
            long sum = (long) querySum.getSingleResult();
            return sum;
        }
    }

    //US-3
            //Using named query
    public List<Note> getAllNotesPrPersonId(int personId) {
        try (var em = emf.createEntityManager()) {
                //Named Query
            Query queryList = em.createNamedQuery("Note.getNotePrId");
            //Query queryList = em.createQuery("SELECT n FROM Note n WHERE n.person.id = :person_id");
            queryList.setParameter("person_id", personId);
            List<Note> notes = queryList.getResultList();
            return notes;
        }
    }

            //same method as above but using Native Query
    public List<Note> getAllNotesPrPersonIdNativeQuery(int personId) {
        try (var em = emf.createEntityManager()) {
                    //Remember that the syntax is SQL and use the table names instead of entity names
            Query queryList = em.createNativeQuery("SELECT * FROM note WHERE person_id = :person_id", Note.class);
            queryList.setParameter("person_id", personId);
            List<Note> notes = queryList.getResultList();
            return notes;
        }
    }

            //Using TypedQuery
    public List<NoteDTO> getAllNotesWithPersonInfo() {
        try (var em = emf.createEntityManager()) {
            TypedQuery<NoteDTO> query = em.createQuery(
                    "SELECT new assignment01.NoteDTO (n.note, n.person.name, n.person.age) FROM Note n", NoteDTO.class
                    //"SELECT n FROM Note n", NoteDTO.class --> Fungerer ikke således...
            );
            return query.getResultList();
        }
    }
}
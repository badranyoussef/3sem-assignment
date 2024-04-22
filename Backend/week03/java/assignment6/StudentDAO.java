package assignment6;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

public class StudentDAO implements StudentDAOInterface {

    @PrePersist
    @Override
    public void create(Student student) {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            // entity is in managed state (after persist)
            em.persist(student);
            // entity is in detached state after the transaction is committed
            em.getTransaction().commit();
        }
    }

    @Override
    public Student read(int id) {
        try (var em = emf.createEntityManager()) {
            //Entity is managed after being retrieved
            Student foundStudent = em.find(Student.class, id);
            // entity is detached after the entitry is returned
            return foundStudent;
        }
    }
    @PreUpdate
    @Override
    public Student update(Student updStd) {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            //The entity is managed after the merge
            Student updatedStudent = em.merge(updStd);
            // entity is in stransient state (after being retrieved)
            em.getTransaction().commit();
            //entitry is detached after it is returned
            return updatedStudent;
        }
    }

    @Override
    public void delete(int id) {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            // the entity is managed after it is found/retrieved
            Student student = read(id);
            if (student != null) {
                em.remove(student);
            } else {
                System.out.println("The student you are looking for does not exist");
            }
            em.getTransaction().commit();
        }
    }
}
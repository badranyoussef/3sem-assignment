package assignment3;

import assignment2.Unicorn;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.util.List;

public class Main {
    private static EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
    public static void main(String[] args) {
        // All entities are in transient state
        Student youssef = new Student("youssef", "Badran", "badranyoussef@gmail.com", 33);
        Student ahmad = new Student("Ahmad", "Kasab", "Ahmad@gmail.com", 25);
        Student holger = new Student("Holger", "Hansen", "hh@gmail.com", 28);
        Student emma = new Student("Emma", "Johansson", "ej@gmail.com", 22);
        Student noah = new Student("Noah", "Larsen", "nl@gmail.com", 24);
        Student olivia = new Student("Olivia", "Nielsen", "on@gmail.com", 23);
        Student william = new Student("William", "Christensen", "wc@gmail.com", 25);
        Student sophia = new Student("Sophia", "Andersen", "sa@gmail.com", 21);
        Student lucas = new Student("Lucas", "Pedersen", "lp@gmail.com", 26);
        Student mia = new Student("Mia", "Rasmussen", "mr@gmail.com", 27);
        //createStudent(emma);
        //createStudent(noah);
        //createStudent(olivia);
        //createStudent(william);
        //createStudent(sophia);
        //createStudent(lucas);
        //createStudent(mia);
        //createStudent(youssef);
        //createStudent(ahmad);
        //createStudent(holger);

        Student youssefBadran = readStudent(1);
        //System.out.println(youssefBadran);

        youssefBadran.setLastName("Badran");

        Student youssefUpd = updateStudent(youssefBadran);

        System.out.println(youssefUpd);

        //List<Student> allStudents = readAllStudents();
        //allStudents.stream().forEach(System.out::println);

        youssefBadran.setEmail("badranyoussef@gmail.com");

        Student youssefUpd2 = updateStudent(youssefBadran);

        //testing if a student can be created with invalid email
        createStudent(new Student("Test", "Testing", "test@dk", 20));

    }

    @PrePersist
    //- This method should create a new student and persist it to the database.
    public static void createStudent(Student student){
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            // entity is in managed state (after persist)
            em.persist(student);
            // entity is in detached state after the transaction is committed
            em.getTransaction().commit();
        }
    }

    //This method should read a student from the database using the student's id.
    public static Student readStudent(int id){
        try(var em = emf.createEntityManager()){
            //Entity is managed after being retrieved
            Student foundStudent = em.find(Student.class, id);
            // entity is detached after the entitry is returned
            return foundStudent;
        }
    }

    @PreUpdate
    //This method should update an existing student in the database
    public static Student updateStudent(Student updStd){
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            //The entity is managed after the merge
            Student updatedStudent = em.merge(updStd);
            // entity is in stransient state (after being retrieved)
            em.getTransaction().commit();
            //entitry is detached after it is returned
            return updatedStudent;
        }
    }

    public static void deleteStudent(int id){
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            // the entity is managed after it is found/retrieved
            Student student = readStudent(id);
            if (student != null) {
                em.remove(student);
            } else {
                System.out.println("The student you are looking for does not exist");
            }
            em.getTransaction().commit();
        }
    }

    public static List<Student> readAllStudents (){
        try(var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            //All entities are managed after the query is created
            var query = em.createQuery("SELECT s FROM Student s", Student.class);
            //All entities are detached once the list is returned
            return query.getResultList();
        }
    }

}

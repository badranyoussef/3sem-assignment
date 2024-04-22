package assignment03.dao;

import assignment02.model.Driver;
import assignment03.config.HibernateConfig;
import assignment03.model.Semester;
import assignment03.model.Student;
import assignment03.model.StudentInfo;
import assignment03.model.Teacher;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class StudentDAOImpl implements IStudentDAO{
    private EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig(false);

    @Override
    public List<Student> findAllStudentsByFirstName(String firstName) {
        try(var em = emf.createEntityManager()){
            //TypedQuery<Student> query = em.createTypedQuery("SELECT s FROM Student s WHERE s.firstName = :name", Student.class);
            Query query = em.createQuery("SELECT s FROM Student s WHERE s.firstName = :name");
            query.setParameter("name", firstName);
            return query.getResultList();
        }
    }

    @Override
    public List<Student> findAllStudentsByLastName(String lastName) {
        try(var em = emf.createEntityManager()){
            //TypedQuery<Student> query = em.createTypedQuery("SELECT s FROM Student s WHERE s.firstName = :name", Student.class);
            Query query = em.createQuery("SELECT s FROM Student s WHERE s.lastName = :name");
            query.setParameter("name", lastName);
            return query.getResultList();
        }
    }

    @Override
    public long findTotalNumberOfStudentsBySemester(String semesterName) {
        try (var em = emf.createEntityManager()) {
            String jpql = "SELECT COUNT(s) FROM Student s WHERE s.semester.name = :semesterName";
            TypedQuery<Long> query = em.createQuery(jpql, Long.class);
            query.setParameter("semesterName", semesterName);
            long numberOfStudents = query.getSingleResult();
            return numberOfStudents;
        }
    }

    @Override
    public long findTotalNumberOfStudentsByTeacher(Teacher teacher) {
        try (var em = emf.createEntityManager()) {
            // JPQL-forespørgsel, der tæller det samlede antal unikke studerende for en given lærer
            String jpql = "SELECT COUNT(DISTINCT s) FROM Student s JOIN s.semester sem JOIN sem.teachers t WHERE t = :teacher";
            TypedQuery<Long> query = em.createQuery(jpql, Long.class);
            query.setParameter("teacher", teacher);
            return query.getSingleResult();
        }
    }

    @Override
    public Teacher findTeacherWithMostSemesters() {
        try (var em = emf.createEntityManager()) {
            String jpql = "SELECT t FROM Teacher t JOIN t.semesters s GROUP BY t ORDER BY COUNT(s) DESC";
            TypedQuery<Teacher> query = em.createQuery(jpql, Teacher.class);
            List<Teacher> results = query.getResultList();
            return results.isEmpty() ? null : results.get(0); // Returnerer den første lærer, hvis listen ikke er tom
        }
    }

    @Override
    public Semester findSemesterWithFewestStudents(){
        try (var em = emf.createEntityManager()) {
            // JPQL-forespørgsel, der finder semesteret med det laveste antal studerende
            String jpql = "SELECT s FROM Semester s LEFT JOIN s.students st GROUP BY s ORDER BY COUNT(st) ASC";
            TypedQuery<Semester> query = em.createQuery(jpql, Semester.class);
            query.setMaxResults(1); // Begrænser resultatet til kun 1 semester
            return query.getSingleResult();
        }
    }

    @Override
    public StudentInfo getAllStudentInfo(int id) {
        try (var em = emf.createEntityManager()) {
            String jpql = "SELECT new assignment03.model.StudentInfo(CONCAT(s.firstName, ' ', s.lastName), s.id, sem.name, sem.description) FROM Student s JOIN s.semester sem WHERE s.id = :id";
            TypedQuery<StudentInfo> query = em.createQuery(jpql, StudentInfo.class);
            query.setParameter("id", id);
            return query.getSingleResult();
        }
    }
}

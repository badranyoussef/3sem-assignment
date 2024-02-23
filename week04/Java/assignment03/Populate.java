package assignment03;

import assignment03.config.HibernateConfig;
import assignment03.dao.StudentDAOImpl;
import assignment03.model.Semester;
import assignment03.model.Student;
import assignment03.model.StudentInfo;
import assignment03.model.Teacher;
import jakarta.persistence.EntityManagerFactory;

public class Populate {
    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig(false);
                StudentDAOImpl studentDAO = new StudentDAOImpl();

//        try (var em = emf.createEntityManager()){
//            em.getTransaction().begin();
//
//            // Opretter nogle eksempeldata
//
//            Student student1 = new Student("Alice", "Johnson");
//            Student student2 = new Student("Bob", "Smith");
//            Student student3 = new Student("Charlie", "Davis");
//            Student student4 = new Student("Diana", "Evans");
//            Student student5 = new Student("Ethan", "Foster");
//
//            Teacher teacher1 = new Teacher("Jörg", "Oertel");
//            Teacher teacher2 = new Teacher("Karen", "White");
//            Teacher teacher3 = new Teacher("Liam", "Green");
//
//            Semester semester3 = new Semester("3. Sem", "JPA, JDD, JPQL");
//            Semester semester4 = new Semester("4. Sem", "Advanced Java, Security");
//            Semester semester5 = new Semester("5. Sem", "Project Management, Agile Methods");
//
//            // Opretter relationer
//            semester3.addStudent(student1);
//            semester3.addStudent(student2);
//            semester4.addStudent(student3);
//            semester4.addStudent(student4);
//            semester5.addStudent(student5);
//            semester3.addTeacher(teacher1);
//            semester4.addTeacher(teacher2);
//            semester4.addTeacher(teacher1);
//            semester5.addTeacher(teacher3);
//
//            // Persisterer objekterne i databasen
//            em.persist(semester3);
//            em.persist(semester4);
//            em.persist(semester5);
//
//            // Committer transaktionen
//            em.getTransaction().commit();
//        }

        Teacher jorg;

        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            jorg = em.find(Teacher.class, 1);
        }

//        studentDAO.findAllStudentsByFirstName("Bob").forEach(System.out::println);
//
//        System.out.println(studentDAO.findTotalNumberOfStudentsBySemester("3. Sem"));
//
//        System.out.println(studentDAO.findTotalNumberOfStudentsByTeacher(jorg));

//        System.out.println(studentDAO.findTeacherWithMostSemesters());
        System.out.println(studentDAO.findSemesterWithFewestStudents());

        StudentInfo studentInfo = studentDAO.getAllStudentInfo(1);

        System.out.println(studentInfo);


    }

}

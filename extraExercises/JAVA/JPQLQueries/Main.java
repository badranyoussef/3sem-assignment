package JPQLQueries;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
        EntityManager em = emf.createEntityManager();

        //Write a JPQL query to select all employees.
        Query q1 = em.createQuery("SELECT e FROM Employee e");
        //List<Employee> employees = q1.getResultList();
        //employees.stream().forEach(System.out::println);

        //Write a JPQL query to select employees with a salary greater than a certain value.
        //Query q2 = em.createQuery("SELECT e FROM Employee e Where salary > 60000");
        //List<Employee> employees = q2.getResultList();
        //employees.stream().forEach(System.out::println);

        //Write a JPQL query to select employees from a specific department.
        //Query q2 = em.createQuery("SELECT e FROM Employee e Where department = 'IT'");
        //List<Employee> employees = q2.getResultList();
        //employees.stream().forEach(System.out::println);

        //Write a JPQL query to select employees whose first name starts with a certain letter.
        Query q3 = em.createQuery("SELECT e FROM Employee e Where e.firstName LIKE 'A%' ");
        List<Employee> employees3 = q3.getResultList();
        employees3.stream().forEach(System.out::println);

        //Write a JPQL query to update the salary of an employee using a named parameter.
        em.getTransaction().begin();
        Query q4 = em.createQuery("UPDATE Employee e SET e.salary = 500 WHERE e.id = 5");
        q4.executeUpdate();
        em.getTransaction().commit();

        //Write a JPQL query to update the department of an employee using positional parameters.
                //Name query
        Query queryNamed = em.createQuery("SELECT e FROM Employee e WHERE e.firstName = :first and e.lastName = :last");
        queryNamed.setParameter("first", "John");
        queryNamed.setParameter("last", "Doe");
        List<Employee> list = queryNamed.getResultList();
        list.stream().forEach(System.out::println);

                //positional parameters
        Query queryPositional = em.createQuery("SELECT e FROM Employee e WHERE e.firstName = ?1 and e.lastName = ?2");
        queryPositional.setParameter(1, "John");
        queryPositional.setParameter(2, "Doe");
        List<Employee> list2 = queryPositional.getResultList();
        list2.stream().forEach(System.out::println);

        //Write a JPQL query to calculate the average salary of all employees.
        Query queryAvg = em.createQuery("SELECT AVG(e.salary) FROM Employee e");
        double avg = (double) queryAvg.getSingleResult();
        System.out.println(avg);

        //Write a JPQL query to calculate the total salary of all employees.
        Query querySum = em.createQuery("SELECT SUM(e.salary) FROM Employee e");
        long sum = (long) querySum.getSingleResult();
        System.out.println(sum);

    }

}

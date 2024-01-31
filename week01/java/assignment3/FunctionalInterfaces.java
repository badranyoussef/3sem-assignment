package assignment3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class FunctionalInterfaces {


    public static List<Employee> createListOfEmployees(int numberOfEmployees, Supplier<Employee> supplier){
        List<Employee> employees = new ArrayList<>();
        for (int i = 0; i < numberOfEmployees; i++) {
            employees.add(supplier.get());
        }
        return employees;
    }

    public static Employee getARandomName(List<String> employeeNames){
        Random random = new Random();
        int randomIndex = random.nextInt(employeeNames.size());
        String randomName = employeeNames.get(randomIndex);
        return new Employee(randomName);
    }

}

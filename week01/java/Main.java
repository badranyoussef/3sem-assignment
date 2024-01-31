import assignment1.LambdaExpressions;
import assignment2.FunctionalProgramming;
import assignment2.*;
import assignment3.Employee;
import assignment3.FunctionalInterfaces;
import assignment4.Time;
import assignment5.MethodReferences;
import utility.Print;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;


public class Main {

    public static void main(String[] args) {

        //exercise 1.1
        Print.text("Exercise 1.1");
        Print.result(LambdaExpressions.addition().perform(5, 5));
        Print.result(LambdaExpressions.subtraction().perform(10, 5));
        Print.result(LambdaExpressions.multiplication().perform(5, 5));
        Print.result(LambdaExpressions.division().perform(5, 5));
        Print.result(LambdaExpressions.modulus().perform(5, 5));
        Print.result(LambdaExpressions.power().perform(2, 2));
        Print.aBreak();

        //exercise 1.2
        Print.text("Exercise 1.2");
        int[] a = {10, 20, 30, 40, 50};
        int[] b = {2, 16, 8, 3, 2};
        int[] resultOfArrays = LambdaExpressions.adttitionOfArrays(a, b, LambdaExpressions.addition());

        for (int i : resultOfArrays) {
            Print.result(i);
        }
        Print.aBreak();

        //exercise 2.1
        Print.text("Exercise 2.1");

        int[] arrayOfIntegers = {1, 2, 3, 6, 8, 9, 10, 11, 12};

        int[] arrayOfValueMultiplyedByItSelf = FunctionalProgramming.map(arrayOfIntegers, number -> number * number);
        //eller
        MyTransformingType tranform = (int number) -> number * number;
        int[] arrayOfValueMultiplyedByItSelf2 = FunctionalProgramming.map(arrayOfIntegers, tranform);

        Print.aBreak();

        //exercise 2.2
        Print.text("Exercise 2.2");

        MyValidatingType validate = (int number) -> number % 3 == 0;

        int[] filteredArray = FunctionalProgramming.filter(arrayOfIntegers, validate);
        //eller
        int[] filteredArray2 = FunctionalProgramming.filter(arrayOfIntegers, (int number) -> number % 3 == 0);

        for (int i : filteredArray) {
            System.out.println(i);
        }

        Print.aBreak();


        //exercise 3.1
        Print.text("Exercise 3.1");

        List<Integer> numbers = List.of(3, 5, 6, 7, 8, 10, 14, 21, 22, 24, 28, 29, 91);
        Predicate<Integer> isDivisibleBySeven = number -> number % 7 == 0;
        List<Integer> divisibleBySeven = numbers.stream().filter(isDivisibleBySeven).collect(Collectors.toList());

        for (int i : divisibleBySeven) {
            System.out.println(i);
        }

        Print.aBreak();


        //exercise 3.2
        Print.text("Exercise 3.2");
        List<String> employeeNames = List.of("Thomas", "Hanne", "Louise", "Aksel", "Hans", "Rune", "Mona", "John", "Jane", "Jack", "Joe", "Jill");

        Supplier<Employee> employee = () -> {
            Random random = new Random();
            int randomIndex = random.nextInt(employeeNames.size());
            String randomName = employeeNames.get(randomIndex);
            return new Employee(randomName);
        };
        //eller
        Supplier<Employee> employee2 = () -> FunctionalInterfaces.getARandomName(employeeNames);

        List<Employee> employeeList = FunctionalInterfaces.createListOfEmployees(8, employee2);
        //employeeList.forEach(System.out::println);

        Print.aBreak();

        //exercise 3.3
        Print.text("Exercise 3.3");

        // Consumer bruges her til at printer hvert "element" i listen
        Consumer<String> printElement = element -> System.out.println(element);
        employeeNames.forEach(printElement);

        Print.aBreak();


        //exercise 3.4
        Print.text("Exercise 3.4");

        // Bruger Funktioner til at få en specifik data type ud af et objekt.
        // Her giver jeg et Employee objekt og får et String output
        Function<Employee, String> employeeName = emp -> emp.getName();

        // For at printe navnene for hver Employee i listen
        employeeList.stream() // Konverteres listen til en Stream
                .map(employeeName) // Brug funktionen til at mappe hver Employee til dets navn
                .forEach(System.out::println); // utility.Print hvert navn

        Print.aBreak();


        //exercise 3.5
        Print.text("Exercise 3.5");

        List<Employee> employeeList2 = List.of(
                new Employee("Thomas", 30),
                new Employee("Ahmad", 25),
                new Employee("Lasse", 40),
                new Employee("Mohammad", 35),
                new Employee("Sandra", 10),
                new Employee("Rune", 5)
        );

        Predicate<Employee> isOver18 = Employee -> Employee.getAge() > 18;
        System.out.println(isOver18.test(employeeList2.get(4)));

        Print.aBreak();


        //exercise 4.1
        Print.text("Exercise 4.1");

        List<Employee> employees = List.of(
                new Employee("Thomas", 1958, 5, 6),
                new Employee("Hanne", 1973, 6, 14),
                new Employee("Louise", 1968, 6, 26),
                new Employee("Aksel", 1977, 5, 23),
                new Employee("Hans", 1959, 10, 1),
                new Employee("Rune", 1976, 6, 14),
                new Employee("Mona", 1963, 4, 24),
                new Employee("John", 1971, 4, 9),
                new Employee("Jane", 1998, 12, 5),
                new Employee("Jack", 1962, 10, 17),
                new Employee("Joe", 1993, 8, 22),
                new Employee("Jill", 1973, 7, 18)
    );

        LocalDate currentDate = LocalDate.now();
        employees.stream().map(Employee -> Time.calculateAge(Employee, currentDate)).forEach(System.out::println);

        //ex til brug i 4.2
        List<Integer> ages = employees.stream().map(Employee -> Time.calculateAge(Employee, currentDate)).toList();

        Print.aBreak();

        //exercise 4.2
        Print.text("Exercise 4.2");

        Double averageAge = employees.stream().mapToInt(Employee -> Time.calculateAge(Employee, currentDate)).average().orElse(0.0);
        System.out.println(averageAge);

        //med ex fra 4.1
        // OptionalDouble er et "container objekt" som kan indeholde en double værdi eller være tom. Således undegår man null poiter exception og undlader brugen af metoden orElse() på average().
        // så kan man håndtere det med et if statement hvis man vil sikre at der er en værdi og ikke 0
        OptionalDouble averagesAges = ages.stream().mapToInt(age -> age).average();

        System.out.println(averagesAges);

        Print.aBreak();

        //exercise 4.3
        Print.text("Exercise 4.3");

        employees.stream().filter(Employee -> Employee.getAge() < 1990).map(Employee -> Employee.getName()).forEach(name -> System.out.println("name: " + name));

        Print.aBreak();

        //exercise 4.4
        Print.text("Exercise 4.4");

        Map<Integer, Integer > mapOfEmployees = new HashMap<>();

        for (int i = 1; i <= 12; i++ ){
            mapOfEmployees.put(i,0);
        }

        //employees.stream().forEach(Employee -> );

        //int month = 7;
        //employees.stream().filter(Employee -> Employee.getMonthOfBirth() == month).map(Employee::toString).forEach(System.out::println);



        Map<Integer, List<Employee>>mapOfEmployees2 = employees.stream().collect(Collectors.groupingBy(Employee::getMonthOfBirth));

        mapOfEmployees2.forEach((monthX, count) -> {
            System.out.println("month: "+monthX + " Number of employees: " + count.size());
        });

        //eller



        Print.aBreak();

        //exercise 4.5
        Print.text("Exercise x.x");

        LocalDate date = LocalDate.now();
        int currentMonth = date.getMonthValue();

        employees.stream().filter(Employee -> Employee.getMonthOfBirth() == currentMonth).map(Employee::toString);

        Print.aBreak();

        //exercise 5.0
        Print.text("Exercise 5.0");

        int[] arrayOfInts ={2,4,6,8,10};
        int[] doubledValue = FunctionalProgramming.map(arrayOfInts, MethodReferences::doubleTheValue);

        //printing the values
        for (int i : doubledValue) {
            System.out.println(i);
        }
        Print.aBreak();

        //exercise 6.1.
        Print.text("Exercise x.x");
        Print.aBreak();


        //exercise 6.1.
        Print.text("Exercise x.x");
        Print.aBreak();

        //exercise 6.1.
        Print.text("Exercise x.x");
        Print.aBreak();


        //exercise 6.1.
        Print.text("Exercise x.x");
        Print.aBreak();


        //exercise 6.1.
        Print.text("Exercise x.x");
        Print.aBreak();

        //exercise 6.1.
        Print.text("Exercise x.x");
        Print.aBreak();

        //exercise 6.1.
        Print.text("Exercise x.x");
        Print.aBreak();

        //exercise 6.1.
        Print.text("Exercise x.x");
        Print.aBreak();

        //exercise 6.1.
        Print.text("Exercise x.x");
        Print.aBreak();

        //exercise 6.1.
        Print.text("Exercise x.x");
        Print.aBreak();



    }
}

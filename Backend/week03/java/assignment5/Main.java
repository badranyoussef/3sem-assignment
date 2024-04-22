package assignment5;

import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        Person person1 = new Person("John", "Doe", 25);
        System.out.println(person1); // This should print something like "Person(firstName=John, lastName=Doe, age=25)"

        person1.setAge(26);
        System.out.println(person1.getAge()); // This should print "26"

        //her benyttes builder() metoden til at instantiere en person
        Person person2 = Person.builder()
                .firstName("Youssef")
                .lastName("Badran")
                .age(30)
                .build();
        System.out.println(person2);

        //her benyttes @NoArgsConstructor og setter metoder til at instantiere en Person
        Person person3 = new Person();
        person3.setFirstName("John");
        person3.setLastName("Doe");
        person3.setAge(30);

        //Benytter equals metoden til at tjekke om personerne er ens
        boolean areEqual = person1.equals(person2);

        System.out.println("Are person1 and person2 equal? " + areEqual);

        //Anvender person1 i en HashSet
        HashSet<Person> setOfPersons = new HashSet<>();
        setOfPersons.add(person1);
        setOfPersons.add(person2);
        setOfPersons.add(person3);

        // Benytter metoden contains på HashSet'et til at tjekke om et objekt findes i sette
        boolean contains = setOfPersons.contains(person2);


        System.out.println("Does setOfPersons contain an object equal to person2? " + contains);

        //Af nysgerrighed printer jeg objekternes hashcode :D
        System.out.println("HashCode for person1: " + person1.hashCode());
        System.out.println("HashCode for person2: " + person2.hashCode());
        System.out.println("HashCode for person3: " + person3.hashCode());

    }
}

package assignment3;

import java.time.LocalDate;

public class Employee {
    private String name;
    private int yearOfBirth;
    private int monthOfBirth;
    private int dayOfBirth;
    private LocalDate dateOfBirth;
    private int age;


    public Employee(String name, int yearOfBirth, int monthOfBirth, int dayOfBirth) {
        this.name = name;
        this.yearOfBirth = yearOfBirth;
        this.monthOfBirth = monthOfBirth;
        this.dayOfBirth = dayOfBirth;
        this.dateOfBirth = LocalDate.of(yearOfBirth, monthOfBirth, dayOfBirth);
    }

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }


    public Employee(String randomName) {
        this.name = randomName;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public int getMonthOfBirth() {
        return monthOfBirth;
    }

    public int getDayOfBirth() {
        return dayOfBirth;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    @Override
    public String toString() {
        return "Name: " + name + " - birthdate (Y/M/D):" + yearOfBirth + "/" + monthOfBirth + "/"+ dayOfBirth;
    }
}

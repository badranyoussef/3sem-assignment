package assignment4;

import assignment3.Employee;

import java.time.LocalDate;
import java.time.Period;

public class Time {

    public static int calculateAge(Employee e, LocalDate currentDate){

        LocalDate birthYear = e.getDateOfBirth();
        Period age = Period.between(birthYear, currentDate);

        return age.getYears();
    }

}

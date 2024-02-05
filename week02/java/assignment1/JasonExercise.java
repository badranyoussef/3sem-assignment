package assignment1;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jetbrains.annotations.NotNull;

import java.io.FileReader;
import java.util.*;

public class JasonExercise {

    static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void main(String[] args) {

        Customer[] arr = getCustomers();

        for (Customer c : arr) {
            System.out.println(c.firstName + " " + c.lastName);
        }


        CustomerDTO[] arr2 = getCustomersDTO();

        for (CustomerDTO c : arr2) {
            System.out.println(c.getFullName());
        }


    }

    class Customer {
        String firstName;
        String lastName;
        String birthDate;
        Address address;
        Account account;

        public Account getAccount() {
            return account;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getBirthDate() {
            return birthDate;
        }

        public Address getAdress() {
            return address;
        }
    }

    class Address {
        String street;
        String city;
        int zipCode;

        public String getCity() {
            return city;
        }

        public int getZipCode() {
            return zipCode;
        }

        public String getStreet() {
            return street;
        }
    }

    class Account {
        String id;
        String balance;
        boolean isActive;

        public String getId() {
            return id;
        }

        public String getBalance() {
            return balance;
        }

        public Boolean getActive() {
            return isActive;
        }
    }

    static class CustomerDTO {
        String fullName;
        String city;
        int zipCode;
        boolean isActive;

        public CustomerDTO(String fullName, String city, int zipCode, boolean isActive) {
        }

        public String getFullName() {
            return fullName;
        }

        public String getCity() {
            return city;
        }

        public int getZipCode() {
            return zipCode;
        }

        public boolean getIsActive() {
            return isActive;
        }
    }

    public static Customer[] getCustomers() {
        Customer[] arr = null;

        try (FileReader reader = new FileReader("/Users/youssefbadran/Documents/GitHub/3sem-assignments/week02/java/assignment1/account.json")) {
            arr = gson.fromJson(reader, Customer[].class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return arr;
    }

    public static CustomerDTO[] getCustomersDTO() {
        CustomerDTO[] arr = null;
        List<CustomerDTO> listDTO = new ArrayList<>();

        try (FileReader reader = new FileReader("/Users/youssefbadran/Documents/GitHub/3sem-assignments/week02/java/assignment1/account.json")) {
            Customer[] customer = gson.fromJson(reader, Customer[].class);

            for (Customer c : customer) {
                String fullName = c.getFirstName() + " " + c.getLastName();
                String city = c.getAdress().getCity();
                int zipCode = c.getAdress().zipCode;
                boolean isAtive = c.getAccount().isActive;
                CustomerDTO dto = new CustomerDTO(fullName, city, zipCode, isAtive);
                listDTO.add(dto);
            }
        } catch (
                Exception e) {
            throw new RuntimeException(e);
        }
        return arr = listDTO.toArray(CustomerDTO[]::new);
    }

}

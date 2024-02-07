package assignment2;


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

        System.out.println(System.lineSeparator());

        CustomerDTO[] arr2 = getCustomersDTO();

        for (CustomerDTO c : arr2) {
            System.out.println(c.getFullName());
        }

        System.out.println(System.lineSeparator());

        CustomerDTO[] arrDTO = arryCustomerToArryDTO(arr);

        for (CustomerDTO c:arrDTO) {
            System.out.println(c);
        }

    }

    class Customer {
        private String firstName;
        private String lastName;
        private String birthDate;
        private Address address;
        private Account account;

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
        private String street;
        private String city;
        private int zipCode;

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
        private String id;
        private String balance;
        private boolean isActive;

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
        private String fullName;
        private String city;
        private int zipCode;
        private boolean isActive;

        public CustomerDTO(String fullName, String city, int zipCode, boolean isActive) {
            this.fullName = fullName;
            this.city = city;
            this.zipCode = zipCode;
            this.isActive = isActive;
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

        @Override
        public String toString() {
            return "CustomerDTO (" +
                    "Full Name = " + fullName +
                    ", City = " + city +
                    ", zipCode = " + zipCode +
                    ", Active Account = " + (isActive ? "Yes" : "No") +
                    ')';
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


    public static CustomerDTO convertCustomerToDTO(Customer c){
        String fullName = c.getFirstName() + " " + c.getLastName();
        CustomerDTO dto = new CustomerDTO(fullName, c.getAdress().getCity(), c.getAdress().zipCode, c.getAccount().getActive());

        return dto;
    }


    public static CustomerDTO[] getCustomersDTO() {
        CustomerDTO[] arr = null;
        List<CustomerDTO> listDTO = new ArrayList<>();

        try (FileReader reader = new FileReader("/Users/youssefbadran/Documents/GitHub/3sem-assignments/week02/java/assignment1/account.json")) {
            Customer[] customer = gson.fromJson(reader, Customer[].class);

            for (Customer c : customer) {
                CustomerDTO dto = convertCustomerToDTO(c);
                listDTO.add(dto);
            }
        } catch (
                Exception e) {
            throw new RuntimeException(e);
        }
        return arr = listDTO.toArray(CustomerDTO[]::new); // <- Class reference
    }


    public static CustomerDTO[] arryCustomerToArryDTO(Customer[] arrayCustomer){
        CustomerDTO[] arrayOfDTO = new CustomerDTO[arrayCustomer.length];
        String fullname = null;

        for (int i = 0; i < arrayCustomer.length; i++) {
            Customer c = arrayCustomer[i];
            fullname = c.getFirstName() + " " + c.getLastName();
            arrayOfDTO[i] = new CustomerDTO(fullname, c.getAdress().city, c.getAdress().getZipCode(), c.getAccount().getActive());
        }
        return arrayOfDTO;
    }

}

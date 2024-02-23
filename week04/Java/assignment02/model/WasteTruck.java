package assignment02.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "truck")
public class WasteTruck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "brand", length = 255, nullable = false)
    private String brand;
    @Column(name = "capacity", nullable = false)
    private int capacity;
    @Column(name = "is_available", nullable = false)
    private boolean isAvailable;
    @Column(name = "registration_number", length = 255, nullable = false)
    private String registrationNumber;

    public WasteTruck(String brand, int capacity, String registrationNumber) {
        this.brand = brand;
        this.capacity = capacity;
        this.registrationNumber = registrationNumber;
    }

    @Setter
    @OneToMany(mappedBy = "truck", fetch = FetchType.EAGER)
    private Set<Driver> drivers = new HashSet<>();

    public void addDriver(Driver driver){
        if(driver != null){
            drivers.add(driver);
            driver.setTruck(this); // Opdaterer 'truck' feltet i Driver entiteten
        }
    }

    @Override
    public String toString(){
        return "Brand: " + brand + " - Registration nr: " + registrationNumber + " - Capacity: " + capacity;
    }
}

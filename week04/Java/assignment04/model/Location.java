package assignment04.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    private double Latitude;
    private double Longitude;
    private String Address;

    @ManyToOne
    Package Package;

    @ManyToMany
    Set<Shipment> shipments = new HashSet<>();

}
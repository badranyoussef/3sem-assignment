package assignment04.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "shipment")
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Package aPackage;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Location sourceLocation;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Location destinationLocation;

    @Temporal(TemporalType.TIMESTAMP)
    private Date shipmentDateTime;
}
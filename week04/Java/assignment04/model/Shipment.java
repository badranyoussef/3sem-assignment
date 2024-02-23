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
    @Column(name = "id", nullable = false)
    private Integer id;
    @ManyToOne
    Package Package;
    @ManyToOne
    Location sourceLocation;
    @ManyToOne
    Location destinationLocation;
    @Temporal(TemporalType.TIMESTAMP)
    private Date shipmentDateTime;

}
package assignment04.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Entity
@Table(name="packages")
@Getter
@Setter
@ToString
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String trackingNumber;
    private String senderName;
    private String receiverName;

    public Package(String trackingNumber, String senderName, String receiverName) {
        this.trackingNumber = trackingNumber;
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.status = status;
    }

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdated = new Date();

    @OneToMany(mappedBy = "aPackage", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Shipment> shipments = new HashSet<>();

    @PrePersist
    private void updateDeliveryStatus() {
        setStatus(DeliveryStatus.PENDING);
        lastUpdated.getTime();
    }

    @PreUpdate
    private void updateTimestamp() {
        lastUpdated = new Date();
    }

    public void addShipment(Shipment shipment, Location destination, Location sourceDestination) {
        if (shipment != null) {
            shipments.add(shipment);
            shipment.setAPackage(this);
            shipment.setSourceLocation(sourceDestination);
            shipment.setDestinationLocation(destination);
        }

    }

    public void removeShipment(Shipment shipment) {
        if (shipment != null) {
            shipments.remove(shipment);
            shipment.setAPackage(null);
            shipment.setSourceLocation(null);
            shipment.setDestinationLocation(null);
        }
    }
}

enum DeliveryStatus {
    PENDING, IN_TRANSIT, DELIVERED
}
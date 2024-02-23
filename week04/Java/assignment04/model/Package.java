package assignment04.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "tracking_nr", nullable = false)
    private String trackingNumber;
    @Column(name = "sender_name", nullable = false)
    private String senderName;
    @Column(name = "receiver_name", nullable = false)
    private String receiverName;

    @OneToMany
    Set<Location> loactions = new HashSet<>();
    @OneToMany
    Set<Shipment> shipments = new HashSet<>();


    @Enumerated(EnumType.STRING) // Du kan bruge EnumType.STRING direkte her
    @Column(name = "delivery_status", nullable = false)
    private DeliveryStatus deliveryStatus;
    public enum DeliveryStatus{
        PENDING,
        IN_TRANSIT,
        DELIVERED
    }
}

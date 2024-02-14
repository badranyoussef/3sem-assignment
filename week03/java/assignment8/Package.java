package assignment8;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import static jakarta.persistence.EnumType.STRING;

@NoArgsConstructor
@Entity
@Table(name="gls_packages")
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

    @Enumerated(EnumType.STRING) // Du kan bruge EnumType.STRING direkte her
    @Column(name = "delivery_status", nullable = false)
    private DeliveryStatus deliveryStatus;
    public enum DeliveryStatus{
        PENDING,
        IN_TRANSIT,
        DELIVERED
    }
}

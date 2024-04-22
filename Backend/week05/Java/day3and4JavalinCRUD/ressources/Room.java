package day3and4JavalinCRUD.ressources;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "rooms")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    @JsonBackReference
    private Hotel hotel;

    private int number;
    private double price;

    public Room(int number, double price) {
        this.number = number;
        this.price = price;
    }

    @Override
    public String toString() {
        return "id: " + id + " - Room number: " + number + " - Price: " + price;
    }
}

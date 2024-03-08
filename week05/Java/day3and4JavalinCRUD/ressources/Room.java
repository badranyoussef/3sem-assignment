package day3and4JavalinCRUD.ressources;

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
    private Hotel hotel;
    private int number;
    private double price;

    public Room(int id, int number, double price) {
        this.id = id;
        this.number = number;
        this.price = price;
    }

    @Override
    public String toString() {
        return "id: " + id + " - Room number: " + number + " - Price: " + price;
    }
}

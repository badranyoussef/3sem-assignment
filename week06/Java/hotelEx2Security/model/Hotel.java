package hotelEx2Security.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hotels")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String address;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hotel", fetch = FetchType.EAGER)
    @JsonManagedReference
    List<Room> rooms = new ArrayList<>();

    public Hotel(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public void addRoom(Room room){
        if(room != null){
            rooms.add(room);
            room.setHotel(this);
        }
    }

    @Override
    public String toString() {
        return "id: " + id + " - HotelName: " + name + " - address: " + address;
    }
}

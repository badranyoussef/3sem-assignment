package day3and4JavalinCRUD.dto;

import day3and4JavalinCRUD.ressources.Room;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class HotelDto {
    private int id;
    private String name;
    private String address;
    private int rooms;

}
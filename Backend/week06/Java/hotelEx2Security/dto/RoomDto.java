package hotelEx2Security.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class RoomDto{
    private int id;
    private int hotelId;
    private int number;
    private double price;
}
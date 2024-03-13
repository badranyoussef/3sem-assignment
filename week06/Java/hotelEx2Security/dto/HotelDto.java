package hotelEx2Security.dto;

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
    private List rooms;

}
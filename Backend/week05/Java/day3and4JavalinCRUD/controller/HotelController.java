package day3and4JavalinCRUD.controller;

import day3and4JavalinCRUD.dao.HotelDAO;
import day3and4JavalinCRUD.dto.HotelDto;
import day3and4JavalinCRUD.dto.RoomDto;
import day3and4JavalinCRUD.ressources.Hotel;
import day3and4JavalinCRUD.ressources.Room;
import io.javalin.http.Handler;
import jakarta.transaction.Transactional;

import java.util.Arrays;
import java.util.List;


public class HotelController {

    @Transactional
    public static Handler getAll(HotelDAO dao) {
        return ctx -> {
            if (dao.getAll().isEmpty()) {
                ctx.status(404).result("No Hotels available");
            } else {
                List<HotelDto> list = dao.getAll().stream().map(hotel -> HotelDto.builder()
                        .id(hotel.getId())
                        .name(hotel.getName())
                        .address(hotel.getAddress())
                        .rooms(Arrays.asList(hotel.getRooms().toArray()))
                        .build()).toList();
                ctx.json(list);
            }
        };
    }

    public static Handler getHotel(HotelDAO dao) {
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            if (dao.getById(id) != null) {
                Hotel h = dao.getById(id);
                HotelDto dto = HotelDto.builder()
                        .id(h.getId())
                        .name(h.getName())
                        .address(h.getAddress())
                        .build();
                ctx.json(dto);
            } else {
                ctx.status(404).result("The id you are looking for does not exist");
            }
        };
    }

    public static Handler create(HotelDAO dao) {
        return ctx -> {
            Hotel hotel = ctx.bodyAsClass(Hotel.class);
            if (hotel != null) {
                dao.create(hotel);
                ctx.json(hotel);
            } else {
                ctx.status(500).result("Received data was incorrect... Something went wrong. Check create method in controller");
            }
        };
    }

    public static Handler delete(HotelDAO dao) {
        return ctx -> {
            Hotel foundHotel = dao.getById(Integer.parseInt(ctx.pathParam("id")));
            if (foundHotel != null) {
                dao.delete(foundHotel.getId());
                ctx.status(204).result("Hotel successfully deleted");
            } else {
                ctx.status(500).result("Hotel was not found");
            }
        };
    }

    public static Handler update(HotelDAO dao) {
        return ctx -> {
            //retrieving data
            int id = Integer.parseInt(ctx.pathParam("id"));
            //instantiating a dto by the data received.
            HotelDto dto = ctx.bodyAsClass(HotelDto.class);
            dto.setId(id);
            //retreiving Hotel by ID
            Hotel h = dao.getById(id);
            //Updating the name which where received
            h.setName(dto.getName());
            h.setAddress(dto.getAddress());
            //updating database
            dao.update(h);
            ctx.json(dto);
        };
    }


    public static Handler getHotelRooms(HotelDAO hDAO) {
        return ctx -> {
          int id = Integer.parseInt(ctx.pathParam("id"));
            Hotel hotel = hDAO.getById(id);
            if (hotel != null) {
                List<Room> rooms = hotel.getRooms();
                if (rooms.isEmpty()) {
                    ctx.status(404).result("No rooms available for this hotel");
                } else {
                    List<RoomDto> roomDtos = rooms.stream().map(room -> RoomDto.builder()
                            .id(room.getId())
                            .number(room.getNumber())
                            .price(room.getPrice())
                            .hotelId(room.getHotel().getId())
                            .build()).toList();
                    ctx.json(roomDtos);
                }
            } else {
                ctx.status(404).result("Hotel not found");
            }
        };
    }
}

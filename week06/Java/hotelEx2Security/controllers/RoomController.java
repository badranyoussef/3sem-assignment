package hotelEx2Security.controllers;

import hotelEx2Security.daos.HotelDAO;
import hotelEx2Security.daos.RoomDAO;
import hotelEx2Security.dto.RoomDto;
import hotelEx2Security.model.Hotel;
import hotelEx2Security.model.Room;
import io.javalin.http.Handler;

import java.util.List;

public class RoomController {

    public static Handler getAll(RoomDAO dao) {
        return ctx -> {
            if (dao.getAll().isEmpty()) {
                ctx.status(404).result("No Room available");
            } else {
                List<RoomDto> list = dao.getAll().stream().map(room -> RoomDto.builder()
                                .id(room.getId())
                                .hotelId(room.getHotel().getId())
                                .number(room.getNumber())
                                .price(room.getPrice())
                                .build())
                        .toList();
                ctx.json(list);
            }
        };
    }

    public static Handler getRoom(RoomDAO dao) {
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            if (dao.getById(id) != null) {
                Room r = dao.getById(id);
                RoomDto dto = RoomDto.builder()
                        .id(r.getId())
                        .hotelId(r.getHotel().getId())
                        .price(r.getPrice())
                        .number(r.getNumber())
                        .build();
                ctx.json(dto);
            } else {
                ctx.status(404).result("The id you are looking for does not exist");
            }
        };
    }

    public static Handler create(RoomDAO dao, HotelDAO hDao) {
        return ctx -> {
            int hotelId = Integer.parseInt(ctx.pathParam("hotelId"));
            Room room = ctx.bodyAsClass(Room.class);
            Hotel hotel = hDao.getById(hotelId);
            if (hotel != null) {
                hotel.addRoom(room); // Tilknytter værelset til hotellet og omvendt
                hDao.update(hotel); // Gem ændringerne (inklusive det nye værelse)
                ctx.status(201).json(RoomDto.builder()
                        .id(room.getId())
                        .hotelId(room.getHotel().getId())
                        .number(room.getNumber())
                        .price(room.getPrice())
                        .build());
            } else {
                ctx.status(404).result("Hotel not found");
            }
        };
    }

    public static Handler delete(RoomDAO dao) {
        return ctx -> {
            Room foundRoom = dao.getById(Integer.parseInt(ctx.pathParam("id")));
            if (foundRoom != null) {
                dao.delete(foundRoom.getId());
                ctx.status(204).result("Hotel successfully deleted");
            } else {
                ctx.status(500).result("Hotel was not found");
            }
        };
    }

    public static Handler update(RoomDAO dao) {
        return ctx -> {
            //retrieving data
            int id = Integer.parseInt(ctx.pathParam("id"));
            //instantiating a dto by the data received.
            RoomDto dto = ctx.bodyAsClass(RoomDto.class);
            dto.setId(id);
            //retreiving Hotel by ID
            Room r = dao.getById(id);
            //Updating the name which where received
            r.setPrice(dto.getPrice());
            r.setNumber(dto.getNumber());
            //updating database
            dao.update(r);
            ctx.json(dto);
        };
    }
}

package day3and4JavalinCRUD.controller;


import day3and4JavalinCRUD.dao.RoomDAO;
import day3and4JavalinCRUD.dto.RoomDto;
import day3and4JavalinCRUD.ressources.Room;
import io.javalin.http.Handler;

public class RoomController {

    public static Handler getAll(RoomDAO dao) {
        return ctx -> {
            if (dao.getAll().isEmpty()) {
                ctx.status(404).result("No Hotels available");
            } else {
                ctx.json(dao.getAll());
            }
        };
    }

    public static Handler get(RoomDAO dao) {
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
}

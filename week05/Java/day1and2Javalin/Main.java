package day1and2Javalin;

import day1and2Javalin.controllers.DogController;
import day1and2Javalin.dtos.DogDTO;
import io.javalin.Javalin;

import java.util.HashMap;
import java.util.Map;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Main {

    public static void main(String[] args) {

        Javalin app = Javalin.create().start(7007);

        //Let the dogs live in a map in memory with the id as key and the dog as value
        Map<Integer, Dog> dogs = new HashMap<>();

        Dog d1 = new Dog(1, "simba", "LABRADOR", "FEMALE", 4);
        Dog d2 = new Dog(2, "bimbo", "BULLDOG", "MALE", 3);
        Dog d3 = new Dog(3, "lola", "ROTTWEILER", "MALE", 6);

        dogs.put(d1.getId(), d1);
        dogs.put(d2.getId(), d2);
        dogs.put(d3.getId(), d3);

        //EX 2
        app.routes(
                () -> {
                    path("api", () -> {
                        get("dogs", ctx -> ctx.json(dogs));
                        get("dogs/{id}", ctx -> {
                            int id = Integer.parseInt(ctx.pathParam("id"));
                            if (dogs.containsKey(id)) {
                                ctx.json(dogs.get(id));
                            } else {
                                ctx.result("The id you are looking for does not exist");
                            }
                        });
                        post("/", ctx -> {
                            Dog incoming = ctx.bodyAsClass(Dog.class);
                            ctx.json(incoming);
                            dogs.put(incoming.getId(), incoming);
                        });
                        put("/dog/{id}/{name}", ctx -> {
                            int id = Integer.parseInt(ctx.pathParam("id"));
                            String newName = ctx.pathParam("name");

                            Dog dogToUpdate = dogs.get(id); // Henter Dog, der skal opdateres
                            if (dogToUpdate != null) {
                                dogToUpdate.setName(newName); // Opdaterer navnet på hunden
                                ctx.json(dogToUpdate); // Sender den opdaterede hund som svar
                            } else {
                                ctx.status(404).result("Dog not found"); // Hvis hunden ikke findes, sendes en 404-fejl
                            }
                        });
                        delete("/delete/dog/{id}", ctx -> {
                            int id = Integer.parseInt(ctx.pathParam("id"));
                            if (dogs.remove(id) != null) {
                                ctx.status(204); // 204 No Content The server has fulfilled the request (returnerer derfor intet)
                            } else {
                                ctx.status(404).result("Dog not found");
                            }
                        });
                    });
                });

        //EX 6-8
        app.get("api/dogs-dto", ctx -> {
                    Map<Integer, DogDTO> dogDTOs = DogController.getAllDogs(dogs);
                    if (dogDTOs.isEmpty()) {
                        ctx.status(404).result("not dogs found");
                    } else {

                        ctx.json(DogController.getAllDogs(dogs));
                    }
                }
        );

        app.get("api/dogs-dto/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            DogDTO dog = DogController.getDogById(dogs, id);
                    if (dog == null) {
                        ctx.status(404).result("The dog you are looking for was not found");
                    } else {
                        ctx.json(dog);
                    }
                }
        );

        app.post("/api/create-dog", ctx -> {
            Dog incomingDog = ctx.bodyAsClass(Dog.class);
            DogDTO createdDog = DogController.createDog(dogs, incomingDog);
            if (createdDog == null) {
                ctx.status(404).result("The dog was not created");
            } else {
                ctx.json(createdDog);
            }
        });

        // her pakker jeg hele logikken i metoden
        app.put("/api/update/dog/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            DogDTO incomingDog = ctx.bodyAsClass(DogDTO.class); // Antager at DogDTO klassen har en setter for 'name'
            DogDTO updatedDog = DogController.updateDog(id, incomingDog.getName(), dogs);

            if (updatedDog != null) {
                ctx.json(updatedDog);
                //dogs.put(updatedDog.getId(), updatedDog);  <-- da updatedDog er en DTO kan den ikke gemmes i mappen da mappen tager et Dog objekt

            } else {
                ctx.status(404).result("Dog not found");
            }
        });

        app.delete("/api/deletee/dog/{id}", ctx -> {
           int id = Integer.parseInt(ctx.pathParam("id"));

           if(DogController.deleteDog(id, dogs)){
               dogs.remove(id);
               ctx.status(204).result("Dog has been deleted");
           }else{
               ctx.status(404).result("Delete was not possible as the dig was not found");
           }

        });

    }

}

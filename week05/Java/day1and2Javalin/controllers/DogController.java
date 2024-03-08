package day1and2Javalin.controllers;

import day1and2Javalin.Dog;
import day1and2Javalin.dtos.DogDTO;
import io.javalin.http.Context;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DogController {

    public static Map<Integer, DogDTO> getAllDogs(Map<Integer, Dog> dogs) {
        return dogs.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        dog -> new DogDTO(dog.getValue().getId(), dog.getValue().getName(), dog.getValue().getBreed(), dog.getValue().getGender(), dog.getValue().getAge())
                ));
    }

    public static DogDTO getDogById(Map<Integer, Dog> dogs, int id) {
        DogDTO dog = null;
        if (dogs.get(id) != null) {
            Dog d = dogs.get(id);
            dog = new DogDTO(d.getId(), d.getName(), d.getBreed(), d.getGender(), d.getAge());
        }
        return dog;
    }

    public static DogDTO createDog(Map<Integer, Dog> dogs, Dog d) {
        dogs.put(d.getId(), d);
        DogDTO dogDTO = new DogDTO(d.getId(), d.getName(), d.getBreed(), d.getGender(), d.getAge());
        return dogDTO;
    }


    public static DogDTO updateDog(int id, String newName, Map<Integer, Dog> dogs) {
        Dog dog = dogs.get(id);
        if (dog != null) {
            dog.setName(newName); // Opdaterer hundens navn
            // Opdater andre felter efter behov
            return new DogDTO(dog.getId(), dog.getName(), dog.getBreed(), dog.getGender(), dog.getAge());
        }
        return null; // Returner null, hvis hunden ikke findes
    }

    public static boolean deleteDog(int id, Map<Integer, Dog> dogs){
        Dog dog = dogs.get(id);
        if (dog != null) {
            dogs.remove(dog);
            return true;
        }
        return false;
    }
}

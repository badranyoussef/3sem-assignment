package assignment8;

import java.util.HashMap;
import java.util.Map;

public class MemoryStorage <T> implements DataStorage <T>{

    private Map<Integer, T> memory = new HashMap<>();
    private static int id = 0;

    @Override
    public String store(T data) {
        memory.put(id, data);
        String currentId = String.valueOf(id); // Konverterer id til en streng

        id++; // Inkrementerer id for næste dataelement

        //Man kunne også sende en anden meddelelse ved fejl.

        return "Filen er gemt under id: " + currentId;
    }

    @Override
    public T retrieve(String id) {
        int idInt = Integer.parseInt(id);
        return memory.get(idInt);
    }

    public Map<Integer, T> getMemory() {
        return memory;
    }
}

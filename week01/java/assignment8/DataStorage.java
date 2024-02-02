package assignment8;

import assignment3.Employee;

interface DataStorage<T> {
    String store(T data); // return a unique ID for the stored data or the filename

    T retrieve(String source); // retrieve data from the specified source (like a file or database table or ID)
}


class DataStorageApp {
    public static void main(String[] args) {
        DataStorage<String> memoryStorage = new MemoryStorage<>();

        memoryStorage.store("TEST");
        String retrievedString = memoryStorage.retrieve("0");

        System.out.println(retrievedString);

        DataStorage<String> fileStorage = new FileStorage<>();

        fileStorage.store("Sidste test");

        fileStorage.retrieve("/Users/youssefbadran/Documents/GitHub/3sem-assignments/week01/java/assignment8/filestorage.txt");

        // Create and demonstrate DatabaseStorage
    }
}
package assignment3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Exercise1Threads {
    public static void main(String[] args) {
        // Create an ExecutorService with chashed
        ExecutorService executor = Executors.newCachedThreadPool();

        // Gennemløber bogstaverne og tilføjer dem til executeren via submit
        for (char ch = 'A'; ch <= 'Z'; ch++) {
            String text = String.valueOf(ch).repeat(3);  // Således gentages bogstavet 3 gange
            Runnable task = () -> System.out.println(text);
            executor.submit(task);
        }

        // Sluk alle threads når arbejdet er udført
        executor.shutdown();
    }

}

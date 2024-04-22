package assignment3;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Exercise4ActivateAllCores {


    public static void main(String[] args) {

        int cores = 8;

        //Creating an ExecutorService with fixed ThreadPool with the amount of cores of my laptop
        ExecutorService executor = Executors.newFixedThreadPool(cores);

        //Creating a loop to submit the same amount of tasks of the amount of cores
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            Runnable tasks = () -> {
                System.out.println("This is task nr:" + finalI);
            };

            /*
            try {
                Thread.sleep(500);

            } catch (InterruptedException e) {
                System.out.println("Thread was interruptet: " + e.getMessage());;
            }

            */
            executor.submit(tasks);
        }
        executor.shutdown();
    }
}


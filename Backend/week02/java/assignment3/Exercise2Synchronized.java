package assignment3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Exercise2Synchronized {
    private int count = 0;

    public static void main(String[] args) {
        Exercise2Synchronized ex = new Exercise2Synchronized();

        //ExecutorService executor = Executors.newCachedThreadPool();
        ExecutorService executor = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 10 ; i++) {

            Runnable task = () -> {
                ex.increment();
                System.out.println(Thread.currentThread().getName() + " - count: " + ex.getCount());
            };
            executor.submit(task);
        }

        executor.shutdown();

    }

    // Method to increment the count, synchronized to ensure thread safety
    public synchronized void increment() {
        count++;
    }

    // Method to retrieve the current count value
    public synchronized int getCount() {
        return count;
    }

}

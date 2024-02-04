package assignment9;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Task {
    void run() {
        // Simulate some computation
        try {
            System.out.println("countung startet");
            Thread.sleep(1000); // Simulate 1 second of work
            System.out.println("counting done");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ConcurrencyExercise {
    public static void main(String[] args) {
        // Using CompletableFuture
        System.out.println("The program just startet");
        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> new Task().run());
        CompletableFuture<Void> future2 = CompletableFuture.runAsync(() -> new Task().run());

        CompletableFuture<Void> allFutures = CompletableFuture.allOf(future1, future2);
        allFutures.thenRun(() -> System.out.println("All CompletableFuture tasks completed."));

        // Using ExecutorService
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(() -> new Task().run());
        executorService.submit(() -> new Task().run());

        executorService.shutdown();
        System.out.println("All ExecutorService tasks submitted.");
    }
}
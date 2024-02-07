package assignment3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Exercise3AddingNumbers {

    /*      Question?????
    * inserts a number into an ArrayList and prints out the size of the list.
    * The list does not always contain a thousand numbers. Why is that? Propose a solution.
    * See if you can come up with a solution that does not use synchronized.
    *
    *
    *       My answer
    *See below
    */


    public static void main(String[] args) {

                //ORIGINAL Choice
        //ExecutorService workingJack = Executors.newFixedThreadPool(17);

                //To solve the issue i would rather fix the number of threads to one
        //ExecutorService workingJack = Executors.newFixedThreadPool(1);

                //or just use the CachedThreadPool method
        ExecutorService workingJack = Executors.newCachedThreadPool();

        System.out.println("Main starts");
        IntegerList integerList = new IntegerList();

        for (int count = 0; count < 1000; count++) {
            workingJack.submit(new TaskToAddCount(integerList, count));
        }
        System.out.println("Main is done");
        workingJack.shutdown();

    }

    private static class IntegerList {
        private static List<Integer> list = new ArrayList<>();
        public void addCount(int count) {
            list.add(count);
            System.out.println("Task: " + count + ": List size = " + list.size());
        }
    }
    private static class TaskToAddCount implements Runnable {
        // Gets a reference to the shared list and the count to add
        private IntegerList integerList;
        private int count;

        TaskToAddCount(IntegerList integerList, int count) {
            this.integerList = integerList;
            this.count = count;
        }

        @Override
        public void run() {
            try {
                Thread.sleep((int) Math.random()*800+200);
                integerList.addCount(count);
            } catch (InterruptedException ex) {
                System.out.println("Thread was interrupted");
            }
        }
    }
}

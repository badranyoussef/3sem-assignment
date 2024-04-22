package assignment3;


/*
Create a program to get data from 10 differenct web services (APIs) at the same time.
Get the returned value from the web service and save it in DTOs.
Collect all the DTO data in a mega DTO and print it out in a nice format.
*/

import assignment1.APIExercise;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class Exercise6ThreadAndAPI {
    static APIExercise api = new APIExercise();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();


    public static void main(String[] args) {

        String dadJoke = "https://icanhazdadjoke.com/";
        String chuckJoke = "https://api.chucknorris.io/jokes/random";
        Exercise6ThreadAndAPI ex = new Exercise6ThreadAndAPI();

        ExecutorService executor = Executors.newCachedThreadPool();


        /*
        Runnable task1 = () -> {
            String joke = api.getResponseBody("https://icanhazdadjoke.com/");
            jokeDTO dto = gson.fromJson(joke, jokeDTO.class);
            System.out.println(Thread.currentThread().getName()+ " DadJoke: " +dto.getJoke());
            System.lineSeparator();
        };

        Runnable task2 = () -> {
            String joke = api.getResponseBody("https://api.chucknorris.io/jokes/random");
            chucknorrisJokesDTO dto = gson.fromJson(joke, chucknorrisJokesDTO.class);
            System.out.println(Thread.currentThread().getName()+ " Joke: " +dto.getValue());
            System.lineSeparator();
        };

        Runnable task3 = () -> {
            String joke = api.getResponseBody("https://icanhazdadjoke.com/");
            jokeDTO dto = gson.fromJson(joke, jokeDTO.class);
            System.out.println(Thread.currentThread().getName()+ " DadJoke: " + dto.getJoke());
            System.lineSeparator();
        };

        Runnable task4 = () -> {
            String joke = api.getResponseBody("https://api.chucknorris.io/jokes/random");
            chucknorrisJokesDTO dto = gson.fromJson(joke, chucknorrisJokesDTO.class);
            System.out.println(Thread.currentThread().getName()+ " Joke: " +dto.getValue());
            System.lineSeparator();
        };

        Runnable task5 = () -> {
            String joke = api.getResponseBody("https://icanhazdadjoke.com/");
            jokeDTO dto = gson.fromJson(joke, jokeDTO.class);
            System.out.println(Thread.currentThread().getName()+ " DadJoke: " +dto.getJoke());
            System.lineSeparator();
        };

        Runnable task6 = () -> {
            String joke = api.getResponseBody("https://api.chucknorris.io/jokes/random");
            chucknorrisJokesDTO dto = gson.fromJson(joke, chucknorrisJokesDTO.class);
            System.out.println(Thread.currentThread().getName()+ " Joke: " +dto.getValue());
            System.lineSeparator();
        };

        Runnable task7 = () -> {
            String joke = api.getResponseBody("https://icanhazdadjoke.com/");
            jokeDTO dto = gson.fromJson(joke, jokeDTO.class);
            System.out.println(Thread.currentThread().getName()+ " DadJoke: " +dto.getJoke());
            System.lineSeparator();
        };

        Runnable task8 = () -> {
            String joke = api.getResponseBody("https://api.chucknorris.io/jokes/random");
            chucknorrisJokesDTO dto = gson.fromJson(joke, chucknorrisJokesDTO.class);
            System.out.println(Thread.currentThread().getName()+ " Joke: " +dto.getValue());
            System.lineSeparator();
        };

        Runnable task9 = () -> {
            String joke = api.getResponseBody("https://icanhazdadjoke.com/");
            jokeDTO dto = gson.fromJson(joke, jokeDTO.class);
            System.out.println(Thread.currentThread().getName()+ " DadJoke: " +dto.getJoke());
            System.lineSeparator();
        };

         */

        Callable<String> task1 = () -> {
            return ex.getDadJoke(dadJoke);
        };
        Callable<String> task2 = () -> {
            return ex.getChuckJoke(chuckJoke);
        };
        Callable<String> task3 = () -> {
            return ex.getDadJoke(dadJoke);
        };
        Callable<String> task4 = () -> {
            return ex.getChuckJoke(chuckJoke);
        };
        Callable<String> task5 = () -> {
            return ex.getDadJoke(dadJoke);
        };
        Callable<String> task6 = () -> {
            return ex.getChuckJoke(chuckJoke);
        };
        Callable<String> task7 = () -> {
            return ex.getDadJoke(dadJoke);
        };
        Callable<String> task8 = () -> {
            return ex.getChuckJoke(chuckJoke);
        };
        Callable<String> task9 = () -> {
            return ex.getDadJoke(dadJoke);
        };
        Callable<String> task10 = () -> {
            return ex.getChuckJoke(chuckJoke);
        };


        Future<String> fut1 = executor.submit(task1);
        Future<String> fut2 = executor.submit(task2);
        Future<String> fut3 = executor.submit(task3);
        Future<String> fut4 = executor.submit(task4);
        Future<String> fut5 = executor.submit(task5);
        Future<String> fut6 = executor.submit(task6);
        Future<String> fut7 = executor.submit(task7);
        Future<String> fut8 = executor.submit(task8);
        Future<String> fut9 = executor.submit(task9);
        Future<String> fut10 = executor.submit(task10);

        List<Future<String>> futureList = List.of(fut1, fut2, fut3,fut4,fut5,fut6,fut7,fut8,fut9,fut10);

        for( Future<String> fut : futureList ) {
            try {
                System.out.println( fut.get() ); // get() blocks until result is available
            } catch ( Exception e ) {
                System.out.println( "Exception: " + e.getMessage() );
            }
        }

        executor.shutdown();


    }

    public String getDadJoke(String url){
        String joke = api.getResponseBody(url);
        jokeDTO dto = gson.fromJson(joke, jokeDTO.class);
        //System.out.println(Thread.currentThread().getName()+ " DadJoke: " +dto.getJoke());
        //System.lineSeparator();
        return dto.getJoke();
    }

    public String getChuckJoke(String url){
        String joke = api.getResponseBody("https://api.chucknorris.io/jokes/random");
        chucknorrisJokesDTO dto = gson.fromJson(joke, chucknorrisJokesDTO.class);
        //System.out.println(Thread.currentThread().getName()+ " Joke: " +dto.getValue());
        //System.lineSeparator();
        return dto.getValue();
    }


}

package assignment8;

import java.io.*;
import java.util.Scanner;


public class FileStorage <T> implements DataStorage <T>{

    String fileName = "filestorage.txt";
    private File file = new File("/Users/youssefbadran/Documents/GitHub/3sem-assignments/week01/java/assignment8/filestorage.txt");

    String path = "/Users/youssefbadran/Documents/GitHub/3sem-assignments/week01/java/assignment8/filestorage.txt";

    @Override
    public String store(T data) {
        try (FileWriter writer = new FileWriter(file, true)) {
            writer.write(data.toString()); // Skriver strengindholdet til filen
            writer.write(System.lineSeparator());
            System.out.println("Indholdet er gemt i filen: " + fileName );
        } catch (IOException e) {
            System.err.println("Der opstod en fejl under skrivning til filen: " + e.getMessage());
        }
        return "succeed";
    }


    @Override
    public T retrieve(String source) {

        StringBuilder fileContent = new StringBuilder();
        String content = "";

        try {
            File file = new File(source);

            //ny scanner som laser fra file
            Scanner scanner = new Scanner(file);

            //hvis scanner har en next så print next... dvs print næste linje
            while(scanner.hasNext()) {
                String s = scanner.nextLine();
                System.out.println(s);
                //content = String.valueOf(fileContent.append(scanner.nextLine()).append(System.lineSeparator()));
            }
            System.out.println("\nTeksten er hermed læst");
        }
        catch(FileNotFoundException e){
            System.out.println("Filen findes ikke");

        }

        return (T) content;
    }
}

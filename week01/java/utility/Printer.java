package utility;

public class Printer {

    //Da jeg arbejder med statiste metoder kan selve klassen ikke laves generisk. Derimod skal de statiske metoder
    //laves generiske

    public static <T> void result(T t){
        System.out.println(t);
    }

    public static <T> void text(T t){
        System.out.println(t);
    }

    public static void aBreak(){
        System.out.println("");
    }
}

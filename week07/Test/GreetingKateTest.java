import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GreetingKateTest {


//        Requirement 1
    public String greet(String name) {
        return "Hello, " + name + ".";
    }

    @Test
    @DisplayName("When giving a name, return 'Hello, name.'")
    void test1() {
        assertEquals("Hello, Bob.", greet("Bob"));

    }


//    Requirement 2

    String name_ = null;
    public String standIn(String _name){
        if(_name == null){
            return "Hello, my friend.";
        }else{
            return "Hello, " + _name + ".";
        }
    }

    @Test
    @DisplayName("When name is null, return 'Hello, my friend.'")
    void test2() {
        assertEquals("Hello, my friend.", standIn(name_));

    }


//    Requirement 3
    public String shouting(String name) {
        if (name.equals(name.toUpperCase())) {
            return "HELLO, " + name + "!";
        } else {
            return "Hello, " + name + ".";
        }
    }


    @Test
    @DisplayName("When input is in uppercase, return uppercase greeting")
    void test3() {
        assertEquals("HELLO, JERRY!", shouting("JERRY"));
        assertEquals("Hello, Jerry.", shouting("Jerry"));

    }

    //        Requirement 4
    public String greetAll(String[] names) {
        return "Hello, " + names[0] + " and " + names[1] + ".";
    }


    public String greetAllWithObject(Object name) {
        if (name instanceof String[]) {
            String[] names = (String[]) name;
            if (names.length == 2) {
                return "Hello, " + names[0] + " and " + names[1] + ".";
            }
        }
        return "Hello, my friend.";
    }


    String[] arrNames = {"Jill", "Jane"};

    @Test
    @DisplayName("When giving a name, return 'Hello, name.'")
    void test4() {
        assertEquals("Hello, Jill and Jane.", greetAll(arrNames));
        assertEquals("Hello, Jill and Jane.", greetAllWithObject(arrNames));

    }


}

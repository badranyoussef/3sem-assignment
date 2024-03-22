import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GreetingKateTest {

    private Greet greet = new Greet();
    private String name_ = null;
    private String[] arrNames = {"Jill", "Jane"};
    String[] arrOfNames = {"Amy", "Brian", "Charlotte"};
    String[] mixedNames = {"Amy", "BRIAN", "Charlotte"};
    String[] mixedNamesWithComma = {"Bob", "Charlie, Dianne"};

    @Test
    @DisplayName("When giving a name, return 'Hello, name.'")
    void test1() {
        assertEquals("Hello, Bob.", greet.greet("Bob"));

    }


//    Requirement 2

    @Test
    @DisplayName("When name is null, return 'Hello, my friend.'")
    void test2() {
        assertEquals("Hello, my friend.", greet.standIn(name_));

    }

    @Test
    @DisplayName("When input is in uppercase, return uppercase greeting")
    void test3() {
        assertEquals("HELLO, JERRY!", greet.shouting("JERRY"));
        assertEquals("Hello, Jerry.", greet.shouting("Jerry"));

    }

    @Test
    @DisplayName("When name is an array of two names, then both names should be printed.")
    void test4() {
        assertEquals("Hello, Jill and Jane.", greet.greetAll(arrNames));
        assertEquals("Hello, Jill and Jane.", greet.greetAllWithObject(arrNames));

    }

    @Test
    @DisplayName("When name represents more than two names, separate them with commas and close with an Oxford comma and 'and'")
    void test5() {
        assertEquals("Hello, Amy, Brian, and Charlotte.", greet.greetEveryone2(arrOfNames));
    }

    @Test
    @DisplayName("when name is ['Amy', 'BRIAN', 'Charlotte'], then the method should return the string 'Hello, Amy and Charlotte. AND HELLO BRIAN!'")
    void test6() {
        assertEquals("Hello, Amy and Charlotte. AND HELLO BRIAN!", greet.mixedNames(mixedNames));
    }

    @Test
    @DisplayName("when name is ['Bob', 'Charlie, Dianne'], then the method should return the string 'Hello, Bob, Charlie, and Dianne.'")
    void test7() {
        assertEquals("Hello, Bob, Charlie and Dianne.", greet.namesWithComma(mixedNamesWithComma));
    }

}

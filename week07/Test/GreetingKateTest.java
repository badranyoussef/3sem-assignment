import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GreetingKateTest {

    private Greet greet = new Greet();
    private String[] arrNames = {"Jill", "Jane"};
    String[] arrOfNames = {"Amy", "Brian", "Charlotte"};
    String[] mixedNames = {"Amy", "BRIAN", "Charlotte"};
    String[] mixedNamesWithComma = {"Bob", "Charlie, Dianne"};

    @Test
    @DisplayName("When giving a name, return 'Hello, name.'")
    void test1() {

        //Given
        String setName = "Bob";
        String expected = "Hello, Bob.";

        //when
        String result = greet.allMethods(setName);

        //then
        assertEquals(result, expected);

    }


//    Requirement 2

    @Test
    @DisplayName("When name is null, return 'Hello, my friend.'")
    void test2() {
        // Given
        String setName = null;
        String expectedString = "Hello, my friend.";

        // When
        String result = greet.allMethods(setName);

        //Then
        assertEquals(expectedString, result);
    }

    @Test
    @DisplayName("When input is in uppercase, return uppercase greeting")
    void test3() {
        assertEquals("HELLO, JERRY!", greet.allMethods("JERRY"));
        assertEquals("Hello, Jerry.", greet.allMethods("Jerry"));

    }

    @Test
    @DisplayName("When name is an array of two names, then both names should be printed.")
    void test4() {
        assertEquals("Hello, Jill and Jane.", greet.allMethods(arrNames));
        assertEquals("Hello, Jill and Jane.", greet.allMethods(arrNames));

    }

    @Test
    @DisplayName("When name represents more than two names, separate them with commas and close with an Oxford comma and 'and'")
    void test5() {
        assertEquals("Hello, Amy, Brian, and Charlotte.", greet.allMethods(arrOfNames));
    }

    @Test
    @DisplayName("when name is ['Amy', 'BRIAN', 'Charlotte'], then the method should return the string 'Hello, Amy and Charlotte. AND HELLO BRIAN!'")
    void test6() {
        assertEquals("Hello, Amy and Charlotte. AND HELLO BRIAN!", greet.allMethods(mixedNames));
    }

    @Test
    @DisplayName("when name is ['Bob', 'Charlie, Dianne'], then the method should return the string 'Hello, Bob, Charlie, and Dianne.'")
    void test7() {
        assertEquals("Hello, Bob, Charlie and Dianne.", greet.allMethods(mixedNamesWithComma));
    }

}

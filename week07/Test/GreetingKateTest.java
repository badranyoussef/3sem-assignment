import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GreetingKateTest {

    private Greet greet = new Greet();

    @Test
    @DisplayName("When giving a name, return 'Hello, name.'")
    void test1() {

        //Given
        String name = "Bob";
        String expected = "Hello, Bob.";

        //when
        String result = greet.allMethods(name);

        //then
        assertEquals(result, expected);

    }


//    Requirement 2

    @Test
    @DisplayName("When name is null, return 'Hello, my friend.'")
    void test2() {
        // Given
        String name = null;
        String expectedString = "Hello, my friend.";

        // When
        String result = greet.allMethods(name);

        //Then
        assertEquals(expectedString, result);
    }

    @Test
    @DisplayName("When input is in uppercase, return uppercase greeting")
    void test3() {
        // Given
        String name = "JERRY";
        String name2 = "Jerry";
        String expectedString = "HELLO, JERRY!";
        String expectedString2 = "Hello, Jerry.";

        // When
        String result = greet.allMethods(name);
        String result2 = greet.allMethods(name2);

        //Then
        assertEquals(expectedString, result);
        assertEquals(expectedString2, result2);

    }

    @Test
    @DisplayName("When name is an array of two names, then both names should be printed.")
    void test4() {

        // Given
        String[] names = {"Jill", "Jane"};

        String expectedString = "Hello, Jill and Jane.";

        // When
        String result = greet.allMethods(names);

        //Then
        assertEquals(expectedString, result);

    }

    @Test
    @DisplayName("When name represents more than two names, separate them with commas and close with an Oxford comma and 'and'")
    void test5() {

        // Given
        String[] names = {"Amy", "Brian", "Charlotte"};

        String expectedString = "Hello, Amy, Brian, and Charlotte.";

        // When
        String result = greet.allMethods(names);

        //Then
        assertEquals(expectedString, result);


        assertEquals("Hello, Amy, Brian, and Charlotte.", greet.allMethods(names));
    }

    @Test
    @DisplayName("when name is ['Amy', 'BRIAN', 'Charlotte'], then the method should return the string 'Hello, Amy and Charlotte. AND HELLO BRIAN!'")
    void test6() {

        // Given
        String[] names = {"Amy", "BRIAN", "Charlotte"};
        String expectedString = "Hello, Amy and Charlotte. AND HELLO BRIAN!";

        // When
        String result = greet.allMethods(names);

        //Then
        assertEquals(expectedString, result);
    }

    @Test
    @DisplayName("when name is ['Bob', 'Charlie, Dianne'], then the method should return the string 'Hello, Bob, Charlie, and Dianne.'")
    void test7() {
        //Given
        String[] names = {"Bob", "Charlie, Dianne"};
        String expectedString = "Hello, Bob, Charlie and Dianne.";

        //When
        String result = greet.allMethods(names);

        //Then
        assertEquals(expectedString,result);
    }

    @Test
    @DisplayName("when name is [\"Bob\", \"\\\"Charlie, Dianne\\\"\"], then the method should return the string \"Hello, Bob and Charlie, Dianne.\"")
    void test8() {
        //Given
        String[] names = {"Bob", "\"Charlie, Dianne\""};
        String expectedString = "Hello, Bob and Charlie, Dianne.";

        //When
        String result = greet.allMethods(names);

        //Then
        assertEquals(expectedString,result);
    }
}

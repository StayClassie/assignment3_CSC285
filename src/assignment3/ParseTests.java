package assignment3;

import static org.junit.Assert.*;
import java.util.*;

import org.junit.BeforeClass;
import org.junit.Test;


public class ParseTests {

    @BeforeClass
    public static void setUp() {
        Main.initialize();
    }
        @Test
    public void testParse() {
        String input = "hello place";
        Scanner scan = new Scanner(input);
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("hello");
        expected.add("place");
        assertEquals(expected, Main.parse(scan));
    }


    public void testQuit() {
        String input = "/quit";
        Scanner scan = new Scanner(input);
        Main.parse(scan); // Program should quit
    }

}

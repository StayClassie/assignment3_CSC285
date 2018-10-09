package assignment3;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Array;
import java.util.ArrayList;
import org.junit.Test;

public class WordsAreSameTest {

    @Test
    public void test(){
        ArrayList<String> output = Main.getWordLadderDFS("mouth", "mouth");
        ArrayList<String> output2 = Main.getWordLadderBFS("mouth", "mouth");
        ArrayList<String> answer = new ArrayList<String>();
        answer.add("mouth");
        answer.add("mouth");
        assertEquals(true, output.equals(answer) && output2.equals(answer));
    }

}

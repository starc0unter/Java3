package ru.chentsov.javacore3;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Evgenii Chentsov
 */
public class AppTest {

    @Test
    public void swapArrayValues() throws Exception {
        final String[] lines = {"1", "2", "3", "4", "5"};
        App.swapArrayValues(lines, 1, 3);
        final String[] correctAnswer = {"1", "4", "3", "2", "5"};
        assertArrayEquals(lines, correctAnswer);
    }

    @Test
    public void convertArrayToList() throws Exception {
        final Boolean[] array = {true, false, false, true};
        assertEquals(App.convertArrayToList(array).getClass().getSimpleName(),
                ArrayList.class.getSimpleName());
    }

}
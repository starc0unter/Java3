package ru.chentsov.javacore3.task2;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author Evgenii Chentsov
 */
@RunWith(Parameterized.class)
public class Task2Test {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {new Integer[] {1, 4, 1, 1, 4, 1, 1, 1}, true},
                {new Integer[] {2, 4, 1, 4, 4, 4, 4, 1}, false},
                {new Integer[] {4, 4, 1, 4, 4, 4, 4, 8}, false},
                {new Integer[] {4, 1, 0, 4, 1}, false}
        });
    }

    private int[] testArray;
    private boolean expectedResult;

    public Task2Test(Integer[] testArray, boolean expectedResult) {
        this.testArray = Arrays.stream(testArray).mapToInt(Integer::intValue).toArray();
        this.expectedResult = expectedResult;
    }

    @Test
    public void containsOnlyTest() throws Exception {
        int[] content = {1, 4};
        Assert.assertEquals("test array is " + Arrays.toString(testArray) +
                ", array must consist of " + Arrays.toString(content),
                expectedResult, Task2.containsOnly(testArray, content));
    }


}
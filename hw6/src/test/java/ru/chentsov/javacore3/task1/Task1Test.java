package ru.chentsov.javacore3.task1;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.chentsov.javacore3.task1.Task1;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author Evgenii Chentsov
 */
@RunWith(Enclosed.class)
public class Task1Test {

    @RunWith(Parameterized.class)
    public static class ArrayExtractorParam {

        @Parameterized.Parameters
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][] {
                    {new Integer[] {1, 2, 4, 4, 2, 3, 4, 1, 7}, new Integer[] {1, 7}},
                    {new Integer[] {1, 2, 5, 6, 7, 8, 9, 4},    new Integer[] {}},
                    {new Integer[] {4, 1, 2, 3, 5},             new Integer[] {1, 2, 3, 5}}
            });
        }

        private int[] testArray;
        private int[] expected;

        public ArrayExtractorParam(Integer[] testArray, Integer[] expected) {
            this.testArray = Arrays.stream(testArray).mapToInt(Integer::intValue).toArray();
            this.expected = Arrays.stream(expected).mapToInt(Integer::intValue).toArray();
        }

        @Test
        public void testArrayExtractor() throws Exception {
            int breaker = 4;
            Assert.assertArrayEquals("source array is" + Arrays.toString(testArray) +
                    ", breaker is " + breaker, expected, Task1.arrayExtractor(testArray, breaker));
        }

    }

    public static class ArrayExtractorNoParam {

        @Test(expected = RuntimeException.class)
        public void testArrayExtractorThrowsRuntime() throws Exception {
            int[] noBreaker = {1, 2, 3, 6};
            Task1.arrayExtractor(noBreaker, 4);
        }

    }

}
package ru.chentsov.javacore3.task2;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author Evgenii Chentsov
 */
public class Task2 {

    public static boolean containsOnly(int[] toCheck, int[] content) {
        return Arrays
                .stream(toCheck)
                .allMatch(i -> IntStream.of(content)
                        .anyMatch(j -> i == j));
    }

}

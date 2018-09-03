package ru.chentsov.javacore3.task1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Task1
{
    public static void main(String[] args) { }

    public static int[] arrayExtractor(int[] source, int breaker) {
        List<Integer> extractedList = new ArrayList<>();

        for (int i = source.length - 1; i >= 0; i--) {
            if (source[i] == breaker) break;
            extractedList.add(source[i]);
        }

        if (extractedList.size() == source.length) throw new RuntimeException();

        Collections.reverse(extractedList);
        return extractedList.stream().mapToInt(Integer::intValue).toArray();
    }

}

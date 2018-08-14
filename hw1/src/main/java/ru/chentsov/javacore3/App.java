package ru.chentsov.javacore3;

import ru.chentsov.javacore3.fruits.Apple;
import ru.chentsov.javacore3.fruits.Box;
import ru.chentsov.javacore3.fruits.Orange;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Hello world!
 *
 */
public final class App
{
    public static void main( String[] args )
    {
        System.out.println("\nTask 1");
        checkSwapArrayElements();           //covered by JUnit test
        System.out.println("\nTask 2");
        checkListConvert();                 //covered by JUnit test
        System.out.println("\nTask 3");
        experimentWithFruits();
    }

    public static <T> void swapArrayValues(final T[] array, final int first, final int second) {
        final int maxIndex = array.length - 1;
        final boolean isFirstInBounds = (first >= 0 && first <= maxIndex);
        final boolean isSecondInBounds = (second >= 0 && second <= maxIndex);
        if (!isFirstInBounds || !isSecondInBounds) {
            System.out.println("Chosen indexes are out of possible bounds");
            return;
        }

        final T temp = array[first];
        array[first] = array[second];
        array[second] = temp;
    }

    public static <T> ArrayList<T> convertArrayToList(final T[] array) {
        return new ArrayList<>(Arrays.asList(array));
    }

    public static void checkSwapArrayElements() {
        final String[] lines = {"1", "2", "3", "4", "5"};

        System.out.println("Before swap:");
        System.out.println(Arrays.toString(lines));

        swapArrayValues(lines, 1, 3);

        System.out.println("After swap:");
        System.out.println(Arrays.toString(lines));
    }

    public static void checkListConvert(){
        final Boolean[] array = {true, false, false, true};
        System.out.println("Current type: " + array.getClass().getSimpleName());
        System.out.println("Changed type: " + convertArrayToList(array).getClass().getSimpleName());
    }

    public static void experimentWithFruits() {
        final Box<Apple> appleBox = new Box<>();
        appleBox.addFruit(new Apple());
        appleBox.addFruit(new Apple());
        appleBox.addFruit(new Apple());

        final Box<Orange> orangeBox = new Box<>();
        orangeBox.addFruit(new Orange());
        orangeBox.addFruit(new Orange());

        System.out.printf("AppleBox has the weight of %.2f \n",  appleBox.getWeight());
        System.out.printf("OrangeBox has the weight of %.2f \n",  orangeBox.getWeight());
        System.out.println("Do boxes have the same weight? " + appleBox.Compare(orangeBox));

        System.out.println("\nMoving apples:");
        final Box<Apple> newAppleBox = new Box<>();
        appleBox.moveTo(newAppleBox);
        System.out.println("Now newAppleBox has the weight of " + newAppleBox.getWeight());
        System.out.println("oldAppleBox now has the weight of " + appleBox.getWeight());
    }

}

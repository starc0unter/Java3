package ru.chentsov.javacore3.fruits;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Evgenii Chentsov
 */
public class Box<T extends Fruit> {

    private final List<T> fruits = new ArrayList<>();

    public void addFruit(final T fruit) {
        fruits.add(fruit);
    }

    public double getWeight() {
        return fruits.stream().mapToDouble(Fruit::getWeight).sum();
    }

    public boolean Compare(final Box<? extends Fruit> another) {
        return ((Double) this.getWeight()).equals(another.getWeight());
    }

    public void moveTo(final Box<T> another) {
        fruits.forEach(another::addFruit);
        fruits.clear();
    }

}

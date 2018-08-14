package ru.chentsov.javacore3.fruits;

/**
 * @author Evgenii Chentsov
 */
public class Fruit {

    private final double weight;
    private final String description;

    public double getWeight() {
        return weight;
    }

    Fruit(final double weight, final String description) {
        this.weight = weight;
        this.description = description;
    }

}

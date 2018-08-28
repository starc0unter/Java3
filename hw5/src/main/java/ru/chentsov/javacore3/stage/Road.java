package ru.chentsov.javacore3.stage;

import ru.chentsov.javacore3.App;
import ru.chentsov.javacore3.Car;

public class Road extends Stage {

    public Road (int length) {
        this.length = length;
        this.description = "Дорога " + length + " метров";
    }

    @Override
    public void go (Car c) {
        try {
            logger.info("{} начал этап: {}", c.getName(), description);
            Thread.sleep(length / c.getSpeed() * 1000 );
            logger.info("{} закончил этап: {}", c.getName(), description);
            App.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

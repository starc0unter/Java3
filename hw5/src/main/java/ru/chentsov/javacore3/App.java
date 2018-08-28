package ru.chentsov.javacore3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.chentsov.javacore3.stage.Road;
import ru.chentsov.javacore3.stage.Tunnel;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Hello world!
 *
 */
public class App {

    public static final int CARS_COUNT = 4 ;
    private static CountDownLatch endLatch;

    static final Logger logger = LogManager.getLogger(App.class.getName());

    public static void main (String[] args) {
        logger.info("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];

        CyclicBarrier startBarrier = new CyclicBarrier(CARS_COUNT + 1);
        endLatch = new CountDownLatch(CARS_COUNT * race.getStages().size());

        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), startBarrier);
        }

        ExecutorService carService = Executors.newFixedThreadPool(CARS_COUNT);
        for (Car car : cars) {
            carService.execute(car);
        }

        try {
            startBarrier.await();
            logger.info("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
            endLatch.await();
            logger.info("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
        } catch (Exception e) {
            logger.error("Got an error during the wait time");
            e.printStackTrace();
        }
        carService.shutdown();
    }

    public static void countDown() {
        endLatch.countDown();
    }

}

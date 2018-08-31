package ru.chentsov.javacore3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.CyclicBarrier;

public class Car implements Runnable {

    private static int CARS_COUNT;
    static {
        CARS_COUNT = 0 ;
    }

    private Race race;
    private int speed;
    private String name;
    private CyclicBarrier cb;
    private static volatile boolean hasWinner = false;
    private static final Object lock = new Object();
    static final Logger logger = LogManager.getLogger(Car.class.getName());


    public String getName () {
        return name;
    }

    public int getSpeed () {
        return speed;
    }

    Car(Race race, int speed, CyclicBarrier  cb) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
        this.cb = cb;
    }

    @Override
    public void run() {
        try {
            logger.info("{} готовится", this.name);
            Thread.sleep(500 + ( int )(Math.random() * 800));
            logger.info("{} готов", this.name);

            cb.await();
            Thread.sleep(20);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
        checkWin();
    }

    private void checkWin() {
        synchronized (lock) {
            if (hasWinner) return;
            logger.info("{} - WIN", this.name);
            hasWinner = true;
        }
    }

}

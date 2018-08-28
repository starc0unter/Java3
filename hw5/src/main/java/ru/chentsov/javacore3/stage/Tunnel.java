package ru.chentsov.javacore3.stage;

import ru.chentsov.javacore3.App;
import ru.chentsov.javacore3.Car;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {

    private Semaphore semaphore;

    public Tunnel () {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
        semaphore = new Semaphore(App.CARS_COUNT / 2);
    }

    @Override
    public void go (Car c) {
        try {
            try {
                logger.info("{} готовится к этапу (ждет): {}", c.getName(), description);
                semaphore.acquire();
                logger.info("{} начал этап: {}", c.getName(), description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                logger.info("{} закончил этап: {}", c.getName(), description);
                semaphore.release();
                App.countDown();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

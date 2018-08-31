package ru.chentsov.javacore3.stage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.chentsov.javacore3.Car;

public abstract class Stage {

    int length;
    String description;
    static final Logger logger = LogManager.getLogger(Stage.class.getName());

    @SuppressWarnings("unused")
    public String getDescription () {
        return description;
    }

    public abstract void go (Car c);

}
